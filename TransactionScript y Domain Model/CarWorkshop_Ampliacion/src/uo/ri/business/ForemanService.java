package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface ForemanService {
	void addClient(Map<String, Object> map) throws BusinessException;
	boolean removeClient(Long id) throws BusinessException;
	void modifyClient(Map<String,Object> map) throws BusinessException;
	List<Map<String,Object>> findAllClients() throws BusinessException;
	Map<String,Object> seeClient(Long id) throws BusinessException;
	boolean addRecommender(Long idRecommender) throws BusinessException;
	List<Map<String, Object>> findAllRecommendedClients(Long id) throws BusinessException;
}
