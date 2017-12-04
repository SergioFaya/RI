package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.FacturasGateway;

public class FacturaGatewayImpl implements FacturasGateway {

	private Connection con;
	private ResultSet rs;

	@Override
	public void setConnection(Connection con) {
		this.con = con;	
	}
	
	@Override
	public void insertarFactura(Map<String,Object> map) {
		PreparedStatement pst = null;
		try {
			pst = con.prepareStatement(Conf.get("SQL_INSERTAR_FACTURA"));
			pst.setLong(1, (long) map.get("numeroFactura"));
			pst.setDate(2, (Date)map.get("date"));
			pst.setDouble(3, (double) map.get("iva"));
			pst.setDouble(4, (double) map.get("totalConIva"));
			pst.setString(5, "SIN_ABONAR");
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public Map<String,Object> recuperarClaveFacturaGenerada(Map<String,Object> map) {
		PreparedStatement pst = null;
		Map<String,Object> mapReturn = new HashMap<>();
		try {
			pst = con.prepareStatement(Conf.get("SQL_RECUPERAR_CLAVE_GENERADA"));
			pst.setLong(1, (long) map.get("numeroFactura"));
			rs = pst.executeQuery();
			rs.next();
			mapReturn.put("idClave",rs.getLong(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}
		return mapReturn;
	}

	@Override
	public Map<String, Object> ultimoNumeroFactura() {
		PreparedStatement pst = null;
		Map<String,Object> map = new HashMap<>();
		try {
			pst = con.prepareStatement(Conf.get("SQL_ULTIMO_NUMERO_FACTURA"));
			rs = pst.executeQuery();
			if (rs.next()) {
				map.put("number",rs.getLong(1) + 1); // +1, el siguiente
			} else { // todavía no hay ninguna
				map.put("number", 1L);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(rs,pst);
		}
		return map;
	}

}
