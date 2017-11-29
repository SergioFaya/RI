package uo.ri.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface RepuestoGateway {
	
	void selectImporteRepuestos(PreparedStatement pst);

	void setConnection(Connection con);

	void setResultSet(ResultSet rs);
}
