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

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BookMapDAOTest {

	IBookHelper bookHelper;
	IBook book1;
	IBook book2;
	
	@Before
    public void setUp()
	{
        bookHelper = mock(IBookHelper.class);
        book1 = mock(IBook.class);
        book2 = mock(IBook.class);
        
        when(book1.getID()).thenReturn(1);
        when(book1.getAuthor()).thenReturn("Simon Lowe");
        when(book1.getTitle()).thenReturn("The Bird");
        
        when(book2.getID()).thenReturn(2);
        when(book2.getAuthor()).thenReturn("Sarah Bell");
        when(book2.getTitle()).thenReturn("The Castle");
        
        when(bookHelper.makeBook("Simon Lowe", "The Bird", "JHJH", 1)).thenReturn(book1);
        when(bookHelper.makeBook("Sarah Bell", "The Castle", "IKIK", 2)).thenReturn(book2);
    }
	
	
	
	public void clearSetUp()
	{
		bookHelper = null;
		book1 = null;
		book2 = null;
	}
	
	
	
	@Test
	public void testBookMapDAO() // ThrowsIllegalArgumentNull
	{
		try {
			BookMapDAO bookMapDao = new BookMapDAO(null);
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testAddBook() {
		IBookDAO testBook = mock(IBookDAO.class);

		Book book = new Book("Maggie Stifvater", "Shiver", "JFJDJ1", 3);

		when(testBook.addBook("Alex Flinn", "Beastly", "K684JE")).thenReturn(
				book);

		assertEquals(testBook.addBook("Alex Flinn", "Beastly", "K684JE"), book);
	}

	
	
	@Test
	public void testGetBookByID() {
		setUp();
		
		BookMapDAO testBookById = mock(BookMapDAO.class);

		Book book = new Book("Maggie Stifvater", "Shiver", "JFJDJ1", 3);

		when(testBookById.getBookByID(3)).thenReturn(book);
	
		assertEquals(null, testBookById.getBookByID(-1));
		assertEquals(3, book.getID());
	
		clearSetUp();
	}

	
	
	@Test
	public void testListBooks() {
		setUp();
		
		BookMapDAO bookDao = new BookMapDAO(bookHelper);
		
		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");
		
		List<IBook> listBooks = bookDao.listBooks();
		
		assertEquals(2, listBooks.size());
		assertTrue(listBooks.contains(book1));
		assertTrue(listBooks.contains(book2));
		
		clearSetUp();
	}

	
	
	@Test
	public void testFindBooksByAuthor() {
		setUp();
		
		BookMapDAO bookDao = new BookMapDAO(bookHelper);
		
		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");
		
		List<IBook> listBooks = bookDao.listBooks();
		
		bookDao.findBooksByAuthor("Simon Lowe");
		
		assertTrue(listBooks.contains(book1));

		clearSetUp();
	}

	
	
	@Test
	public void testFindBooksByTitle() {
		setUp();
		
		BookMapDAO bookDao = new BookMapDAO(bookHelper);
		
		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");
		
		List<IBook> listBooks = bookDao.listBooks();
		
		bookDao.findBooksByTitle("The Bird");

		assertEquals(bookDao.getBookByID(1), book1);
		assertTrue(listBooks.contains(book1));

		clearSetUp();
	}

	
	
	@Test
	public void testFindBooksByAuthorTitle() {
		setUp();
		
		BookMapDAO bookDao = new BookMapDAO(bookHelper);
		
		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");
		
		List<IBook> listBooks = bookDao.listBooks();
		
		bookDao.findBooksByAuthorTitle("Simon Lowe", "The Bird");
		bookDao.findBooksByTitle("The Bird");
		
		assertEquals(bookDao.getBookByID(1), book1);
		assertTrue(listBooks.contains(book1));
		
		clearSetUp();
	}
}
