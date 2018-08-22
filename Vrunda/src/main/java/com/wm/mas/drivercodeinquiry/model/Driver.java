/**
 * 
 */
package com.wm.mas.drivercodeinquiry.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author prao1
 *
 */
@XmlRootElement
public class Driver {
	String code;
	String description;
	String employeeId;
	String status;
	String status_description;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus_description() {
		return status_description;
	}
	public void setStatus_description(String status_description) {
		this.status_description = status_description;
	}
}
