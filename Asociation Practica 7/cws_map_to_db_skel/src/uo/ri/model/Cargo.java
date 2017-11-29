package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import uo.ri.model.exception.BusinessException;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "MEDIOPAGO_ID,FACTURA_ID") })
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Factura factura;
	@ManyToOne
	private MedioPago medioPago;
	private double importe = 0.0;

	Cargo() {

	}

	Long getId() {
		return id;
	}

	public Cargo(Factura factura, MedioPago medioPago, double importe) throws BusinessException {
		// incrementar el importe en el acumulado del medio de pago
		medioPago.acumulado += importe;
		// guardar el importe
		this.importe = importe;
		// enlazar (link) factura, este cargo y medioDePago
		Association.Cargar.link(factura, this, medioPago);
	}

	/**
	 * Anula (retrocede) este cargo de la factura y el medio de pago Solo se
	 * puede hacer si la factura no está abonada Decrementar el acumulado del
	 * medio de pago Desenlazar el cargo de la factura y el medio de pago
	 * 
	 * @throws BusinessException
	 */
	public void rewind() throws BusinessException {
		// verificar que la factura no esta ABONADA
		// decrementar acumulado en medio de pago
		// desenlazar factura, cargo y edio de pago
	}

	void _setFactura(Factura factura) {
		this.factura = factura;
	}

	void _setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}

	public Factura getFactura() {
		return factura;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public double getImporte() {
		return importe;
	}

}
