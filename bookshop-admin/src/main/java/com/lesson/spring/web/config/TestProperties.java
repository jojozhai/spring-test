/**
 * 
 */
package com.lesson.spring.web.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhailiang
 *
 */
@ConfigurationProperties(prefix = "xxx")
public class TestProperties {
	
	private Map<String, String> users = new HashMap<>();
	
	private Y yyy = new Y();
	
	private Zz zzz = new Zz();

	public Y getYyy() {
		return yyy;
	}

	public void setYyy(Y yyy) {
		this.yyy = yyy;
	}

	public Zz getZzz() {
		return zzz;
	}

	public void setZzz(Zz zzz) {
		this.zzz = zzz;
	}

	public Map<String, String> getUsers() {
		return users;
	}

	public void setUsers(Map<String, String> users) {
		this.users = users;
	}
	
}
