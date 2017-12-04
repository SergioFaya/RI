package uo.ri.persistence;

import java.sql.Connection;
import java.util.Map;

public interface RepuestoGateway {
	
	Map<String,Object> selectImporteRepuestos(Map<String,Object> map);

	void setConnection(Connection con);
}
