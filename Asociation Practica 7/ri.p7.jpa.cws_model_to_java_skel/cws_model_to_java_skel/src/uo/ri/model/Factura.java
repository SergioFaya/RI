package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import alb.util.date.DateUtil;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import alb.util.math.*;

public class Factura {

	private Long numero;
	private Date fecha;
	private double importe;
	private double iva;
	private FacturaStatus status = FacturaStatus.SIN_ABONAR;
	private Set<Cargo> cargos = new HashSet<>();
	private Set<Averia> averias = new HashSet<>();

	public Factura(Long numero) {
		super();
		this.numero = numero;
		this.fecha = new Date();
		this.iva = 0.21;
	}

	public Factura(long l, Date today) {
		this(l);
		this.fecha = today;
	}

	public Factura(long l,List<Averia> averias) throws BusinessException {
		this(l);
		for (Averia averia : averias) {
			if(!averia.getStatus().equals(AveriaStatus.TERMINADA)){
				throw new BusinessException();
			}else{
				addAveria(averia);
			}
		}	
	}

	public Factura(long l, Date date, List<Averia> averias) throws BusinessException {
		this(l,date);
		for (Averia averia : averias) {
			if(!averia.getStatus().equals(AveriaStatus.TERMINADA)){
				throw new BusinessException();
			}else{
				addAveria(averia);
			}
		}	
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		Factura other = (Factura) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}

	Set<Cargo> _getCargos() {
		return cargos;
	}

	public Set<Cargo> getCargos() {
		return new HashSet<>(cargos);
	}

	/**
	 * Añade la averia a la factura
	 * 
	 * @param averia
	 */
	public void addAveria(Averia averia) {
		// Verificar que la factura está en estado SIN_ABONAR
		// Verificar que La averia está TERMINADA
		// linkar factura y averia
		// marcar la averia como FACTURADA ( averia.markAsInvoiced() )
		// calcular el importe
		if (status.equals(FacturaStatus.SIN_ABONAR)) {
			if (averia.getStatus().equals(AveriaStatus.TERMINADA)) {
				Association.Facturar.link(this, averia);
				averia.markAsInvoiced();
				calcularImporte();
			}
		}
	}

	/**
	 * Calcula el importe de la avería y su IVA, teniendo en cuenta la fecha de
	 * factura
	 */
	void calcularImporte() {
		double accumulatedImport = 0;
		if(fecha.before(DateUtil.fromDdMmYyyy(01, 07, 2012))){
			iva = 0.18;
		}
		for (Averia averia : averias) {
			accumulatedImport +=  averia.getImporte();
		}
		importe = Round.twoCents(accumulatedImport * iva + accumulatedImport);
		//factura es anterior al 1/7/2012 el IVA 
		//es el 18%, * el importe es 250€ + IVA 18%
		
	}

	/**
	 * Elimina una averia de la factura, solo si está SIN_ABONAR y recalcula el
	 * importe
	 * 
	 * @param averia
	 */
	public void removeAveria(Averia averia) {
		// verificar que la factura está sin abonar
		// desenlazar factura y averia
		// la averia vuelve al estado FINALIZADA ( averia.markBackToFinished() )
		// TODO Auto-generated method stub
		// volver a calcular el importe
		if (status.equals(FacturaStatus.SIN_ABONAR)) {
			Association.Facturar.unlink(this, averia);
			averia.markBackToFinished();
			calcularImporte();
		}
	}

	public Set<Averia> getAverias() {
		return new HashSet<>(averias);
	}

	Set<Averia> _getAverias() {
		return averias;
	}

	public double getImporte() {
		return importe;
	}
		
	public FacturaStatus getStatus() {
		return status;
	}

}
