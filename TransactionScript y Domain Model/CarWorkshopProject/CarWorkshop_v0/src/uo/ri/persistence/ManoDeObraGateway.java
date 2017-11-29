package uo.ri.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ManoDeObraGateway {
	
	void selectImporteManoDeObra(PreparedStatement pst);

	void setConnection(Connection con);

	void setResultSet(ResultSet rs);
}
