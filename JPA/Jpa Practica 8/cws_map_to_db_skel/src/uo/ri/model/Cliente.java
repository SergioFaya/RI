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

import uo.ri.model.types.Address;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; //si no pongo parametro el mapeador lo especifica como el quiera
	@Column(unique = true)private String dni;
	private String nombre;
	private String apellidos;
	private Address address;

	@OneToMany(mappedBy = "cliente")
	private Set<Vehiculo> vehiculos = new HashSet<>();
	@ManyToOne private Set<MedioPago> mediosPago = new HashSet<>();

	public Cliente(String dni) {
		super();
		this.dni = dni;
	}

	public Cliente(String dni, String nombre, String apellidos) {
		this(dni);
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	Cliente() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	Set<MedioPago> _getMediosPago() {
		return mediosPago;
	}

	public Set<MedioPago> getMediosPago() {
		return new HashSet<>(mediosPago);
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", address=" + address + "]";
	}

	Set<Vehiculo> _getVehiculos() {
		return vehiculos;
	}

	public Set<Vehiculo> getVehiculos() {
		return new HashSet<>(vehiculos);
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
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDni() {
		return dni;
	}

}
