package com.example;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.config.ApplactioProperties;
import com.example.entities.Todo;
import com.example.mapper.TodoMapper;


@SpringBootApplication

public class DemoApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	
	 private final TodoMapper todoMapper;

	    public DemoApplication(TodoMapper todoMapper) {
	        this.todoMapper = todoMapper; // Mapperをインジェクションする
	    }
	
	    
	 // Spring Boot起動時にCommandLineRunner#runメソッドが呼び出される
	    @Transactional
	    @Override
	    public void run(String... args) throws Exception { 
	        Todo newTodo = new Todo();
	        newTodo.setTitle("飲み会");
	        newTodo.setDetails("銀座 19:00");

	        todoMapper.insert(newTodo); // 新しいTodoをインサートする

	        Todo loadedTodo = todoMapper.select(newTodo.getId()); // インサートしたTodoを取得して標準出力する
	        System.out.println("ID       : " + loadedTodo.getId());
	        System.out.println("TITLE    : " + loadedTodo.getTitle());
	        System.out.println("DETAILS  : " + loadedTodo.getDetails());
	        System.out.println("FINISHED : " + loadedTodo.isFinished());
	    }
	
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(-1);
		return messageSource;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    slr.setDefaultLocale(Locale.JAPAN);
	    return slr;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
}
