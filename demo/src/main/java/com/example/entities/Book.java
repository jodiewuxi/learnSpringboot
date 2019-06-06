package com.example.entities;

public class Book {


/*	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
    private Long book_id;
    private String bookname;
    private String bookwriter;
    private String comment;
    private String create_on;
    private String last_login;

    public Book() {
    }

	public Long getBook_id() {
		return book_id;
	}

	public void setBook_id(Long book_id) {
		this.book_id = book_id;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookwriter() {
		return bookwriter;
	}

	public void setBookwriter(String bookwriter) {
		this.bookwriter = bookwriter;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreate_on() {
		return create_on;
	}

	public void setCreate_on(String create_on) {
		this.create_on = create_on;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}

 
}