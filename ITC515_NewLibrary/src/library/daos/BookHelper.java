package library.daos;

import library.entities.Book;
import library.interfaces.entities.IBook;

public class BookHelper {

	public IBook makeBook(String author, String title, String callNumber, int id) {
		return new Book(author, title, callNumber, id);
	}
}
