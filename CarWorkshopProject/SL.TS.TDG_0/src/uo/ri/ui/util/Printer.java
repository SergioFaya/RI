package uo.ri.ui.util;

import java.util.Map;

import alb.util.console.Console;

public class Printer {

	public static void printInvoice(Map<String,Object> map) {
		Console.printf("Factura nº: %d\n", map.get("numeroFactura"));
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", map.get("fechaFactura"));
		Console.printf("\tTotal: %.2f €\n", map.get("totalFactura"));
		Console.printf("\tIva: %.1f %% \n", map.get("iva"));
		Console.printf("\tTotal con IVA: %.2f €\n", map.get("totalConIva"));
	}
}
