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
import uo.ri.conf.Conf;
import uo.ri.persistence.MechanicGateway;

public class MechanicGatewayImpl implements MechanicGateway {

	private Connection con;
	private ResultSet rs = null;
	private PreparedStatement pst = null;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public void deleteMechanic(Map<String, Object> map) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_DELETE_FROM_MECANICOS"));
			pst.setLong(1, (long) map.get("idMecanico"));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void addMechanic(Map<String, Object> map) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_INSERT_INTO_MECANICOS"));
			pst.setString(1, (String) map.get("nombre"));
			pst.setString(2, (String) map.get("apellidos"));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() {
		List<Map<String, Object>> list = new ArrayList<>();
		try {
			pst = con.prepareStatement(Conf.get("SELECT_ALL_FROM_MECANICOS"));
			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", rs.getLong(1));
				map.put("nombre", rs.getString(2));
				map.put("apellidos", rs.getString(3));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}
		return list;
	}

	@Override
	public void updateMechanic(Map<String, Object> map) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_UPDATE_MECANICOS"));
			pst.setString(1, (String) map.get("nombre"));
			pst.setString(2, (String) map.get("apellidos"));
			pst.setLong(3, (long) map.get("id"));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(pst);
		}
	}

}
