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

public class FindAllClients {

	Connection con = null;

	public List<Map<String, Object>> execute() throws BusinessException {
		List<Map<String, Object>> info = new ArrayList<>();
		List<Long> listIds = getClientsIds();
		for (Long id : listIds) {
			info.add(getInfoOfClient(id));
		}
		return info;
	}
	
	private Map<String,Object> getInfoOfClient(Long id) throws BusinessException{
		Map<String,Object> map = new HashMap<>();
		try {
			con = Jdbc.getConnection();
			ClientsGateway gate = PersistenceFactory.getClientGateway();
			gate.setConnection(con);
			map.put("id", id);
			map = gate.getInfo(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(con);
		}
		return map;
	}
	
	
	@SuppressWarnings("unchecked")
	private List<Long> getClientsIds() throws BusinessException{
		Map<String,Object> map = null;
		try {
			con = Jdbc.getConnection();
			ClientsGateway gate = PersistenceFactory.getClientGateway();
			gate.setConnection(con);
			map = gate.getAllClients();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(con);
		}
		return (List<Long>) map.get("clients");
	}
}
