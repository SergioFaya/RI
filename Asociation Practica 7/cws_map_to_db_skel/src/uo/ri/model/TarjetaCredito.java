package uo.ri.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TarjetaCredito extends MedioPago {

	@Column(unique = true)private String numero;
	private String tipo;
	private Date validez;
	
	TarjetaCredito() {
		
	}
	public TarjetaCredito(String numero) {
		super();
		this.numero = numero;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Date getValidez() {
		return (Date) validez.clone();
	}


	public void setValidez(Date validez) {
		this.validez = validez;
	}


	public String getNumero() {
		return numero;
	}


	@Override
	public String toString() {
		return "TarjetaCredito [numero=" + numero + ", tipo=" + tipo + ", validez=" + validez + "]";
	}
	
	
	
}
