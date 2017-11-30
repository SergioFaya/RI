package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
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
		try {
			c = Jdbc.getConnection();
			
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("nombre", nombre);
			map.put("apellidos", apellidos);
			
			MechanicGateway gate = PersistenceFactory.getMechanicGateway();
			gate.setConnection(c);
			gate.addMechanic(map);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");

	}

}
