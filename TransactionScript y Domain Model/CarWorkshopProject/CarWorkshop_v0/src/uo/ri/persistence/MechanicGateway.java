package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public interface MechanicGateway {
	
	void setConnection(Connection con);
	
	void addMechanic(Map<String, Object> map);
	
	void deleteMechanic(Map<String,Object> map);

	List<Map<String,Object>> findAllMechanics();
	
	void updateMechanic(Map<String,Object> map);	
}
