package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hsqldb.lib.HashSet;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.CashService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import uo.ri.ui.util.Printer;

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
		mostrarFactura((long)map.get("numeroFactura"), (Date) map.get("fechaFactura"),
				(double)map.get("totalFactura"), (double)map.get("iva"), (double)map.get("importe"));
	}

	private void mostrarFactura(long numeroFactura, Date fechaFactura,
			double totalFactura, double iva, double totalConIva) {
		Map<String,Object> message = new HashMap<String, Object>();
		message.put("numeroFactura", numeroFactura);
		message.put("fechaFactura",fechaFactura);
		message.put("totalFactura", totalFactura);
		message.put("iva", iva);
		message.put("totalConIva", totalConIva);
		Printer.printInvoice(message);
	}


	private boolean masAverias() {
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

}
