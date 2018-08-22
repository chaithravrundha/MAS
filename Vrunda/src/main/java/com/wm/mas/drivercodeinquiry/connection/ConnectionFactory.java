package com.wm.mas.drivercodeinquiry.connection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import javassist.bytecode.stackmap.TypeData.ClassName;

public class ConnectionFactory {

	private static Logger logger = Logger
			.getLogger(ClassName.class.getName());

	public static Connection getConnection() throws SQLException {

		DataSource ds = null;

		Connection conn = null;
		if (ds == null) {
			try {
				Context ic = new InitialContext();
				ds = (DataSource) ic
						.lookup("java:/comp/env/jdbc/MASCustomerInfoDB");

			} catch (NamingException ne) {

				logger.log(
						Level.SEVERE,
						"MASDriverCodeInquiry: Cannot get data source java:comp/env/jdbc/MASCustomerInfoDB.",
						ne);
				ne.printStackTrace();

			}
		}
		try {
			conn = ds.getConnection();
			System.out.println(conn + "$$$$$$$$$$$^&**");

		} catch (Exception e) {

			logger.log(
					Level.SEVERE,
					"MASDriverCodeInquiry: Cannot get connection from data source java:comp/env/jdbc/MASCustomerInfoDB.",
					e);
			e.printStackTrace();

		}

		return conn;
	}

}