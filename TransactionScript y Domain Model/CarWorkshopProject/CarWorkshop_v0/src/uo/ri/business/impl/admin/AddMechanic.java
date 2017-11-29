package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class AddMechanic {

	private String nombre;
	private String apellidos;

	public AddMechanic(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() {
		// Procesar
		Connection c = null;
		PreparedStatement pst = null;
		//ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_INSERT_INTO_MECANICOS"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			
			MechanicGateway gate = PersistenceFactory.getMechanicGateway(); 
			gate.setConnection(c);
			gate.addMechanic(pst);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");

	}

}
