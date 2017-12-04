package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.ForemanService;
import uo.ri.business.impl.foreman.AddClient;
import uo.ri.business.impl.foreman.AddRecommender;
import uo.ri.business.impl.foreman.FindAllClients;
import uo.ri.business.impl.foreman.FindRecommendedClients;
import uo.ri.business.impl.foreman.RemoveClient;
import uo.ri.business.impl.foreman.SeeClient;
import uo.ri.business.impl.foreman.UpdateClient;
import uo.ri.common.BusinessException;

public class ForemanServiceImpl implements ForemanService {

	@Override
	public void addClient(Map<String,Object> map) throws BusinessException {
		AddClient client = new AddClient(map);
		client.execute();
	}

	@Override
	public boolean removeClient(Long id) throws BusinessException {
		RemoveClient client = new RemoveClient(id);
		return client.execute();
	}

	@Override
	public void modifyClient(Map<String,Object> map) throws BusinessException {
		UpdateClient client = new UpdateClient(map);
		client.execute();
		
	}

	@Override
	public List<Map<String, Object>> findAllClients() throws BusinessException {
		FindAllClients clients = new FindAllClients(); 
		return clients.execute();
	}

	@Override
	public Map<String, Object> seeClient(Long id) throws BusinessException {
		SeeClient client = new SeeClient(id);
		return client.execute();
	}

	@Override
	public boolean addRecommender(Long idRecommender) throws BusinessException {
		AddRecommender recommender = new AddRecommender(idRecommender);
		return recommender.execute();
		
	}

	@Override
	public List<Map<String, Object>> findAllRecommendedClients(Long id) throws BusinessException {
		FindRecommendedClients finder = new FindRecommendedClients();
		return finder.execute(id);
	}


}
