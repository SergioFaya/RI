package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import uo.ri.persistence.RepuestoGateway;

public class RepuestoGatewayImpl implements RepuestoGateway {

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
	public void selectImporteRepuestos(PreparedStatement pst) {

	}

}
