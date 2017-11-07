package uo.ri.ui.admin.action;

import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import business.admin.FindAllMechanics;
import uo.ri.common.BusinessException;

public class ListMechanicsAction implements Action {

	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");  
		for (Map<String, Object> map: new FindAllMechanics().execute()) {
			System.out.println(map.get("id")+ ","+ map.get("nombre")+ ","+map.get("apellidos"));
		}
	}
}
