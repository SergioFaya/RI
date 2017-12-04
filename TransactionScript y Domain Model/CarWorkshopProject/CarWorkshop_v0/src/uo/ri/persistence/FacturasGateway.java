package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

public interface FacturasGateway {
	
	void insertarFactura(Map<String,Object> map);

	Map<String,Object> recuperarClaveFacturaGenerada(Map<String,Object> map);

	Map<String, Object> ultimoNumeroFactura();

	void setConnection(Connection con);
}
