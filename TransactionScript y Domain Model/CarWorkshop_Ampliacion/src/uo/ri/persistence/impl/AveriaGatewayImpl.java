package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.AveriaGateway;

public class AveriaGatewayImpl implements AveriaGateway {

	private Connection con;
	private PreparedStatement pst;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void verificarEstadoAverias(Map<String, Object> map) throws BusinessException {
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(Conf.get("SQL_VERIFICAR_ESTADO_AVERIA"));
			for (Long idAveria : (List<Long>) map.get("idsAveria")) {
				pst.setLong(1, idAveria);
				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("No existe la averia " + idAveria);
				}
				String status = rs.getString(1);
				if (!"TERMINADA".equalsIgnoreCase(status)) {
					throw new BusinessException("No está terminada la avería " + idAveria);
				}
			}
		} catch (SQLException e) {
			throw new BusinessException(
					"Un error inesperado ha ocurrido en la BD en Verificar Averia por favor pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actualizarEstadoAverias(Map<String, Object> map) throws BusinessException {
		try {
			pst = con.prepareStatement(Conf.get("SQL_ACTUALIZAR_ESTADO_AVERIA"));
			for (Long idAveria : (List<Long>) map.get("idsAveria")) {
				pst.setString(1, (String) map.get("status"));
				pst.setLong(2, idAveria);
			}
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException(
					"Un error inesperado ha ocurrido en la BD en Actualizar Averia por favor pongase en contacto con su proveedor ");
		} finally {
			Jdbc.close(pst);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void vincularAveriasConFactura(Map<String, Object> map) throws BusinessException {
		try {
			pst = con.prepareStatement(Conf.get("SQL_VINCULAR_AVERIA_FACTURA"));
			for (Long idAveria : (List<Long>) map.get("idsAveria")) {
				pst.setLong(1, idAveria);
				pst.setLong(2, (long) map.get("idFactura"));
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			throw new BusinessException(
					"Un error inesperado ha ocurrido en la BD en Vincular Averia por favor pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void updateImporteAveria(Map<String, Object> map) throws BusinessException {
		try {
			pst = con.prepareStatement(Conf.get("SQL_UPDATE_IMPORTE_AVERIA"));
			pst.setDouble(1, (double) map.get("totalAveria"));
			pst.setLong(2, (long) map.get("idAveria"));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException(
					"Un error inesperado ha ocurrido en la BD en Vincular Averia por favor pongase en contacto con su proveedor");
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public Map<String, Object> selectAveriasOfClient(Map<String, Object> map) throws BusinessException {

		ResultSet rs = null;
		Map<String, Object> mapReturn = new HashMap<>();
		List<Long> averias = new ArrayList<>();
		try {
			pst = con.prepareStatement(Conf.get("SQL_SELECT_AVERIAS_OF_CLIENT"));
			pst.setLong(1, (long) map.get("id"));
			rs = pst.executeQuery();
			while (rs.next()) {
				averias.add(rs.getLong(1));
			}

		} catch (SQLException e) {
			throw new BusinessException(
					"Un error inesperado ha ocurrido en la BD en Seleccionar Averias De Cliente por favor pongase en contacto con su proveedor");
		}
		mapReturn.put("averias", averias);
		return mapReturn;
	}

	@Override
	public void updateUsadaBono(Map<String, Object> map) throws BusinessException {
		try {
			pst = con.prepareStatement(Conf.get("SQL_UPDATE_USED_IN_BOND"));
			pst.setLong(1, (long) map.get("id"));
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new BusinessException(
					"Un error inesperado ha ocurrido en la BD al actualizar Bonos de Cliente por favor pongase en contacto con su proveedor");
		}

	}

}
