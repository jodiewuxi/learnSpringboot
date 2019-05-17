package com.example.services;

import java.util.Locale;

public interface ErrMessageService {
	
	String getMessage(String id,Locale locale);
	String getMessage(String id, String[] param,Locale locale);

}
