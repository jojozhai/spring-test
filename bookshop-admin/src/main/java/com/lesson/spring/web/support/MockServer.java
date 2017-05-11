/**
 * 
 */
package com.lesson.spring.web.support;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;

/**
 * @author zhailiang
 *
 */
public class MockServer {
	
	public static void main(String[] args) {
		
		configureFor("127.0.0.1", 8080);
		
		stubFor(get(urlEqualTo("/book"))
				.willReturn(okJson("{'name':'tom'}")));
		
		
	}

}
