package uo.ri.ui.admin.action;

import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class CreateBondsForClientsAction implements Action{

	@Override
	public void execute() throws BusinessException { 
		AdminService service = ServicesFactory.getAdminService();
		service.createBonds();
		System.out.println("BONOS GENERADOS CORRECTAMENTE");
	}
	
}
