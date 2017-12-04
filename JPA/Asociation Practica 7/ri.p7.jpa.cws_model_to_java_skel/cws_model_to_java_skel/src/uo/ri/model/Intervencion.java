package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class Intervencion {
	
	
	private Averia averia;
	private Mecanico mecanico;
	private int minutos;
	private Set<Sustitucion> sustituciones = new HashSet<>();
		
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

	Set<Sustitucion> _getSustituciones() {
		return sustituciones;
	}
	
	public Set<Sustitucion> getSustituciones(){
		return new HashSet<>(sustituciones);
	}

	public double getImporte() {
		double acumulatedImport = 0;
		double pricePerHour = averia.getVehiculo().getTipo().getPrecioHora();
		double hours = (double) minutos / 60.0;
		for (Sustitucion sustitucion : sustituciones) {
			acumulatedImport+= sustitucion.getImporte();
		}
		return acumulatedImport+ pricePerHour * hours;		
	}
}
