package com.example.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix="app")
public class ApplactioProperties {
	private String savefilepath;

	public String getSavefilepath() {
		return savefilepath;
	}

	public void setSavefilepath(String savefilepath) {
		this.savefilepath = savefilepath;
	}
	
	
	
}
