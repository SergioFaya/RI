package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Mecanico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column(unique = true)
	private String dni;
	@Column(unique = false)
	private String apellidos;
	@Column(unique = false)
	private String nombre;
	@OneToMany(mappedBy = "mecanico")
	private Set<Averia> asignadas = new HashSet<>();
	@ManyToMany
	private Set<Intervencion> intervenciones = new HashSet<>();

	Mecanico() {
	}

	public Mecanico(String dni) {
		super();
		this.dni = dni;
	}

	public Mecanico(String dni, String nombre, String apellidos) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Set<Intervencion> getIntervenciones() {
		return new HashSet<>(intervenciones);
	}

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Long getId() {
		return id;
	}

	public String getDni() {
		return dni;
	}

	public String getApellidos() {
		return apellidos;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public String toString() {
		return "Mecanico [dni=" + dni + ", apellidos=" + apellidos + ", nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Mecanico other = (Mecanico) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	public Set<Averia> getAsignadas() {
		return new HashSet<>(asignadas);
	}

	Set<Averia> _getAsignadas() {
		return (asignadas);
	}

}
