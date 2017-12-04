package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class Vehiculo {

	private String marca;
	private String matricula;
	private String modelo;

	private int numAverias = 0;

	private Cliente cliente;
	private Set<Averia> averias = new HashSet<>();
	private TipoVehiculo tipo;
	
	public Vehiculo(String matricula) {
		super();
		this.matricula = matricula;
	}
	
	public Vehiculo(String matricula, String marca, String modelo) {
		this(matricula);
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	@Override
	public String toString() {
		return "Vehiculo [marca=" + marca + ", matricula=" + matricula + ", modelo=" + modelo + ", numAverias="
				+ numAverias + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Vehiculo other = (Vehiculo) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	void _setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}
	
	public int getNumAverias() {
		this.numAverias = averias.size();
		return numAverias;
	}

	public void setNumAverias(int numAverias) {
		this.numAverias = numAverias;
	}

	public String getMatricula() {
		return matricula;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	Set<Averia> _getAverias() {
		return averias;
	}
	
	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}

	

}
