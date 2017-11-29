package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.AveriaGateway;

public class AveriaGatewayImpl implements AveriaGateway {

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
	public void verificarEstadoAverias(PreparedStatement pst) {
		try {
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void actualizarEstadoAverias(PreparedStatement pst) {
		try {
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void vincularAveriasConFactura(PreparedStatement pst) {
		try {
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(pst);
		}

	}

	@Override
	public void updateImporteAveria(PreparedStatement pst) {
		// TODO Auto-generated method stub

	}

}
