/**
 * 
 */
package com.lesson.spring.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lesson.spring.dto.FileInfo;

/**
 * @author zhailiang
 *
 */
@RestController
@RequestMapping("/file")
public class FileController {
	
	@PostMapping("/upload")
	public FileInfo update(MultipartFile file) throws Exception {
	
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		
		String path = "/Users/zhailiang/Documents/my/roncoo/workspace/bookshop-admin/src/main/java/com/lesson/spring/web/controller";
		String extention = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
		File localfile = new File(path, new Date().getTime()+"."+extention);
		file.transferTo(localfile);
		
		return new FileInfo(localfile.getAbsolutePath());
		
	}
	
	@GetMapping("/download")
	public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String filePath = "/Users/zhailiang/Documents/my/roncoo/workspace/bookshop-admin/src/main/java/com/lesson/spring/web/controller/1494176321973.txt";
		try(InputStream inputStream = new FileInputStream(filePath);
			OutputStream outputStream = response.getOutputStream();){
			
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename=test.txt");
			
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
		
	}

}
