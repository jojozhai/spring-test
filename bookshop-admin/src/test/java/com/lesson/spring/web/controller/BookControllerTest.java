/**
 * 
 */
package com.lesson.spring.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lesson.BookShopAdminApplication;

/**
 * @author zhailiang
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookShopAdminApplication.class)
public class BookControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(fileUpload("/file/upload")
				.file(new MockMultipartFile("file","testFile.txt","multipart/form-data", "hello upload".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenQuerySuccess() throws Exception {
		String result = mockMvc.perform(get("/book")
				.param("categoryId", "1")
				.param("name", "战争")
				.param("page", "1")
				.param("size", "15")
				.param("sort", "name,desc", "createdTime,asc")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		String content = "{\"id\":null,\"name\":\"战争与和平\",\"content\":null, \"publishDate\":"+new Date().getTime()+"}";
		String result = mockMvc.perform(post("/book").content(content).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value("1"))
			.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		String content = "{\"id\":1,\"name\":\"战争与和平\",\"content\":null, \"publishDate\":\"2017-05-05\"}";
		mockMvc.perform(put("/book/1").content(content).contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value("1"));
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/book/1").contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk());
	}
	
	@Test
	public void whenCookieOrHeaderExists() throws Exception {
		mockMvc.perform(get("/book/1")
				.cookie(new Cookie("token", "123456"))
				.header("auth", "xxxxxxxxxx")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/book/1")
			.accept(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("战争与和平"))
			.andReturn().getResponse().getContentAsString();
		System.out.println(result);
			
	}
	
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(get("/book/10")
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}
	
}
