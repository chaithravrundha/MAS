/**
 * 
 */
package com.wm.mas.drivercodeinquiry.model;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author prao1
 *
 */
@XmlRootElement
public class Library {
	String name;
	List<Driver> drivers;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Driver> getDrivers() {
		return drivers;
	}
	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}
	

}
