package test.lowlevelunittesting;

import static org.junit.Assert.*;

import library.entities.Book;
import library.interfaces.entities.EBookState;

import library.interfaces.entities.ILoan;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;


public class BookTest {

	private static Book testBook1;
	private static EBookState ON_LOAN;

	private final String author = "Stephenie Meyer";
	private final String title = "Twilight";
	private final String callNumber = "JHFJDS";
	private int id = 1;
	

	@Before
	public void setUp() throws Exception {
		
		testBook1 = new Book(author, title, callNumber, id);
	}

	@Test
	public void testBook() {
		Book testBook2 = new Book("Kristin Cashore", "Graceling", "H2FGFF",2);

		assertEquals("Kristin Cashore", testBook2.getAuthor());
		assertEquals("Graceling", testBook2.getTitle());
		assertEquals("H2FGFF", testBook2.getCallNumber());
		assertEquals(2, testBook2.getID());
	}

	 @Test
	 public void testBorrow() {

		//create mock
		 ILoan testBorrow = mock(ILoan.class);
		 
		 Book book = new Book("Maggie Stifvater", "Shiver", "JFJDJ1", 3);
	
		 when(testBorrow.getBook()).thenReturn(book);
		 when(testBorrow.isOverDue()).thenReturn(false);
		 
		 assertEquals(testBorrow.getBook(), book);
	 }
	
	 @Test
	 public void testGetLoan() {
		 
		 //create mock
		 ILoan testGetLoan = mock(ILoan.class);
		 
		 Book book = new Book("Maggie Stifvater", "Linger", "H2J760", 4);
		 
		 when(testGetLoan.getBook()).thenReturn(book);
		 when(testGetLoan.isOverDue()).thenReturn(false);
		 
		 assertEquals(testGetLoan.getBook(), book);
	 }
	
	
//	 @Test
//	 public void testReturnBook() {
//	
//	
//	 }
//	
//	 @Test
//	 public void testLose() {
//
//		 
//	 
//	
//	 }
//	
//	 @Test
//	 public void testRepair() {
//	
//	
//	 }
	//
	// @Test
	// public void testDispose() {
	//
	//
	// }
	//
	 @Test
	 public void testGetState() {
		 
		 ILoan testGetState = mock(ILoan.class);
		 
		 Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		 when(testGetState.getBook()).thenReturn(book);
		 when(testGetState.isOverDue()).thenReturn(true);
		 
		 
		 assertEquals(ON_LOAN, testBook1.getState());
	 }


	@Test
	public void testGetAuthor() {

		assertEquals("Stephenie Meyer", testBook1.getAuthor());

	}

	@Test
	public void testGetTitle() {

		assertEquals("Twilight", testBook1.getTitle());

	}

	@Test
	public void testGetCallNumber() {

		assertEquals("JHFJDS", testBook1.getCallNumber());
	}

	@Test
	public void testGetId() {

		assertEquals(1, testBook1.getID());
	}
}
