package uo.ri.ui.foreman.action.client;

import java.util.HashMap;
import java.util.Map;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.business.ForemanService;
import uo.ri.conf.ServicesFactory;

public class UpdateClientAction implements Action{

	@Override
	public void execute() throws Exception {
		long id = Console.readLong("ID");
		String city = Console.readString("Ciudad");
		String street= Console.readString("Calle");
		String zipcode = Console.readString("Codigo postal");
		String nombre = Console.readString("Nombre");
		String apellidos = Console.readString("Apellidos");
		long telefono = Console.readLong("Telefono");
		String dni = Console.readString("DNI");
		String email = Console.readString("Email");
		
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("city", city);
		map.put("street", street);
		map.put("zipcode", zipcode);
		map.put("nombre",nombre);
		map.put("telefono", telefono);
		map.put("apellidos", apellidos);
		map.put("dni", dni);
		map.put("email", email);
		ForemanService service = ServicesFactory.getForemanService();
		service.modifyClient(map);
		
		System.out.println("Cliente actualizado");
	}

}
