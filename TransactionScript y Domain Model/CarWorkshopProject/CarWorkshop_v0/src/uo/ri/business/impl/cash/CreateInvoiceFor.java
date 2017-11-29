package uo.ri.business.impl.cash;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriaGateway;
import uo.ri.persistence.FacturaGateway;

public class CreateInvoiceFor {

	private Connection connection;

	private List<Long> idsAveria;

	public CreateInvoiceFor(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	public Map<String, Object> execute() throws BusinessException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);

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
			;
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
			}
			;
			throw e;
		} finally {
			Jdbc.close(connection);
		}

		return map;

	}

	@SuppressWarnings("resource")
	private void verificarAveriasTerminadas(List<Long> idsAveria) throws BusinessException, SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = connection.prepareStatement(Conf.get("SQL_VERIFICAR_ESTADO_AVERIA"));
		AveriaGateway gate = PersistenceFactory.getAveriasGateway();
		gate.setConnection(connection);
		gate.setResultSet(rs);
		for (Long idAveria : idsAveria) {
			pst.setLong(1, idAveria);
			gate.verificarEstadoAverias(pst);
			if (rs.next() == false) {
				throw new BusinessException("No existe la averia " + idAveria);
			}
			String status = rs.getString(1);
			if (!"TERMINADA".equalsIgnoreCase(status)) {
				throw new BusinessException("No está terminada la avería " + idAveria);
			}
		}

	}

	private void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException {

		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(Conf.get("SQL_ACTUALIZAR_ESTADO_AVERIA"));
			AveriaGateway gate = PersistenceFactory.getAveriasGateway();
			gate.setConnection(connection);

			for (Long idAveria : idsAveria) {
				pst.setString(1, status);
				pst.setLong(2, idAveria);
				gate.actualizarEstadoAverias(pst);
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws SQLException {

		PreparedStatement pst = null;
		pst = connection.prepareStatement(Conf.get("SQL_VINCULAR_AVERIA_FACTURA"));
		AveriaGateway gate = PersistenceFactory.getAveriasGateway();
		gate.setConnection(connection);
		for (Long idAveria : idsAveria) {
			pst.setLong(1, idFactura);
			pst.setLong(2, idAveria);
			gate.vincularAveriasConFactura(pst);
		}

	}

	private long crearFactura(long numeroFactura, Date fechaFactura, double iva, double totalConIva)
			throws SQLException {

		PreparedStatement pst = null;

		pst = connection.prepareStatement(Conf.get("SQL_INSERTAR_FACTURA"));
		pst.setLong(1, numeroFactura);
		pst.setDate(2, new java.sql.Date(fechaFactura.getTime()));
		pst.setDouble(3, iva);
		pst.setDouble(4, totalConIva);
		pst.setString(5, "SIN_ABONAR");
		FacturaGateway gate = PersistenceFactory.getFacturaGateway();
		gate.setConnection(connection);
		gate.insertarFactura(pst);

		return getGeneratedKey(numeroFactura); // Id de la nueva factura
												// generada
	}

	private long getGeneratedKey(long numeroFactura) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		pst = connection.prepareStatement(Conf.get("SQL_RECUPERAR_CLAVE_GENERADA"));
		pst.setLong(1, numeroFactura);
		FacturaGateway gate = PersistenceFactory.getFacturaGateway();
		gate.setConnection(connection);
		gate.setResultSet(rs);
		gate.recuperarClaveFacturaGenerada(pst);
		return rs.getLong(1);
		//TODO: hacer que el retorno sea de recuperarClaveFacturaGenerada
	}

	private Long generarNuevoNumeroFactura() throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get("SQL_ULTIMO_NUMERO_FACTURA"));
			FacturaGateway gate = PersistenceFactory.getFacturaGateway();
			gate.setConnection(connection);
			gate.setResultSet(rs);
			gate.ultimoNumeroFactura(pst);
			if (rs.next()) {
				return rs.getLong(1) + 1; // +1, el siguiente
			} else { // todavía no hay ninguna
				return 1L;
			}
		} finally {
			Jdbc.close(rs, pst);
		}
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

	private void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException {
		PreparedStatement pst = null;

		try {
			pst = connection.prepareStatement(Conf.get("SQL_UPDATE_IMPORTE_AVERIA"));
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		} finally {
			Jdbc.close(pst);
		}
	}

	private double consultaImporteRepuestos(Long idAveria) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get("SQL_IMPORTE_REPUESTOS"));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}

			return rs.getDouble(1);

		} finally {
			Jdbc.close(rs, pst);
		}
	}

	private double consultaImporteManoObra(Long idAveria) throws BusinessException, SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(Conf.get("SQL_IMPORTE_MANO_OBRA"));
			pst.setLong(1, idAveria);

			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("La averia no existe o no se puede facturar");
			}

			return rs.getDouble(1);

		} catch (BusinessException e) {
			throw e;
		} finally {
			Jdbc.close(rs, pst);
		}

	}

}
