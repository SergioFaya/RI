package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.persistence.MediosPagoGateway;

public class MediosPagoGatewayImpl implements MediosPagoGateway {

	private Connection con;
	private PreparedStatement pst;
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public void createBond(Map<String, Object> map) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_INSERT_MEDIOPAGO"));
			pst.setString(1, "TBonos");
			pst.setLong(2, (long) map.get("id"));
			pst.setDouble(3, (double) map.get("value"));
			pst.setLong(4, (long) map.get("clientId"));
			pst.setString(5,(String) map.get("concept"));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void createMetalico(Map<String, Object> map) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_INSERT_MEDIOPAGO"));
			pst.setString(1, "TMetalico");
			pst.setLong(2, (long) map.get("id"));
			pst.setDouble(3, 0);
			pst.setLong(4, (long) map.get("clientId"));
			pst.setString(5, "" );
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(pst);
		}
	}
	
	@Override
	public Map<String,Object> selectLatestId() {
		ResultSet rs = null;
		Map<String,Object> map = new HashMap<>();
		
		try {
			pst = con.prepareStatement(Conf.get("SQL_LATEST_NUM_MEDIOPAGO"));
			rs = pst.executeQuery();
			if (rs.next()) {
				map.put("id",rs.getLong(1) + 1);
			} else { 
				map.put("id", 1L);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(rs,pst);
		}
		return map;
	}

	@Override
	public void deleteMedioPagoCliente(Map<String, Object> map) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_DELETE_MEDIOSPAGO_USER"));
			pst.setLong(1, (long) map.get("id"));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(pst);
		}
	}

}
