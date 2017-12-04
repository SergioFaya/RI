package uo.ri.ui.util;

import java.util.Date;
import java.util.Map;

import alb.util.console.Console;

public class Printer {
	
	public static void mostrarFactura(long numeroFactura, Date fechaFactura,
			double totalFactura, double iva, double totalConIva) {
		
		Console.printf("Factura nº: %d\n", numeroFactura);
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", fechaFactura);
		Console.printf("\tTotal: %.2f €\n", totalFactura);
		Console.printf("\tIva: %.1f %% \n", iva);
		Console.printf("\tTotal con IVA: %.2f €\n", totalConIva);
	}

	public static void mostrarCliente(Map<String, Object> map) {
		String city = (String) map.get("city");
		String street = (String) map.get("street");
		String zipCode = (String) map.get("zipcode");
		String nombre = (String) map.get("nombre");
		String apellidos = (String) map.get("apellidos");
		long telefono = (long) map.get("telefono");
		long id = (long) map.get("id");
		String dni = (String) map.get("dni");
		String email = (String) map.get("email");
		long idRecomender = Long.parseLong((String) map.get("idRecomendador"));
		Console.printf("ID: "+id+"\n");
		Console.printf(apellidos+","+nombre+"\n");
		Console.printf("DNI: "+dni+"\n");
		Console.printf("Email: "+email+"\n");
		Console.printf("Tlfn: "+telefono+"\n");
		Console.printf("Residencia: "+zipCode+","+city+","+street+"\n");
		Console.printf("Recomendado por:"+idRecomender+"\n");
		
		
		
	}

}
