package com.wm.mas.drivercodeinquiry.service;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.ws.rs.WebApplicationException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.wm.mas.drivercodeinquiry.connection.ConnectionFactoryTest;
import com.wm.mas.drivercodeinquiry.model.DriversList;


//@RunWith(MockitoJUnitRunner.class)
public class DriverCodeInquiryServiceTest {
	/*
	*//**
	 * Test method for {@link com.wm.mas.drivercodeinquiry.service.DriverCodeInquiryService#validateLibrary(java.lang.String)}.
	 *//*
	
	@Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;*/
    
   // DriversList drivers;
    
   // @Before
	public void setUp() throws Exception {
    	System.out.println("hhASJH");
       /* when(c.prepareStatement(anyString())).thenReturn(stmt);
        doNothing().when(stmt).setString(anyInt(),anyString());
        when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true);*/
    }
	
	
	


	@Test
	public void testGetValidListOfLibraries() {
		//fail("Not yet implemented");
		DriverCodeInquiryService service = new DriverCodeInquiryService();
		ConnectionFactoryTest cf = new ConnectionFactoryTest();
		Connection c = cf.getCentralConnection();
		service.setConn(c);
		/*assertNotNull("", service.getValidListOfLibraries());*/
		
	}

}
