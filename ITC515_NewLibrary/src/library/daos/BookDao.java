package library.daos;

import java.util.List;

import library.entities.Book;

public interface BookDao {

	public List<Book> getId();
	public List<Book> getAuthor();
	public List<Book> getTitle();
	public List<Book> getCallNumber();
	
	public Book getId(String id);
	public void ILoan(Book loan_);
	public void EBookState(Book state_);
}
