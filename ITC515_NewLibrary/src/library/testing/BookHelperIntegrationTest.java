package library.testing;

import static org.junit.Assert.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import library.interfaces.daos.IBookHelper;
import library.daos.BookHelper;

import library.interfaces.entities.IBook;
import library.entities.Book;



public class BookHelperIntegrationTest {

	private final String author = "Stephenie Meyer";
	private final String title = "Twilight";
	private final String callNumber = "JHFJDS";
	private int id = 1;

	@Test
	public void testMakeBook() {

		IBookHelper bookHelper = new BookHelper();

		IBook book = bookHelper.makeBook(author, title, callNumber, id);
		
		assertNotNull(book);
        assertEquals(book.getAuthor(), author);
        assertEquals(book.getTitle(), title);
        assertEquals(book.getCallNumber(), callNumber);
        assertEquals(book.getID(), id);
		

	}
}
