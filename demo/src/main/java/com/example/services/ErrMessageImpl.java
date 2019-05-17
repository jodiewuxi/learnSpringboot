package com.example.services;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ErrMessageImpl implements ErrMessageService {
	
	@Autowired
	private MessageSource messageSource;

	@Override
	public String getMessage(String id ,Locale locale) {
		//Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(id, null, locale);
	}

	@Override
	public String getMessage(String id, String[] param,Locale locale) {
		//Locale locale = LocaleContextHolder.getLocale();
		return messageSource.getMessage(id, param, locale);
	}

}
