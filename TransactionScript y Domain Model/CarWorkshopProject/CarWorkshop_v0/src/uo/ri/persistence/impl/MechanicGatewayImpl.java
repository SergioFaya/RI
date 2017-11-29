package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.persistence.MechanicGateway;

public class MechanicGatewayImpl implements MechanicGateway {

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
	public void deleteMechanic(PreparedStatement pst) {
		try {
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst, con);
		}

	}

	@Override
	public void addMechanic(PreparedStatement pst) {
		try {
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst, con);
		}
	}

	@Override
	public void findAllMechanics(PreparedStatement pst) {
		try {
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst, con);
		}
	}

	@Override
	public void updateMechanic(PreparedStatement pst) {
		try {
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, pst, con);
		}
	}

}
