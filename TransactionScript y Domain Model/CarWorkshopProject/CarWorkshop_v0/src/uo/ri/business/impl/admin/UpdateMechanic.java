package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.conf.Conf;
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
		PreparedStatement pst = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement(Conf.get("SQL_UPDATE_MECANICOS"));
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);

			MechanicGateway gate = new MechanicGatewayImpl();
			gate.setConnection(c);
			gate.updateMechanic(pst);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
}
