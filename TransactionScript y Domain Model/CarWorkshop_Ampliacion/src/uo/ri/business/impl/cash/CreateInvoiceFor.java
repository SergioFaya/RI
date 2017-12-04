package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriaGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.ManoDeObraGateway;
import uo.ri.persistence.RepuestoGateway;

public class CreateInvoiceFor {

	private Connection connection;

	private List<Long> idsAveria;

	public CreateInvoiceFor(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	private void prepareDB() throws SQLException {
		connection = Jdbc.getConnection();
		connection.setAutoCommit(false);
	}

	public Map<String, Object> execute() throws BusinessException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			prepareDB();
			verificarAveriasTerminadas(idsAveria);

			long numeroFactura = generarNuevoNumeroFactura();
			Date fechaFactura = DateUtil.today();
			double totalFactura = calcularImportesAverias(idsAveria);
			double iva = porcentajeIva(totalFactura, fechaFactura);
			double importe = totalFactura * (1 + iva / 100);
			importe = Round.twoCents(importe);

			long idFactura = crearFactura(numeroFactura, fechaFactura, iva, importe);
			map.put("numeroFactura", numeroFactura);
			map.put("fechaFactura", fechaFactura);
			map.put("iva", iva);
			map.put("importe", importe);
			map.put("totalFactura", totalFactura);
			vincularAveriasConFactura(idFactura, idsAveria);
			cambiarEstadoAverias(idsAveria, "FACTURADA");
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			throw e;
		} finally {
			Jdbc.close(connection);
		}

		return map;

	}

	private void verificarAveriasTerminadas(List<Long> idsAveria) throws BusinessException {

		AveriaGateway gate = PersistenceFactory.getAveriasGateway();
		gate.setConnection(connection);
		Map<String, Object> map = new HashMap<>();
		map.put("idsAveria", idsAveria);
		gate.verificarEstadoAverias(map);

	}

	private void cambiarEstadoAverias(List<Long> idsAveria, String status) throws BusinessException{
		AveriaGateway gate = PersistenceFactory.getAveriasGateway();
		gate.setConnection(connection);
		Map<String, Object> map = new HashMap<>();
		map.put("idsAveria", idsAveria);
		map.put("status", status);
		gate.actualizarEstadoAverias(map);
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws BusinessException {

		Map<String, Object> map = new HashMap<>();
		map.put("idsAveria", idsAveria);
		map.put("idFactura", idFactura);
		
		AveriaGateway gate = PersistenceFactory.getAveriasGateway();
		gate.setConnection(connection);
		gate.vincularAveriasConFactura(map);

	}

	private long crearFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva)
			throws SQLException {

		Map<String, Object> map = new HashMap<>();
		map.put("numeroFactura", numeroFactura);
		long l = fechaFactura.getTime();
		map.put("date", new java.sql.Date(l));
		map.put("iva", iva);
		map.put("totalConIva", totalConIva);
		map.put("estatus", "SIN_ABONAR");

		FacturasGateway gate = PersistenceFactory.getFacturaGateway();
		gate.setConnection(connection);
		gate.insertarFactura(map);

		return getGeneratedKey(numeroFactura); // Id de la nueva factura
												// generada
	}

	private long getGeneratedKey(long numeroFactura) throws SQLException {

		Map<String, Object> map = new HashMap<>();
		map.put("numeroFactura", numeroFactura);

		FacturasGateway gate = PersistenceFactory.getFacturaGateway();
		gate.setConnection(connection);
		map = gate.recuperarClaveFacturaGenerada(map);
		return (long) map.get("idClave");
	}

	private Long generarNuevoNumeroFactura() throws SQLException {
		Map<String, Object> map = new HashMap<>();

		FacturasGateway gate = PersistenceFactory.getFacturaGateway();
		gate.setConnection(connection);
		map = gate.ultimoNumeroFactura();
		return (Long) map.get("number");
	}

	private double porcentajeIva(double totalFactura, Date fechaFactura) {
		return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0 : 18.0;
	}

	protected double calcularImportesAverias(List<Long> idsAveria) throws BusinessException, SQLException {

		double totalFactura = 0.0;
		for (Long idAveria : idsAveria) {
			double importeManoObra = consultaImporteManoObra(idAveria);
			double importeRepuestos = consultaImporteRepuestos(idAveria);
			double totalAveria = importeManoObra + importeRepuestos;
			actualizarImporteAveria(idAveria, totalAveria);
			totalFactura += totalAveria;
		}
		return totalFactura;
	}

	private void actualizarImporteAveria(Long idAveria, double totalAveria) throws BusinessException  {
		Map<String, Object> map = new HashMap<>();
		map.put("totalAveria", totalAveria);
		map.put("idAveria", idAveria);
		AveriaGateway  gate = PersistenceFactory.getAveriasGateway();
		gate.setConnection(connection);
		gate.updateImporteAveria(map);

	}

	private double consultaImporteRepuestos(Long idAveria) throws SQLException {
		Map<String,Object> map = new HashMap<>();
		map.put("idAveria", idAveria);
		RepuestoGateway gate = PersistenceFactory.getRepuestoGateway();
		gate.setConnection(connection);
		map = gate.selectImporteRepuestos(map);
		
		return (double) map.get("importe");
	}

	private double consultaImporteManoObra(Long idAveria) throws BusinessException, SQLException {
		
		Map<String,Object> map = new HashMap<>();
		map.put("idAveria", idAveria);

		ManoDeObraGateway gate = PersistenceFactory.getManoDeObraGateway();
		gate.setConnection(connection);
		map = gate.selectImporteManoDeObra(map);
		return (double) map.get("importe");

	}

}
