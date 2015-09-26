package library.lowlevelunittesting;

import static org.junit.Assert.*;
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

	
	@Test
	public void testBookDAO()
	{
		//create mock
		 BookMapDAO testBook = mock(BookMapDAO.class);
		 IBookHelper testBookHelper = mock(IBookHelper.class);
		 
		 Book book = new Book("Maggie Stifvater", "Shiver", "JFJDJ1", 3);
		
		 when(testBookHelper.makeBook("Maggie Stifvater", "Shiver", "JFJDJ1", 3)).thenReturn(book);
		 
		 assertEquals(testBookHelper.makeBook("Maggie Stifvater", "Shiver", "JFJDJ1", 3), book);
	}
	
	@Test
	public void testaddBook()
	{
		IBookDAO testBook = mock(IBookDAO.class);
		
		Book book = new Book("Maggie Stifvater", "Shiver", "JFJDJ1", 3);
			
		when(testBook.addBook("Alex Flinn", "Beastly", "K684JE")).thenReturn(book);
		
		assertEquals(testBook.addBook("Alex Flinn", "Beastly", "K684JE"), book);
	}
	
	@Test
	public void testGetBookByID()
	{
		
	}
	
	@Test
	public void testListBooks()
	{
		
	}
	
	@Test
	public void testFindBooksByAuthor()
	{
		
	}
	
	@Test
	public void testFindBooksByTitle()
	{
		
	}
	
	@Test
	public void testFindBooksByAuthorTitle()
	{
		
	}
}
