package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.properties.Queries;

public class DeleteMechanic {
	
	private long idMecanico;
	
	public DeleteMechanic(long idMecanico) {
		this.idMecanico = idMecanico;
	}
	
	public void execute() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(Queries.SQL_DELETE_FROM_MECANICOS);
			pst.setLong(1, idMecanico);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		Console.println("Se ha eliminado el mecánico");

	}
}
