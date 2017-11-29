package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.FacturasGateway;

public class FacturaGatewayImpl implements FacturasGateway {

	private Connection con;
	private ResultSet rs;

	@Override
	public void setConnection(Connection con) {
		this.con = con;	
	}

	@Override
	public void setResultSet(ResultSet rs) {
		this.rs = rs;
	}
	
	@Override
	public void insertarFactura(PreparedStatement pst) {
		try {
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void recuperarClaveFacturaGenerada(PreparedStatement pst) {
		try {
			rs = pst.executeQuery();
			rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}

	}

	@Override
	public void ultimoNumeroFactura(PreparedStatement pst) {
		try {
			rs = pst.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			Jdbc.close(pst);
		}

	}

}
