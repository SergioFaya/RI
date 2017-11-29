package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MedioPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne private Cliente cliente;
	@Column(unique = false)
	protected double acumulado = 0.0;

	@OneToMany
	protected Set<Cargo> cargos = new HashSet<>();

	Set<Cargo> _getCargos() {
		return cargos;
	}

	public Long getId() {
		return id;
	}

	public Set<Cargo> getCargos() {
		return new HashSet<>(cargos);
	}

	public Cliente getCliente() {
		return cliente;
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public double getAcumulado() {
		return acumulado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		MedioPago other = (MedioPago) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}

}
