package uo.ri.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "REPUESTO_ID,INTERVENCION_ID") })
public class Sustitucion {
	@ManyToOne
	private Repuesto repuesto;
	@ManyToOne
	private Intervencion intervencion;
	@Column(unique = false)private int cantidad;
	
	Sustitucion() {
		// TODO Auto-generated constructor stub
	}
	
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
