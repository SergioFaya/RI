package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

public interface FacturasGateway extends Gateway{
	
	void insertarFactura(Map<String,Object> map);

	Map<String,Object> recuperarClaveFacturaGenerada(Map<String,Object> map);

	Map<String, Object> ultimoNumeroFactura();

	@Override
	void setConnection(Connection con);
}
