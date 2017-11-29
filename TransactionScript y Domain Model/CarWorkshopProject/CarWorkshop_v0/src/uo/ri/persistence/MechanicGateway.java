package uo.ri.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface MechanicGateway {
	
	void setConnection(Connection con);

	void setResultSet(ResultSet rs);
	
	void addMechanic(PreparedStatement pst);
	
	void deleteMechanic(PreparedStatement pst);

	void findAllMechanics(PreparedStatement pst);
	
	void updateMechanic(PreparedStatement pst);	
}
