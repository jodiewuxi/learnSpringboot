package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dataSource.BookMapper;
import com.example.entities.Book;

@Controller
public class BookController {
	@Autowired
	BookMapper bookmapper;
	
/*	@RequestMapping
	public String index(Model model) {
		List<Book> list = bookmapper.selectAll(1);
		model.addAttribute("books", list);
		return "index";
	}*/
	
	
	@GetMapping(value="/menu")
	public String menu(Model model) {
			
		return "menu";
	}
	
	@GetMapping(value="/addbook")
	public String addbook(Model model) {
		
		Book book= new Book();
		model.addAttribute("bookinfo",book);		
		return "addbook";
	}
	
	
	@RequestMapping(value="/bookinfoadd",method=RequestMethod.POST)
	@ResponseBody
	public String summit(@Valid @ModelAttribute("bookinfo")Book book,
			BindingResult result/*,Model model*/) {
		
		if(result.hasErrors()) {
			return "error";
		}
		
		try {
			bookmapper.insertBook(book);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		/*model.addAttribute("bookinfo",new Book());*/

		return "success";
	}
	
	
	
	@RequestMapping(value="/bookinfo",method=RequestMethod.GET)
	public String view(Model model) {
		
		List<Book> booklist = bookmapper.selectAll();
		System.out.println(booklist.get(0).getBookname());
		model.addAttribute("booklist",booklist);

		return "bookview";
	}
	
}
