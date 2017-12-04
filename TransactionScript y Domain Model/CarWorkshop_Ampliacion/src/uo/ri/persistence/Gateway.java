package uo.ri.persistence;

import java.sql.Connection;

public interface Gateway {
	
	void setConnection(Connection con);
}
