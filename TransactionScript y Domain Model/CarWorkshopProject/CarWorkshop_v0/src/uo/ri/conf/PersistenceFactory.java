package uo.ri.conf;

import uo.ri.persistence.AveriaGateway;
import uo.ri.persistence.FacturaGateway;
import uo.ri.persistence.ManoDeObraGateway;
import uo.ri.persistence.MechanicGateway;
import uo.ri.persistence.RepuestoGateway;
import uo.ri.persistence.impl.AveriaGatewayImpl;
import uo.ri.persistence.impl.FacturaGatewayImpl;
import uo.ri.persistence.impl.ManoDeObraGatewayImpl;
import uo.ri.persistence.impl.MechanicGatewayImpl;
import uo.ri.persistence.impl.RepuestoGatewayImpl;

public class PersistenceFactory {

	public static MechanicGateway getMechanicGateway() {
		return new MechanicGatewayImpl();
	}

	public static AveriaGateway getAveriasGateway() {
		return new AveriaGatewayImpl();
	}

	public static FacturaGateway getFacturaGateway() {
		return new FacturaGatewayImpl();
	}

	public static ManoDeObraGateway getManoDeObraGateway() {
		return new ManoDeObraGatewayImpl();
	}

	public static RepuestoGateway getRepuestoGateway() {
		return new RepuestoGatewayImpl();
	}
}
