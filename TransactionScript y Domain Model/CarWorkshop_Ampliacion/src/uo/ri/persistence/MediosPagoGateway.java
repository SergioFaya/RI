package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

public interface MediosPagoGateway extends Gateway{

	@Override
	void setConnection(Connection con);
	
	void createBond(Map<String,Object> map);

	Map<String,Object> selectLatestId();

	void createMetalico(Map<String, Object> map);

	void deleteMedioPagoCliente(Map<String, Object> map);
	
}
