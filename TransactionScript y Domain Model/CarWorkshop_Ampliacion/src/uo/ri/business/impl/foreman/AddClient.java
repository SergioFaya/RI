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

public class AddClient {

	private Map<String, Object> map;
	Connection con = null;

	public AddClient(Map<String, Object> map) {
		this.map = map;
	}

	public void execute() throws BusinessException {
		try {
			con = Jdbc.getConnection();
			insertClient();
			insertMetalico();
			con.commit();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(con);
		}
	}

	private void insertClient() throws BusinessException {
		ClientsGateway gate = PersistenceFactory.getClientGateway();
		gate.setConnection(con);
		map.put("clientId", getId().get("clientId"));
		gate.addClient(map);
	}

	private void insertMetalico() {
		MediosPagoGateway gate = PersistenceFactory.getMediosPagoGateway();
		gate.setConnection(con);
		map.put("id", getIdMetalico().get("id"));
		gate.createMetalico(map);

	}

	private Map<String, Object> getId() throws BusinessException {
		Map<String, Object> map = new HashMap<>();

		ClientsGateway gate = PersistenceFactory.getClientGateway();
		gate.setConnection(con);
		map = gate.getLatestClient();

		return map;
	}

	private Map<String, Object> getIdMetalico() {
		Map<String, Object> map = new HashMap<>();

		MediosPagoGateway gate = PersistenceFactory.getMediosPagoGateway();
		gate.setConnection(con);
		map = gate.selectLatestId();

		return map;
	}
}
