package uo.ri.ui.admin.action;

import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class ListMechanicsAction implements Action {

	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");  
		AdminService service = ServicesFactory.getAdminService();
		for (Map<String, Object> map: service.findAllMechanics()) {
			System.out.println(map.get("id")+ ","+ map.get("nombre")+ ","+map.get("apellidos"));
		}
	}
}
