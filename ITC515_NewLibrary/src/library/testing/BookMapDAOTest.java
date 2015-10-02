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

	IBookHelper mockBookHelper;
	IBook mockBook1;	
	IBook mockBook2;
	BookMapDAO bookMapDao;
	String name1 = "Simon Lowe";
	String title1 = "The Bird";
	String callNo1 = "JHJH";
	
	@Before
    public void setUp()
	{
        mockBookHelper = mock(IBookHelper.class);
        mockBook1 = mock(IBook.class);
        mockBook2 = mock(IBook.class);
        
        when(mockBook1.getID()).thenReturn(1);
        when(mockBook1.getAuthor()).thenReturn(name1);
        when(mockBook1.getTitle()).thenReturn(title1);
        
        when(mockBook2.getID()).thenReturn(2);
        when(mockBook2.getAuthor()).thenReturn("Sarah Bell");
        when(mockBook2.getTitle()).thenReturn("The Castle");
        
        when(mockBookHelper.makeBook(name1, title1, callNo1, 1)).thenReturn(mockBook1);
        when(mockBookHelper.makeBook("Sarah Bell", "The Castle", "IKIK", 2)).thenReturn(mockBook2);
        
        bookMapDao = new BookMapDAO(mockBookHelper);

    }
	
	
	
	public void clearSetUp()
	{
		mockBookHelper = null;
		mockBook1 = null;
		mockBook2 = null;
		
		bookMapDao = null;
	}
	
	
	
    @Test
    public void testCreateBookMapDAO() 
    {
            bookMapDao = new BookMapDAO(mockBookHelper);
           
            assertNotNull(bookMapDao);
           
   }

    @Test
    public void testCreateBookMapDAOHelperNull() // ThrowsIllegalArgumentNull
    {
        try {
            bookMapDao = new BookMapDAO(null);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

	
	
	@Test
	public void testAddBook() {
	    
	    //execute
	    IBook actual = bookMapDao.addBook(name1, title1, callNo1);
	    
	    //asserts
	    verify(mockBookHelper).makeBook(name1, title1, callNo1, 1);
	    
        assertEquals(actual, mockBook1);
 	}

	
	
	@Test
	public void testGetBookByID() {
	    
        //execute
        IBook actual = bookMapDao.addBook(name1, title1, callNo1);
        
        IBook mapBook = bookMapDao.getBookByID(1);
        
        //assert
        assertEquals(mapBook, mockBook1);
	}

	
	
	@Test
	public void testListBooks() {
		
		bookMapDao.addBook(name1, title1, callNo1);
		bookMapDao.addBook("Sarah Bell", "The Castle", "IKIK");
		
		List<IBook> listBooks = bookMapDao.listBooks();
		
		assertEquals(2, listBooks.size());
		assertTrue(listBooks.contains(mockBook1));
		assertTrue(listBooks.contains(mockBook2));
	}

	
	
	@Test
	public void testFindBooksByAuthor() {
		BookMapDAO bookDao = new BookMapDAO(mockBookHelper);
		
		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");
		
		List<IBook> listBooks = bookDao.findBooksByAuthor("Simon Lowe");
		
		IBook theBird = listBooks.get(0);
		
		assertEquals(1, listBooks.size());
		assertEquals("Simon Lowe", theBird.getAuthor());
	}

	
	
	@Test
	public void testFindBooksByTitle() {
		BookMapDAO bookDao = new BookMapDAO(mockBookHelper);
		
		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");
		
		List<IBook> listBooks = bookDao.findBooksByTitle("The Castle");
		
		IBook theCastle = listBooks.get(0);
		
		assertEquals("The Castle", theCastle.getTitle());
	}

	
	
	@Test
	public void testFindBooksByAuthorTitle() {
		setUp();
		
		BookMapDAO bookDao = new BookMapDAO(mockBookHelper);
		
		bookDao.addBook("Simon Lowe", "The Bird", "JHJH");
		bookDao.addBook("Sarah Bell", "The Castle", "IKIK");
		
		List<IBook> listBooks = bookDao.findBooksByAuthorTitle("Simon Lowe", "The Bird");
				
		assertEquals(1, listBooks.size());
		assertTrue(listBooks.contains(mockBook1));
		
		clearSetUp();
	}
}
