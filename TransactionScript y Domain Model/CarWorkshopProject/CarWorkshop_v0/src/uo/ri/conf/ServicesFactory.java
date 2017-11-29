package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;

public class ServicesFactory {

	
	
	//MechanicService mechanicServise = new MechanicServiceImpl();
	//ForemanService foremanService = new ForemanServiceImpl();

	public static AdminService getAdminService() {
		return new AdminServiceImpl();
		
	}

	public static CashService getCashService() {
		return new CashServiceImpl();
		
	}
	
	
	
}
