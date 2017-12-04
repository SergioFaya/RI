package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.impl.MechanicGatewayImpl;

public class UpdateMechanic {

	private long id;
	private String nombre;
	private String apellidos;

	public UpdateMechanic(long id, String nombre, String apellidos) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() {
		// Procesar
		Connection c = null;

		try {
			c = Jdbc.getConnection();

			Map<String, Object> map = new HashMap<>();
			map.put("nombre", nombre);
			map.put("apellidos", apellidos);
			map.put("id", id);

			MechanicGateway gate = new MechanicGatewayImpl();
			gate.setConnection(c);
			gate.updateMechanic(map);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
