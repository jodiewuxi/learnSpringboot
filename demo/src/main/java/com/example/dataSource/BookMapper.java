package com.example.dataSource;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.entities.Book;

@Mapper
public interface BookMapper {
	//@Select("SELECT * FROM readbook")
	List<Book> selectAll();
	void insertBook(Book book);
}
