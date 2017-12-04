package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import uo.ri.ui.foreman.action.client.AddClientAction;
import uo.ri.ui.foreman.action.client.FindAllClientsAction;
import uo.ri.ui.foreman.action.client.FindRecommendedClientsAction;
import uo.ri.ui.foreman.action.client.RemoveClientAction;
import uo.ri.ui.foreman.action.client.SeeClientAction;
import uo.ri.ui.foreman.action.client.UpdateClientAction;

public class ClientesMenu extends BaseMenu {

	public ClientesMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller > Gestión de Clientes", null },

			{ "Añadir cliente", AddClientAction.class }, 
			{ "Modificar datos de cliente", UpdateClientAction.class }, 
			{ "Eliminar cliente", RemoveClientAction.class }, 
			{ "Listar clientes", FindAllClientsAction.class }, 
			{ "Ver cliente", SeeClientAction.class},
			{ "Clientes recomendados" , FindRecommendedClientsAction.class}
		};
	}

}
