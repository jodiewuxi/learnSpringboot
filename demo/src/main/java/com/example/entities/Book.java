package com.example.entities;

public class Book {


/*	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)*/
/*    private Long book_id;*/
    private String bookname;
    private String bookwriter;
    private String comment;
  
    public Book() {
    }

/*	public Long getBook_id() {
		return book_id;
	}

	public void setBook_id(Long book_id) {
		this.book_id = book_id;
	}*/

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


 
}