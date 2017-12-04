package uo.ri.ui.foreman.action.client;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.conf.ServicesFactory;

public class RemoveClientAction implements Action{

	@Override
	public void execute() throws Exception {
		long id = Console.readLong("ID");
		ForemanService service = ServicesFactory.getForemanService();
		if(service.removeClient(id)){
			System.out.println("El cliente se ha eliminado correctamente");
		}else{
			System.out.println("El cliente no se ha podido eliminar tiene vehiculos asignados");
		}
		
	}

}
