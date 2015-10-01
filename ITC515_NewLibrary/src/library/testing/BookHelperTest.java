package library.testing;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import library.entities.Book;

import org.junit.Test;

import library.daos.BookHelper;

public class BookHelperTest {

	private final String author = "Stephenie Meyer";
	private final String title = "Twilight";
	private final String callNumber = "JHFJDS";
	private int id = 1;

	@Test
	public void testMakeBook() {

		// create mock
		BookHelper testHelper = mock(BookHelper.class);

		Book book = new Book("Stephenie Meyer", "Twilight", "JHFJDS", 1);

		when(testHelper.makeBook(author, title, callNumber, id)).thenReturn(book);

		assertEquals(testHelper.makeBook("Stephenie Meyer", "Twilight", "JHFJDS", 1), book);
	}
}
