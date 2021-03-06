package com.example.services;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.tomcat.jni.File;	
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.config.ApplactioProperties;

@Service
@EnableConfigurationProperties(ApplactioProperties.class)
public class FileUpLoadService {
	
	
	private Path savefilepath;

	@Autowired
	public FileUpLoadService(ApplactioProperties saveProperty) {
		
		this.savefilepath=Paths.get(saveProperty.getSavefilepath()).toAbsolutePath().normalize();
		try {
            Files.createDirectories(this.savefilepath);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	
	}
	
	public String storeFile(MultipartFile file) {
		
		System.out.println("Method storeFile start");
		
		String filename=file.getOriginalFilename();
		if( filename.indexOf("\\") > -1) {
			filename=filename.substring(filename.lastIndexOf("\\"));
		}
		Path targetLocation = this.savefilepath.resolve(filename);
		try {
		Files.copy(file.getInputStream(), targetLocation,StandardCopyOption.REPLACE_EXISTING);
		}catch(Exception e) {
			e.printStackTrace();
			return "create file failed";
		}
		return filename;
	}
	
	
	public Resource loadFileAsResource(String fileName) throws MalformedURLException {
		Path filePath = this.savefilepath.resolve(fileName).normalize();
		System.out.println("path:"+filePath.toAbsolutePath().toString());
		Resource resource = new UrlResource(filePath.toUri());
		if(resource.exists()) {
            
        } else {
            System.out.println("file can not found");
        }
		return resource;
		
		
	}
	

}
