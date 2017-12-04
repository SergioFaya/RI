package uo.ri.ui.foreman.action.client;

import java.util.List;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;

public class FindRecommendedClientsAction implements Action {

	@Override
	public void execute() throws Exception {
		long idRecomendador = Console.readLong("Id");
		ForemanService service = ServicesFactory.getForemanService();
		List<Map<String,Object>> list = service.findAllRecommendedClients(idRecomendador);
		for (Map<String, Object> map : list) {
			Printer.mostrarCliente(
			ServicesFactory.getForemanService().seeClient((Long) map.get("idRecommender")));
		}
		
	}

}
