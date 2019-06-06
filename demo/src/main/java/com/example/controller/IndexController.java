package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dataSource.BookMapper;
import com.example.entities.Book;

@Controller
public class IndexController {
	@Autowired
	BookMapper bookmapper;
	
	@RequestMapping
	public String index(Model model) {
		List<Book> list = bookmapper.selectAll(1);
		model.addAttribute("books", list);
		return "index";
	}
	
	@GetMapping(value="/adduser")
	public String adduser(Model model) {

		return "insertUser";
	}
	
}
