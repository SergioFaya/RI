package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import uo.ri.model.types.AveriaStatus;

public class Averia {

	private String descripcion;
	private Date fecha;
	private double importe = 0.0;
	private AveriaStatus status = AveriaStatus.ABIERTA;
	private Vehiculo vehiculo;	
	private Mecanico mecanico;
	private Set<Intervencion> intervenciones = new HashSet<>();
	
	public Averia(Vehiculo vehiculo) {
		super();
		this.fecha = new Date();
		Association.Averiar.link(vehiculo,this);
		
	}

	

	public Set<Intervencion> getIntervenciones() {
		return new HashSet(intervenciones);
	}

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Averia(Vehiculo vehiculo, String descripcion) {
		this(vehiculo);
		this.descripcion = descripcion;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Vehiculo getVehiculo() {
		return vehiculo;
	}


	void _setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}


	public Date getFecha() {
		return fecha;
	}


	public double getImporte() {
		return importe;
	}


	public AveriaStatus getStatus() {
		return status;
	}


	/**
	 * Asigna la averia al mecanico
	 * @param mecanico
	 */
	public void assignTo(Mecanico mecanico) {
		// Solo se puede asignar una averia que está ABIERTA
		// linkado de averia y mecanico
		// la averia pasa a ASIGNADA
	}


	/**
	 * El mecánico da por finalizada esta avería, entonces se calcula el 
	 * importe
	 * 
	 */
	public void markAsFinished() {
		// Se verifica que está en estado ASIGNADA
		// se calcula el importe
		// se desvincula mecanico y averia
		// el status cambia a TERMINADA
	}


	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico
	 * (el primero no ha podido terminar la reparación), pero debe ser pasada 
	 * a ABIERTA primero
	 */
	public void reopen() {
		// Solo se puede reabrir una averia que está TERMINADA
		// la averia pasa a ABIERTA
	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 */
	public void markBackToFinished() {
		// verificar que la averia está FACTURADA
		// cambiar status a TERMINADA
	}


	public Mecanico getMecanico() {
		return mecanico;
	}


	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}
}
