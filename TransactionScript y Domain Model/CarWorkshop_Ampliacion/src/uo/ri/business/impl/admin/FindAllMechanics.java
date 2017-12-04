package uo.ri.business.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.PersistenceFactory;
import uo.ri.persistence.MechanicGateway;

public class FindAllMechanics {


	public List<Map<String, Object>> execute() throws BusinessException {
		Connection c = null;

		List<Map<String, Object>> list ;
		try {
			c = Jdbc.getConnection();
			MechanicGateway gate = PersistenceFactory.getMechanicGateway();
			gate.setConnection(c);
			list = gate.findAllMechanics();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			Jdbc.close(c);
		}
		return list;
	}
}
