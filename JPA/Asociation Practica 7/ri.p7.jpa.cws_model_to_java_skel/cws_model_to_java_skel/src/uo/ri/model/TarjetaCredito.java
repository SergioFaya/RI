package uo.ri.model;

import java.util.Date;

public class TarjetaCredito extends MedioPago {

	private String numero;
	private String tipo;
	private Date validez;
	
	
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
