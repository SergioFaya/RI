package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.CreateBondsForClients;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;

public class AdminServiceImpl implements AdminService {
	
	@Override
	public void newMechanic(String nombre, String apellidos) throws BusinessException {
		AddMechanic addMechanic = new AddMechanic(nombre, apellidos);
		addMechanic.execute();
	}

	@Override
	public void deleteMechanic(Long id) throws BusinessException {
		DeleteMechanic deleteMechanic = new DeleteMechanic(id);
		deleteMechanic.execute();
		
	}

	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) throws BusinessException {
		UpdateMechanic updateMechanic = new UpdateMechanic(id, nombre, apellidos);
		updateMechanic.execute();
		
	}

	@Override
	public List<Map<String, Object>> findAllMechanics() throws BusinessException {
		FindAllMechanics findAllMechanics = new FindAllMechanics();
		return findAllMechanics.execute();
	}

	@Override
	public void createBonds() throws BusinessException {
		CreateBondsForClients bonds = new CreateBondsForClients();
		bonds.execute();
		
	}

}
