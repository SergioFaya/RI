package business;

import java.util.List;
import java.util.Map;

public interface AdminService {
	void newMechanic(String nombre,String apellidos);
	void deleteMechanic(Long id);
	void updateMechanic(Long id, String nombre, String apellidos);
	List<Map<String,Object>> findAllMechanics();
}
