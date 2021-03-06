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

	public Repuesto getRepuesto() {
		return repuesto;
	}

	void _setRepuesto(Repuesto repuesto) {
		this.repuesto = repuesto;
	}

	public Intervencion getIntervencion() {
		return intervencion;
	}

	void _setIntervencion(Intervencion intervencion) {
		this.intervencion = intervencion;
	}

	public int getCantidad() {
		return cantidad;
	}

	public double getImporte() {
		return repuesto.getPrecio() * cantidad;
	}

	
	

}
