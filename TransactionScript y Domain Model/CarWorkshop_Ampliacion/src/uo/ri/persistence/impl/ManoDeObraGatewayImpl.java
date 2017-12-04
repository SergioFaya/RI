package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.conf.Conf;
import uo.ri.persistence.ManoDeObraGateway;

public class ManoDeObraGatewayImpl implements ManoDeObraGateway {

	private Connection con;
	private ResultSet rs;

	@Override
	public void setConnection(Connection con) {
		this.con = con;
	}

	@Override
	public Map<String, Object> selectImporteManoDeObra(Map<String, Object> map) throws BusinessException {
		PreparedStatement pst = null;
		Map<String, Object> mapReturn = new HashMap<>();
		try {
			pst = con.prepareStatement(Conf.get("SQL_IMPORTE_MANO_OBRA"));
			pst.setLong(1, (long) map.get("idAveria"));
			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("La averia no existe o no se puede facturar");
			}
			mapReturn.put("importe", rs.getDouble(1));
		} catch (BusinessException e) {
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}
		return mapReturn;
	}

}
