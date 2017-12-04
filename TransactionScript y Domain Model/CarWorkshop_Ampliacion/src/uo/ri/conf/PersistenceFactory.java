package uo.ri.conf;

import uo.ri.persistence.AveriaGateway;
import uo.ri.persistence.ClientsGateway;
import uo.ri.persistence.FacturasGateway;
import uo.ri.persistence.ManoDeObraGateway;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.MediosPagoGateway;
import uo.ri.persistence.RepuestoGateway;
import uo.ri.persistence.VehiculosGateway;
import uo.ri.persistence.impl.AveriaGatewayImpl;
import uo.ri.persistence.impl.ClientGatewayImpl;
import uo.ri.persistence.impl.FacturaGatewayImpl;
import uo.ri.persistence.impl.ManoDeObraGatewayImpl;
import uo.ri.persistence.impl.MechanicGatewayImpl;
import uo.ri.persistence.impl.MediosPagoGatewayImpl;
import uo.ri.persistence.impl.RepuestoGatewayImpl;
import uo.ri.persistence.impl.VehiculosGatewayImpl;

public class PersistenceFactory {

	public static MechanicGateway getMechanicGateway() {
		return new MechanicGatewayImpl();
	}

	public static AveriaGateway getAveriasGateway() {
		return new AveriaGatewayImpl();
	}

	public static FacturasGateway getFacturaGateway() {
		return new FacturaGatewayImpl();
	}

	public static ManoDeObraGateway getManoDeObraGateway() {
		return new ManoDeObraGatewayImpl();
	}

	public static RepuestoGateway getRepuestoGateway() {
		return new RepuestoGatewayImpl();
	}
	
	public static ClientsGateway getClientGateway(){
		return new ClientGatewayImpl();
	}

	public static MediosPagoGateway getMediosPagoGateway() {
		return new MediosPagoGatewayImpl();
	}

	public static VehiculosGateway getVehiculosGateway() {
		return new VehiculosGatewayImpl();
	}
}
