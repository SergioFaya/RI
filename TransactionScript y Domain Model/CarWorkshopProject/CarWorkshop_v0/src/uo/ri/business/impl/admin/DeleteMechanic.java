package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class DeleteMechanic {
	
	private long idMecanico;
	
	public DeleteMechanic(long idMecanico) {
		this.idMecanico = idMecanico;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		//ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(Conf.get("SQL_DELETE_FROM_MECANICOS"));
			pst.setLong(1, idMecanico);
			MechanicGateway gate = PersistenceFactory.getMechanicGateway();
			gate.setConection(c);
			gate.deleteMechanic(pst);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		Console.println("Se ha eliminado el mecánico");

	}
}
