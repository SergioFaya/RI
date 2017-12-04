package uo.ri.model;

import java.util.Set;

public class Metalico extends MedioPago {

	
	public Metalico(Cliente cliente) {
		Association.Pagar.link(cliente,this);
	}

	
	
	
}
