package com.example.controller;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.spi.http.HttpHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.services.FileUpLoadService;

@Controller
public class FileUpLoadController {
	
	@Autowired
	private FileUpLoadService filuploadservice;
	
	
	
	@GetMapping("/upload")
	public String upload() {		
			return "upload";	
	}
	
	
	@PostMapping("/uploadfile")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file) {
		
		System.out.println("Method upload start");
		
		String filename=file.getOriginalFilename();
		if( filename.indexOf("\\") > -1) {
			filename=filename.substring(filename.lastIndexOf("\\"));
		}
		String ret=filuploadservice.storeFile(file);
		
		if(ret.equals("failed")) {
			return "failed";
		}else {
			return "success";
		}
		
	}
	
	@GetMapping("/downloadFile")
	public ResponseEntity<Resource> downloadFile(@RequestParam String fileName,HttpServletRequest request) throws IOException{
		System.out.println("downloadFile start");
		System.out.println("downloadFile fileName:"+fileName);
		Resource resource = filuploadservice.loadFileAsResource(fileName);
		
		String contentType = null;
		contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        if(contentType == null) {
            contentType = "application/octet-stream";
        }	
        
        return ResponseEntity.ok()
        		.contentType(MediaType.parseMediaType(contentType))
        		.header(HttpHeaders.CONTENT_DISPOSITION,"attachen:filename=\"CS.SLK\"")
        		.contentLength(resource.contentLength())
        		.body(resource);
        
		
	}
	


}
