package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

public interface RepuestoGateway extends Gateway{
	
	Map<String,Object> selectImporteRepuestos(Map<String,Object> map);

	@Override
	void setConnection(Connection con);
}
