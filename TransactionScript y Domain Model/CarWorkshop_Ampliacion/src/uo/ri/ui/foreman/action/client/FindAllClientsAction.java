package uo.ri.ui.foreman.action.client;

import java.util.List;
import java.util.Map;

import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;

public class FindAllClientsAction implements Action{

	@Override
	public void execute() throws Exception {
		ForemanService service = ServicesFactory.getForemanService();
		System.out.println("Esta accion tardará unos segundos en ejecutarse");
		List<Map<String,Object>> list = service.findAllClients();
		for (Map<String, Object> map : list) {
			Printer.mostrarCliente(map);
		}
	}

}
