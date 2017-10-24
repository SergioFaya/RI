package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.sql.DataSource;

import org.hsqldb.lib.HashMap;

import com.mchange.v2.c3p0.DataSources;

public class DBCarsConnection {

	static String URL= "jdbc:hsqldb:hsql://localhost";
	static String USER= "sa"; 
	static String PASS= "";	
	
	
	
	
	public static void main(String[] args) {
		new DBCarsConnection().runLoops();
	}
	
	
	private void runLoops() {
		try {
			exercise1();
			exercise2();
			complexStatementvsComplexPrepared();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void complexStatementvsComplexPrepared()  throws SQLException{
	
		DataSource unpooled = DataSources.unpooledDataSource(URL,USER,PASS);
		Map overrides = (Map) new HashMap();
		overrides.put("maxStatements","200");
		overrides.put("maxPoolSize", new Integer(50));
		overrides.put("minPoolSize", "6");
		
		DataSource pooled = DataSources.pooledDataSource(unpooled, overrides);
		Connection con = pooled.getConnection();
		Statement st = con.createStatement();
		String textQuery =  "select * from TVEHICULOS where ID =";
		PreparedStatement pStatement = con.prepareStatement("select * from TVEHICULOS where ID = ?");
		long startTimeSt , startTimePst ,endTimeSt, endTimePst;
		startTimeSt = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			st.executeQuery(textQuery+i);
		}
		endTimeSt = System.currentTimeMillis();
		startTimePst = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			pStatement.setInt(1, i);
			pStatement.executeQuery();
		}
		endTimePst = System.currentTimeMillis();
		System.out.println("###########MEDICION STATEMENTS############");
		System.out.println("Tiempo Statement: " +(startTimeSt - endTimeSt));
		System.out.println("Tiempo Prepared Statement: "+(startTimePst - endTimePst));
		System.out.println("Tiempo Medio Statement: " +(startTimeSt - endTimeSt)/100);
		System.out.println("Tiempo MEdio Prepared Statement: "+(startTimePst - endTimePst)/100);
		System.out.println("#################################");
	}
	
	private void exercise1() throws SQLException{
		Connection con;
		Statement st;
		String textQuery =  "select * from TVEHICULOS";
		
		int i = 0;
		long startTime,endTime;
		startTime = System.currentTimeMillis();
		while (i < 100) {
			con = DriverManager.getConnection(URL);
			st = con.createStatement();
			st.executeQuery(textQuery);
			con.close();
			i++;
		}
		endTime= System.currentTimeMillis();
		
		long time = endTime - startTime;
		double average = time/100;
		System.out.println("###########EXERCISE 1############");
		System.out.println("Tiempo total transcurrido en las 100 conexiones:"+ time);
		System.out.println("Tiempo medio:"+ average);
		System.out.println("#################################");
	}
		
	private void exercise2() throws SQLException{
		Connection con;
		Statement st;
		String textQuery =  "select * from TVEHICULOS";
		con = DriverManager.getConnection(URL);
		st = con.createStatement();
		int i = 0;
		long startTime, endTime; 
		startTime = System.currentTimeMillis();
		while (i < 100) {
			st.executeQuery(textQuery);
			i++;
		}
		con.close();
		endTime = System.currentTimeMillis();
		
		long time = endTime - startTime;
		double average = time/100;
		System.out.println("###########EXERCISE 2############");
		System.out.println("Tiempo total transcurrido en las 100 conexiones:"+ time);
		System.out.println("Tiempo medio:"+ average);
		System.out.println("#################################");	
	}
	
}
