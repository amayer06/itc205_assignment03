package library.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.entities.Book;
import library.entities.Member;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class BookEntityDAOHelperIntegration {

	IBookHelper realBookHelper;
	IBook realBook1;    
	IBook realBook2;

	BookMapDAO bookMapDao;
	String name1 = "Simon Lowe";
	String title1 = "The Bird";
	String callNo1 = "JHJH";
	int id = 1;

	@Before
	public void setUp()
	{
		realBookHelper = new BookHelper();

		realBook1 = new Book(name1, title1, callNo1, id);

		bookMapDao = new BookMapDAO(realBookHelper);
	}



	@After
	public void clearSetUp()
	{
		realBookHelper = null;
		realBook1 = null;
		bookMapDao = null;
	}



	@Test
	public void testCreateBookMapDAO() {
		
		//execute
		bookMapDao = new BookMapDAO(realBookHelper);

		//asserts
		assertNotNull(bookMapDao);
	}

	

	@Test
	public void testAddBook() {

		//execute
		IBook actual = bookMapDao.addBook(name1, title1, callNo1);

		//asserts
		assertNotNull(actual);
		assertEquals(name1, actual.getAuthor());
		assertEquals(title1, actual.getTitle());
		assertEquals(callNo1, actual.getCallNumber());
	}



	@Test
	public void testGetBookByID() {

		//execute
		IBook actual = bookMapDao.addBook(name1, title1, callNo1);

		IBook mapBook = bookMapDao.getBookByID(1);

		//assert
		assertEquals(mapBook, actual);
	}



	@Test
	public void testListBooks() {

		//execute
		IBook book1 = bookMapDao.addBook(name1, title1, callNo1);
		IBook book2 = bookMapDao.addBook("Sarah Bell", "The Castle", "IKIK");

		List<IBook> listBooks = bookMapDao.listBooks();

		//asserts
		assertEquals(2, listBooks.size());
		assertTrue(listBooks.contains(book1));
		assertTrue(listBooks.contains(book2));
	}



	@Test
	public void testFindBooksByAuthor() {
		
		//execute
		BookMapDAO bookDao = new BookMapDAO(realBookHelper);

		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");

		List<IBook> listBooks = bookDao.findBooksByAuthor("Simon Lowe");

		IBook theBird = listBooks.get(0);

		assertEquals(1, listBooks.size());
		assertEquals("Simon Lowe", theBird.getAuthor());
	}



	@Test
	public void testFindBooksByTitle() {
		
		//execute
		BookMapDAO bookDao = new BookMapDAO(realBookHelper);

		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");

		List<IBook> listBooks = bookDao.findBooksByTitle("The Castle");

		IBook theCastle = listBooks.get(0);

		//asserts
		assertEquals("The Castle", theCastle.getTitle());
	}



	@Test
	public void testFindBooksByAuthorTitle() {
		
		//execute
		BookMapDAO bookDao = new BookMapDAO(realBookHelper);

		IBook book1 = bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		IBook book2 = bookDao.addBook("Sarah Bell", "The Castle", "IKIK");

		List<IBook> listBooks = bookDao.findBooksByAuthorTitle("Simon Lowe", "The Bird");

		//asserts
		assertEquals(1, listBooks.size());
		assertTrue(listBooks.contains(book1));
	}
}
