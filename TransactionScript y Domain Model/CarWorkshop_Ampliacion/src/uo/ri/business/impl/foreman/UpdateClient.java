package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientsGateway;

public class UpdateClient {
	
	private Map<String, Object> map;
	
	public UpdateClient(Map<String,Object> map) {
		this.map = map;
	}
	
	public void execute() throws BusinessException{
		Connection con = null;
		try {
			con = Jdbc.getConnection();
			ClientsGateway gate = PersistenceFactory.getClientGateway();
			gate.setConnection(con);
			gate.updateClient(map);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally{
			Jdbc.close(con);
		}
	}
}
