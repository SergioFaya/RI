package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.CashService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;

public class FacturarReparacionesAction implements Action {

	
	
	@Override
	public void execute() throws BusinessException {
		List<Long> idsAveria = new ArrayList<Long>();
		
		// pedir las averias a incluir en la factura
		do {
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} while ( masAverias() );
		CashService cashService =ServicesFactory.getCashService();
		HashMap<String, Object> map = (HashMap<String, Object>) cashService.createInvoiceFor(idsAveria);
		uo.ri.ui.util.Printer.mostrarFactura((long)map.get("numeroFactura"), (Date) map.get("fechaFactura"),
				(double)map.get("totalFactura"), (double)map.get("iva"), (double)map.get("importe"));
	}

	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

}
