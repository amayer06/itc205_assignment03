package library.daos;

import java.util.List;

import library.entities.Book;

public interface BookDao {

	public List<Book> getId();
//	public List<Book> getAuthor();
//	public List<Book> getTitle();
//	public List<Book> getCallNumber();
	
	public Book getAuthor(String author);
	public Book getTitle(String title);
	public Book getCallNumber(String callNumber);
	public void ILoan(Book loan);
	public void EBookState(Book state);
}
