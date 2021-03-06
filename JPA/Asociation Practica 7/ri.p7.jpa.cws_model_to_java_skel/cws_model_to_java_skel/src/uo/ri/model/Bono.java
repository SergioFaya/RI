package uo.ri.model;

public class Bono extends MedioPago {

	@Override
	public String toString() {
		return "Bono [disponible=" + disponible + ", descripcion=" + descripcion + ", codigo=" + codigo + "]";
	}
	private double disponible = 0.0;
	private String descripcion;
	private String codigo;
	
	public Bono(String codigo) {
		super();
		this.codigo = codigo;
	}

	public double getDisponible() {
		return disponible;
	}

	public void setDisponible(double disponible) {
		this.disponible = disponible;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	
	
}
