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
public class Error {

	int code;
	String message;
	String fields;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
}
