package uo.ri.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface AveriaGateway {
	
	void setConnection(Connection con);

	void setResultSet(ResultSet rs);
	
	void verificarEstadoAverias(PreparedStatement pst);
	
	void actualizarEstadoAverias(PreparedStatement pst);
	
	void vincularAveriasConFactura(PreparedStatement pst);
	
	void updateImporteAveria(PreparedStatement pst);
}
