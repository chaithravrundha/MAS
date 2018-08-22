package com.wm.mas.drivercodeinquiry.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;



public class ConnectionFactoryTest {
	
	Properties prop = new Properties();
	Connection conn = null;


	public Connection getCentralConnection() {
		String url = null;
		try {

			Class.forName("com.ibm.as400.access.AS400JDBCDriver");

			url = "jdbc:as400://wmunitst";

			Properties prop1 = new Properties();
			prop1.setProperty("user", "wmesb");
			prop1.setProperty("password", "wmesb");

			conn = DriverManager.getConnection(url, prop1);
			if (conn != null) {
				System.out.println("connection successfull");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;

	}
}
