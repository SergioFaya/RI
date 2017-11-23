package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;

public class ServicesFactory {

	
	
	//MechanicService mechanicServise = new MechanicServiceImpl();
	//ForemanService foremanService = new ForemanServiceImpl();
	
	public ServicesFactory() {
		// TODO Auto-generated constructor stub
	}

	public static AdminService getAdminService() {
		AdminService adminService = new AdminServiceImpl();
		return adminService;
	}

	public static CashService getCashService() {
		CashService cashService = new CashServiceImpl();
		return cashService;
	}
	
	
	
}
