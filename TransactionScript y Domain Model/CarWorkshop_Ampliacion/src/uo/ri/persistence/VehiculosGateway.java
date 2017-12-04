package uo.ri.persistence;

import java.util.List;
import java.util.Map;


public interface VehiculosGateway extends Gateway{
	List<Map<String,Object>> selectVehiculosRegistrados(Map<String, Object> map);
	void deleteVehicle(Map<String,Object> map);
}
