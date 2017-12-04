package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class DeleteMechanic {

	private long idMecanico;

	public DeleteMechanic(long idMecanico) {
		this.idMecanico = idMecanico;
	}

	public void execute() throws BusinessException {
		Connection c = null;
		try {
			c = Jdbc.getConnection();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idMecanico", idMecanico);

			MechanicGateway gate = PersistenceFactory.getMechanicGateway();
			gate.setConnection(c);
			gate.deleteMechanic(map);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
		Console.println("Se ha eliminado el mecánico");

	}
}
