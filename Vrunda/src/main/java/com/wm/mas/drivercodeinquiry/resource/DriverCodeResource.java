package com.wm.mas.drivercodeinquiry.resource;

import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.wm.mas.drivercodeinquiry.connection.ConnectionFactory;
import com.wm.mas.drivercodeinquiry.model.CustomerInfo;
import com.wm.mas.drivercodeinquiry.model.Driver;
import com.wm.mas.drivercodeinquiry.model.DriversList;
import com.wm.mas.drivercodeinquiry.model.LibrariesList;
import com.wm.mas.drivercodeinquiry.model.Library;
import com.wm.mas.drivercodeinquiry.model.Error;
import com.wm.mas.drivercodeinquiry.service.DriverCodeInquiryService;



/**
 * Root resource (exposed at "/" path)
 */
@Path("/")
public class DriverCodeResource {
	
	@Context
	UriInfo uri;
	
	Connection conn;
	@Path("{customerUniqueid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerInfo(@PathParam("customerUniqueid") String customerUniqueid) {
	  	
		List<String> customerDetails = new ArrayList<String> ();
		CustomerInfo customerInfo = new CustomerInfo();
		DriverCodeInquiryService service = new DriverCodeInquiryService();

		
		if(conn == null) {
			try {
				conn = ConnectionFactory.getConnection();
				if(conn != null) {
				System.out.println("Connected @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+conn);
				}else {
					System.out.println("Not connected @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+conn);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println(customerUniqueid);
		String pattern = "\\d{5,15}"; 
		System.out.println(customerUniqueid.matches(pattern));
		if(customerUniqueid.matches(pattern) == false) {
			customerInfo.setErrorMsg("Invalid Customer Id");
		}else {
			List<String> info = new ArrayList<String>();
			info = service.getValidListOfLibraries(customerUniqueid);
			
			System.out.println("Library names: " +service.getValidListOfLibraries(customerUniqueid));
			String library = info.get(0);
			String company = info.get(1);
			String customerNum = info.get(2);
			
			if(library != null) {
			customerDetails = service.getDetails(library, company, customerNum);
			customerInfo.setServiceName(customerDetails.get(0));
			customerInfo.setBillingPhoneNo(customerDetails.get(1));
		 }else {
			 customerInfo.setErrorMsg("Invalid Customer Id");
		 }
		}
	   // System.out.println(customerUniqueid.matches(pattern));
		 
		//get library list ordered by name
		
	 try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	return Response.status(Status.OK).entity(customerInfo).build();
	}
	
	@GET
	@Path("test")
	@Produces(MediaType.APPLICATION_JSON)
	public String getString() {
		
		return "Hello";
	}

	
}
