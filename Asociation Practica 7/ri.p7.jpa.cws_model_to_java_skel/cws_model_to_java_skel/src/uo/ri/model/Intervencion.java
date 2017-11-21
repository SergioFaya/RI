package uo.ri.model;

public class Intervencion {
	
	
	private Averia averia;
	private Mecanico mecanico;
	private int minutos;
	

		
	public Intervencion(Mecanico mecanico, Averia averia) {
		Association.Intervenir.link(mecanico,this,averia);
	}

	void _setAveria(Averia averia) {
		this.averia = averia;
	}
	
	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
		
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
		
	}

	public Averia getAveria() {
		return averia;
	}
}
