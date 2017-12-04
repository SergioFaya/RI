package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import uo.ri.model.types.AveriaStatus;

@Entity
public class Averia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = false)
	private String descripcion;
	private Date fecha;
	@Column(unique = false)
	private double importe = 0.0;
	@Transient
	private AveriaStatus status = AveriaStatus.ABIERTA;
	@ManyToOne
	private Vehiculo vehiculo;
	@ManyToOne 
	private Mecanico mecanico;

	@ManyToMany
	private Set<Intervencion> intervenciones = new HashSet<>();
	@ManyToOne
	private Factura factura;

	Averia() {

	}

	public Averia(Vehiculo vehiculo) {
		super();
		this.fecha = new Date();
		Association.Averiar.link(vehiculo, this);

	}

	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((vehiculo == null) ? 0 : vehiculo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Averia other = (Averia) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (vehiculo == null) {
			if (other.vehiculo != null)
				return false;
		} else if (!vehiculo.equals(other.vehiculo))
			return false;
		return true;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
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
	 * 
	 * @param mecanico
	 */
	public void assignTo(Mecanico mecanico) {
		// Solo se puede asignar una averia que está ABIERTA
		// linkado de averia y mecanico
		// la averia pasa a ASIGNADA
		if (status.equals(AveriaStatus.ABIERTA)) {
			Association.Asignar.link(mecanico, this);
			status = AveriaStatus.ASIGNADA;
		}

	}

	/**
	 * El mecánico da por finalizada esta avería, entonces se calcula el importe
	 * 
	 */
	public void markAsFinished() {
		// Se verifica que está en estado ASIGNADA
		// se calcula el importe
		// se desvincula mecanico y averia
		// el status cambia a TERMINADA
		if (status.equals(AveriaStatus.ASIGNADA)) {
			importe = getImporteIntervenciones();
			Association.Asignar.unlink(mecanico, this);
			status = AveriaStatus.TERMINADA;
		}
	}

	private double getImporteIntervenciones() {
		double accumulatedImport = 0.0;
		for (Intervencion intervencion : intervenciones) {
			accumulatedImport += intervencion.getImporte();
		}
		return accumulatedImport;
	}

	public void markAsInvoiced() {
		status = AveriaStatus.FACTURADA;

	}

	/**
	 * Una averia en estado TERMINADA se puede asignar a otro mecánico (el
	 * primero no ha podido terminar la reparación), pero debe ser pasada a
	 * ABIERTA primero
	 */
	public void reopen() {
		// Solo se puede reabrir una averia que está TERMINADA
		// la averia pasa a ABIERTA
		if (status.equals(AveriaStatus.TERMINADA)) {
			status = AveriaStatus.ABIERTA;
		}
	}

	/**
	 * Una avería ya facturada se elimina de la factura
	 */
	public void markBackToFinished() {
		// verificar que la averia está FACTURADA
		// cambiar status a TERMINADA
		if (status.equals(AveriaStatus.FACTURADA)) {
			status = AveriaStatus.TERMINADA;
		}
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	void _setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setStatus(AveriaStatus status) {
		this.status = status;

	}
}
