/**
 * 
 */
package com.lesson.domain;

import javax.persistence.Embeddable;

/**
 * @author zhailiang
 *
 */
@Embeddable
public class Address {
	
	private String province;
	
	private String city;
	
	private String area;
	
	private String address;
	
	private String zipcode;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
}
