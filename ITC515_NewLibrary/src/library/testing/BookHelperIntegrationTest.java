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

	private final String author_ = "Stephenie Meyer";
	private final String title_ = "Twilight";
	private final String callNumber_ = "JHFJDS";
	private int id_ = 1;

	@Test
	public void testMakeBook() {

		//execute
		IBookHelper bookHelper = new BookHelper();

		IBook book = bookHelper.makeBook(author_, title_, callNumber_, id_);
		
		//asserts
		assertNotNull(book);
        assertEquals(book.getAuthor(), author_);
        assertEquals(book.getTitle(), title_);
        assertEquals(book.getCallNumber(), callNumber_);
        assertEquals(book.getID(), id_);
		

	}
}
