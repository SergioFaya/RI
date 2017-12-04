package uo.ri.persistence;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ClientsGateway extends Gateway{

	Map<String,Object> getAllClients() throws BusinessException;
	@Override
	void setConnection (Connection con);
	
	void addClient(Map<String, Object> map)throws BusinessException;
	
	void removeClient(Map<String,Object> map)throws BusinessException;
	
	void updateClient(Map<String,Object> map)throws BusinessException;
	
	Map<String, Object> getInfo(Map<String,Object> map) throws BusinessException;
	
	Map<String, Object> getLatestClient()throws BusinessException;
	
	Map<String, Object> exists(Map<String, Object> map)throws BusinessException;
	Map<String, Object> facturaPagada(Map<String, Object> map)throws BusinessException;
	List<Map<String, Object>> getClientsOfRecommender(Map<String, Object> map) throws BusinessException;
	
}
