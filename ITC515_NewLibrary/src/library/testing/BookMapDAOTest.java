package library.testing;

import static org.junit.Assert.*;

import java.util.List;

import library.daos.BookMapDAO;
import library.daos.BookHelper;
import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BookMapDAOTest {

	IBookHelper mockBookHelper_;
	IBook mockBook1;
	IBook mockBook2;
	BookMapDAO bookMapDao_;
	String name1 = "Simon Lowe";
	String title1 = "The Bird";
	String callNo1 = "JHJH";
	String name2 = "Sarah Bell";
	String title2 = "The Castle";
	String callNo2 = "IKIK";

	@Before
	public void setUp() {
		mockBookHelper_ = mock(IBookHelper.class);
		mockBook1 = mock(IBook.class);
		mockBook2 = mock(IBook.class);

		when(mockBook1.getID()).thenReturn(1);
		when(mockBook1.getAuthor()).thenReturn(name1);
		when(mockBook1.getTitle()).thenReturn(title1);

		when(mockBook2.getID()).thenReturn(2);
		when(mockBook2.getAuthor()).thenReturn("Sarah Bell");
		when(mockBook2.getTitle()).thenReturn("The Castle");

		when(mockBookHelper_.makeBook(name1, title1, callNo1, 1)).thenReturn(
				mockBook1);
		when(mockBookHelper_.makeBook(name2, title2, callNo2, 2)).thenReturn(
				mockBook2);

		bookMapDao_ = new BookMapDAO(mockBookHelper_);
	}

	
	
	@After
	public void clearSetUp() {
		mockBookHelper_ = null;
		mockBook1 = null;
		mockBook2 = null;

		bookMapDao_ = null;
	}

	
	
	@Test
	public void testCreateBookMapDAO() {
		
		//execute
		bookMapDao_ = new BookMapDAO(mockBookHelper_);

		assertNotNull(bookMapDao_);

	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateBookMapDAOHelperNull() {

		//execute
		bookMapDao_ = new BookMapDAO(null);

		fail("Should have thrown IllegalArgumentException");
	}

	
	
	@Test
	public void testAddBook() {

		//execute
		IBook actual = bookMapDao_.addBook(name1, title1, callNo1);

		//asserts
		verify(mockBookHelper_).makeBook(name1, title1, callNo1, 1);

		assertEquals(actual, mockBook1);
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookAuthorIsNull() {

		/// execute
		IBook actual = bookMapDao_.addBook(null, title1, callNo1);

		// asserts
		verify(mockBookHelper_).makeBook(null, title1, callNo1, 1);

		assertNull(actual.getAuthor());
		
		fail("Should have thrown IllegalArgumentException");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookAuthorIsBlank() {

		/// execute
		IBook actual = bookMapDao_.addBook("", title1, callNo1);

		// asserts
		verify(mockBookHelper_).makeBook("", title1, callNo1, 1);

		assertNull(actual.getAuthor());
		
		fail("Should have thrown IllegalArgumentException");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookTitleIsNull() {

		/// execute
		IBook actual = bookMapDao_.addBook(name1, null, callNo1);

		// asserts
		verify(mockBookHelper_).makeBook(name1, null, callNo1, 1);

		assertNull(actual.getTitle());
		
		fail("Should have thrown IllegalArgumentException");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookTitleIsBlank() {

		/// execute
		IBook actual = bookMapDao_.addBook(name1, "", callNo1);

		// asserts
		verify(mockBookHelper_).makeBook(name1, "", callNo1, 1);

		assertNull(actual.getTitle());
		
		fail("Should have thrown IllegalArgumentException");
	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookCallNumberIsNull() {

		/// execute
		IBook actual = bookMapDao_.addBook(name1, title1, null);

		// asserts
		verify(mockBookHelper_).makeBook(name1, title1, null, 1);

		assertNull(actual.getCallNumber());
		
		fail("Should have thrown IllegalArgumentException");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddBookCallNumberIsBlank() {

		/// execute
		IBook actual = bookMapDao_.addBook(name1, title1, "");

		// asserts
		verify(mockBookHelper_).makeBook(name1, title1, "", 1);

		assertNotNull(actual.getCallNumber());
		
		fail("Should have thrown IllegalArgumentException");
	}
	
	
	
	@Test
	public void testGetBookByID() {

		// execute
		IBook actual = bookMapDao_.addBook(name1, title1, callNo1);

		IBook mapBook = bookMapDao_.getBookByID(1);

		// assert
		assertEquals(mapBook, mockBook1);
	}

	
	
	@Test
	public void testListBooks() {

		//execute
		bookMapDao_.addBook(name1, title1, callNo1);
		bookMapDao_.addBook("Sarah Bell", "The Castle", "IKIK");

		List<IBook> listBooks = bookMapDao_.listBooks();

		// asserts
		assertEquals(2, listBooks.size());
		assertTrue(listBooks.contains(mockBook1));
		assertTrue(listBooks.contains(mockBook2));
	}

	
	
	@Test
	public void testFindBooksByAuthor() {

		//execute
		bookMapDao_.addBook("Simon Lowe", "The Bird", "JHJH");
		bookMapDao_.addBook("Sarah Bell", "The Castle", "IKIK");

		List<IBook> listBooks = bookMapDao_.findBooksByAuthor("Simon Lowe");

		IBook theBird = listBooks.get(0);

		// asserts
		assertEquals(1, listBooks.size());
		assertEquals("Simon Lowe", theBird.getAuthor());
	}

	
	
	@Test
	public void testFindBooksByTitle() {
		
		//execute
		bookMapDao_.addBook("Simon Lowe", "The Bird", "JHJH");
		bookMapDao_.addBook("Sarah Bell", "The Castle", "IKIK");

		List<IBook> listBooks = bookMapDao_.findBooksByTitle("The Castle");

		IBook theCastle = listBooks.get(0);

		//asserts
		assertEquals("The Castle", theCastle.getTitle());
	}

	
	
	@Test
	public void testFindBooksByAuthorTitle() {

		//execute
		bookMapDao_.addBook("Simon Lowe", "The Bird", "JHJH");
		bookMapDao_.addBook("Sarah Bell", "The Castle", "IKIK");

		List<IBook> listBooks = bookMapDao_.findBooksByAuthorTitle("Simon Lowe",
				"The Bird");

		//asserts
		assertEquals(1, listBooks.size());
		assertTrue(listBooks.contains(mockBook1));
	}
}
