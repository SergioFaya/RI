package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.RepuestoGateway;

public class RepuestoGatewayImpl implements RepuestoGateway {

	private Connection con;
	private ResultSet rs;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public Map<String, Object> selectImporteRepuestos(Map<String, Object> map) {
		PreparedStatement pst = null;
		Map<String, Object> mapReturn = new HashMap<>();
		try {
			pst = con.prepareStatement(Conf.get("SQL_IMPORTE_REPUESTOS"));
			pst.setLong(1, (long) map.get("idAveria"));
			rs = pst.executeQuery();
			if (rs.next() == false) {
				mapReturn.put("importe", 0.0);
			}
			mapReturn.put("importe", rs.getDouble(1));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}
		return mapReturn;
	}

}
