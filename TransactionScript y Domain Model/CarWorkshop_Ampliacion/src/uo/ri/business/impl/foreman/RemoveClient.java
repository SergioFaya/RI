package uo.ri.business.impl.foreman;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.ClientsGateway;
import uo.ri.persistence.MediosPagoGateway;
import uo.ri.persistence.VehiculosGateway;

public class RemoveClient {

	private Long id;
	private Connection con = null;

	public RemoveClient(Long id) {
		this.id = id;
	}

	public boolean execute() throws BusinessException {
		try {
			con = Jdbc.getConnection();
			if(comprobarVehiculosRegistrados()){
				eliminarMediosPago();
				eliminarCliente();
				return true;
			}
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(con);
		}
		return false;
	}

	private boolean comprobarVehiculosRegistrados() {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		VehiculosGateway gate = PersistenceFactory.getVehiculosGateway();
		gate.setConnection(con);
		return gate.selectVehiculosRegistrados(map).size() == 0; 
	}
	
	private void eliminarCliente() throws BusinessException{
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		ClientsGateway gate = PersistenceFactory.getClientGateway();
		gate.setConnection(con);
		gate.removeClient(map);
	}
	
	private void eliminarMediosPago(){
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		
		MediosPagoGateway gate = PersistenceFactory.getMediosPagoGateway();
		gate.setConnection(con);
		gate.deleteMedioPagoCliente(map);
	}
	
}
