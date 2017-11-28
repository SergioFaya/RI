package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.properties.Queries;

public class FindAllMechanics {
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(Queries.SELECT_ALL_FROM_MECANICOS);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				
				list.add((Map<String, Object>)new HashMap<String,Object>().put("id", rs.getLong(1)));
				list.add((Map<String, Object>)new HashMap<String,Object>().put("nombre", rs.getString(2)));
				list.add((Map<String, Object>)new HashMap<String,Object>().put("apellidos", rs.getString(3)));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	
		
		return list;
	}
}
