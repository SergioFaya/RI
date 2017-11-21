package uo.ri.model;

public class Sustitucion {
	
	private Repuesto repuesto;
	private Intervencion intervencion;
	private int cantidad;
	
	public Sustitucion(Repuesto repuesto,Intervencion intervencion) {
		super();
		Association.Sustituir.link(intervencion,this,repuesto);
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
		
	}


	

}
