package uo.ri.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface FacturaGateway {
	
	void insertarFactura(PreparedStatement pst);

	void recuperarClaveFacturaGenerada(PreparedStatement pst);

	void ultimoNumeroFactura(PreparedStatement pst);

	void setConnection(Connection con);

	void setResultSet(ResultSet rs);
}
