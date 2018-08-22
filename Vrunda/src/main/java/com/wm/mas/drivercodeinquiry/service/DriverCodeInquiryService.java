/**
 * 
 */
package com.wm.mas.drivercodeinquiry.service;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.wm.mas.drivercodeinquiry.connection.ConnectionFactory;
import com.wm.mas.drivercodeinquiry.model.CustomerInfo;
import com.wm.mas.drivercodeinquiry.model.Driver;
import com.wm.mas.drivercodeinquiry.model.Error;
import com.wm.mas.drivercodeinquiry.model.Library;

import javassist.bytecode.stackmap.TypeData.ClassName;

/**
 * @author prao1
 *
 */
public class DriverCodeInquiryService {
	PreparedStatement ps;
	Connection conn;
	Error error;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private static Logger logger = Logger.getLogger(ClassName.class.getName());

	

	private void recoverConnection() {
		int retry = 3;
		try {
			while (conn == null && retry != 0) {
				retry--;
				conn = ConnectionFactory.getConnection();
				if (conn != null)
					break;
				else if (retry == 0 && conn == null) {
					throw new Exception("Could not get connection to the Database, Retries left: " + retry);
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"MASDriverCodeInquiry: Cannot get connection from data source java:comp/env/jdbc/MASCustomerInfoDB.", e);
			error = new Error();
			error.setCode(504);
			error.setMessage(e.getMessage());

			throw new WebApplicationException(Response.status(Status.GATEWAY_TIMEOUT).entity(error).build());
		}
	}

	
	public List<String> getValidListOfLibraries(String customerUniqueid) {
		if (conn == null) {
			recoverConnection();
		}
		
		//CustomerInfo customerInfo = new CustomerInfo();
		String librariesQuery = "SELECT LIBRARY,COMPANY,CUSTOMER_NUMBER FROM  \"MASLIBRS\".W1000 where customer_unique_number=?";
		System.out.println(librariesQuery);
		System.out.println(librariesQuery);
		List<String> result = new ArrayList<String>();
		System.out.println(result);
		ResultSet res = null;
		try {
			ps = conn.prepareStatement(librariesQuery);
			ps.setString(1, customerUniqueid);
			res = ps.executeQuery();
			while (res.next()) {
				if(res.getString("LIBRARY") != null){
				result.add(res.getString("LIBRARY"));
			    result.add(res.getString("COMPANY"));
				result.add(res.getString("CUSTOMER_NUMBER"));
				}
			}
         
		} catch (SQLException e) {
			Response.status(Status.OK).entity("Internal error").build();
			//throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build());
		} finally {
			try {
				if (res != null)
					res.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	
		return result;
	}
	 public Boolean validateCustomerUniqueId(String CustomerUniqueId) {
		 if (conn == null) {
				recoverConnection();
			}
			
	        String result = null;
	        Boolean isValid = false;
			String validateCustomerId = "SELECT LIBRARY,COMPANY,CUSTOMER_NUMBER FROM  \"MASLIBRS\".W1000 where customer_unique_number=?";
			
			ResultSet res = null;
			try {
				ps = conn.prepareStatement(validateCustomerId);
				ps.setString(1, CustomerUniqueId);
				res = ps.executeQuery();
				while (res.next()) {
					result = res.getString("LIBRARY");
				}

				if(result.equalsIgnoreCase(CustomerUniqueId)) {
					isValid = true;
				}else {
					isValid = false;
				}
			} catch (SQLException e) {
				

				throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build());
			} finally {
				try {
					if (res != null)
						res.close();
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		 
		 return isValid;
	 }
	
	


	public List<String> getDetails(String library, String company, String customerNum) {
		if (conn == null) {
			recoverConnection();
		}
        
		String librariesQuery = "Select SERVICE_NM, BILLING_PHONE_NO from " + '"' + library + '"'+".CUSTOMER" + " Where COMPANY=? and  CUSTOMER_NO=?";
		List<String> result = new ArrayList<String>();
		ResultSet res = null;
		try {
			ps = conn.prepareStatement(librariesQuery);
			ps.setString(1, company);
			ps.setString(2, customerNum);
			res = ps.executeQuery();
			while (res.next()) {
				result.add(res.getString("SERVICE_NM").trim());
			    result.add(res.getString("BILLING_PHONE_NO").trim());
			}

		} catch (SQLException e) {
			

			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build());
		} finally {
			try {
				if (res != null)
					res.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		
		return result;
		
	}

}
