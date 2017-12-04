package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.AveriaGateway;
import uo.ri.persistence.ClientsGateway;
import uo.ri.persistence.MediosPagoGateway;

public class CreateBondsForClients {

	private Connection con = null;

	private void prepareDB() throws SQLException {
		con = Jdbc.getConnection();
		con.setAutoCommit(false);
	}

	public void execute() throws BusinessException {
		try {
			prepareDB();
			List<Long> clientIds = getAllClientIds();
			int counter = 0;
			for (Long id : clientIds) {
				for (Long averia : selectAveriasOfClient(id)) {
					if(counter == 3){
						createBond(id, "Por tres averías", 20);
						updateUsadaBono(averia);
						counter = 0;
					}
					counter++;
				}
				counter = 0;
			}
			con.commit();
		} catch (

		SQLException e) {
			System.out.println("EXCEPTION");
			try {
				con.rollback();
			} catch (SQLException ex) {
			}
			throw new RuntimeException(e);
		} catch (BusinessException e) {
			try {
				System.out.println("EXCEPTION");
				con.rollback();
			} catch (SQLException ex) {
			}
			throw e;
		} finally {
			Jdbc.close(con);
		}
	}

	private void updateUsadaBono(Long averia) throws BusinessException {
		Map<String,Object> map = new HashMap<>();
		map.put("id", averia);
		AveriaGateway gate = PersistenceFactory.getAveriasGateway();
		gate.setConnection(con);
		gate.updateUsadaBono(map);
	}

	private void createBond(long clientId, String concept, double price) {
		Map<String, Object> map = new HashMap<>();
		long id = latestIdMediosPago();
		map.put("id", id);
		map.put("value", price);
		map.put("clientId", clientId);
		map.put("concept", concept);
		MediosPagoGateway gate = PersistenceFactory.getMediosPagoGateway();
		gate.setConnection(con);
		gate.createBond(map);
	}

	private long latestIdMediosPago() {
		MediosPagoGateway gate = PersistenceFactory.getMediosPagoGateway();
		gate.setConnection(con);
		return (long) gate.selectLatestId().get("id");
	}

	private List<Long> getAllClientIds() throws BusinessException {
		ClientsGateway gate = PersistenceFactory.getClientGateway();
		gate.setConnection(con);
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) gate.getAllClients().get("clients");
		return list;
	}

	@SuppressWarnings("unchecked")
	private List<Long> selectAveriasOfClient(long id) throws BusinessException {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		AveriaGateway gate = PersistenceFactory.getAveriasGateway();
		gate.setConnection(con);
		return (List<Long>) gate.selectAveriasOfClient(map).get("averias");
	}

}
