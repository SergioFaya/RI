
package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "AVERIA_ID,MECANICO_ID") })
public class Intervencion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne private Averia averia;
	@ManyToOne private Mecanico mecanico;
	@Column(unique = false)private int minutos;
	@OneToMany(mappedBy = "intervencion") private Set<Sustitucion> sustituciones = new HashSet<>();

	Intervencion(){
		
	}
	
	Long getId() {
		return id;
	}

	public Intervencion(Mecanico mecanico, Averia averia) {
		Association.Intervenir.link(mecanico, this, averia);
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

	public Set<Sustitucion> getSustituciones() {
		return new HashSet<>(sustituciones);
	}

	public double getImporte() {
		double acumulatedImport = 0;
		double pricePerHour = averia.getVehiculo().getTipo().getPrecioHora();
		double hours = (double) minutos / 60.0;
		for (Sustitucion sustitucion : sustituciones) {
			acumulatedImport += sustitucion.getImporte();
		}
		return acumulatedImport + pricePerHour * hours;
	}
}
