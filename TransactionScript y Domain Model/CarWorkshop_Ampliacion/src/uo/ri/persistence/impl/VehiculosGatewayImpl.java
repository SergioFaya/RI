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
import uo.ri.persistence.VehiculosGateway;

public class VehiculosGatewayImpl implements VehiculosGateway {

	private Connection con;
	private PreparedStatement pst;
	
	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public List<Map<String, Object>> selectVehiculosRegistrados(Map<String, Object> map) {
		List<Map<String, Object>> list = new ArrayList<>();
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(Conf.get("SQL_SELECT_REGISTERED_VEHICLES"));
			pst.setLong(1, (long) map.get("id"));
			rs = pst.executeQuery();
			while(rs.next()){
				Map<String,Object> mapAux = new HashMap<>();
				map.put("id", rs.getLong(1));
				list.add(mapAux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(rs, pst);
		}
		
		return list;
		
	}

	@Override
	public void deleteVehicle(Map<String, Object> map) {
		try {
			pst = con.prepareStatement(Conf.get("SQL_DELETE_VEHICLE"));
			pst.setLong(1, (long) map.get("id"));
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(pst);
		}
	}

}
