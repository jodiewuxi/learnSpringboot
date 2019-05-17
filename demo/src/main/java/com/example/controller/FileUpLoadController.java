package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String upload(@RequestParam("file") MultipartFile file) {
		
		System.out.println("Method upload start");
		
		String filename=file.getOriginalFilename();
		if( filename.indexOf("\\") > -1) {
			filename=filename.substring(filename.lastIndexOf("\\"));
		}
		String ret=filuploadservice.storeFile(file);
		
		if(ret.equals("failed")) {
			return "";
		}else {
			return "success";
		}
		
	}


}
