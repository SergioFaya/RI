package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientsGateway;

public class FindRecommendedClients {

	public List<Map<String, Object>> execute(Long id) throws BusinessException {
		List<Map<String, Object>> list = new ArrayList<>();
		Connection con = null;
		try {
			Map<String,Object> map = new  HashMap<>();
			con = Jdbc.getConnection();
			ClientsGateway gate = PersistenceFactory.getClientGateway();
			gate.setConnection(con);
			map.put("idRecommender", id);
			list = gate.getClientsOfRecommender(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(con);
		}
		return list;
	}

}
