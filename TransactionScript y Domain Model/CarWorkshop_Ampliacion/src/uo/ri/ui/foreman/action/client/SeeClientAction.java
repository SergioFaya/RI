package uo.ri.ui.foreman.action.client;

import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;
public class SeeClientAction implements Action{

	@Override
	public void execute() throws Exception {
		long id = Console.readLong("id");
		ForemanService service = ServicesFactory.getForemanService();
		Map<String,Object> map =service.seeClient(id);
		Printer.mostrarCliente(map);
		
	}

}