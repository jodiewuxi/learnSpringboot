package com.example.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.services.ErrMessageService;

@Controller
public class DemoController {

	@Autowired
	ErrMessageService msg;
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index() {
		
		return "index";
	}
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String memu(Model model) {
		String msg1 = msg.getMessage("msg1", null, Locale.CHINA);
		String msg2 = msg.getMessage("msg2", new String[] { "岐阜", "山崎屋" }, Locale.US);
		model.addAttribute("msg1", msg1);
		model.addAttribute("msg2", msg2);
		return "index";
		
	}
}
