package Utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconnect {
	public Connection connect() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/movieticket","root","1212");
			System.out.println(conn);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return conn;
		
	}

}
