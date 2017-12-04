package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface MechanicGateway extends Gateway{
	
	@Override
	void setConnection(Connection con);
	
	void addMechanic(Map<String, Object> map) throws BusinessException;
	
	void deleteMechanic(Map<String,Object> map) throws BusinessException;

	List<Map<String,Object>> findAllMechanics() throws BusinessException;
	
	void updateMechanic(Map<String,Object> map) throws BusinessException;	
}
