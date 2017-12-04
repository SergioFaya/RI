package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientsGateway;

public class AddRecommender {

	private long idRecommender;
	
	public AddRecommender(Long idRecommender) {
		this.idRecommender = idRecommender;
	}

	public boolean execute() throws BusinessException {
		Connection con = null;
		Map<String,Object> map = new HashMap<>();
		try {
			con = Jdbc.getConnection();
			map.put("id", idRecommender);
			
			ClientsGateway gate = PersistenceFactory.getClientGateway();
			gate.setConnection(con);
			if((boolean) gate.exists(map).get("exists")){
				if(gate.facturaPagada(map).get("status").equals("ABONADA")){
					return true;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			Jdbc.close(con);
		}
		return false;
		
	}

}
