package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientsGateway;

public class SeeClient {

	private long id;
	public SeeClient(Long id) {
		this.id = id;
	}
	
	public Map<String,Object> execute() throws BusinessException{
		Connection con = null;
		Map<String,Object>	map = new HashMap<>();
		try {
			con = Jdbc.getConnection();
			ClientsGateway gate = PersistenceFactory.getClientGateway();
			gate.setConnection(con);
			map.put("id", id);
			map = gate.getInfo(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(con);
		}
		return map;
	}

}
