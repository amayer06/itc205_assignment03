package test.lowlevelunittesting;

//import static org.mockito.Mockito*;
import static org.junit.Assert.*;
import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;


import library.interfaces.entities.ILoan;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

	private IBookDAO bookDAO;
	private static Book testBook;
	private static ILoan loan;

	private final String author = "Stephenie Meyer";
	private final String title = "Twilight";
	private final String callNumber = "JHFJDS";
	private int id = 1;
	
//	private final IBook borrowBookLoan;
	
//	private final String borrowBookLoan = loan;

	@Before
	public void setUp() throws Exception {
		
		testBook = new Book(author, title, callNumber, id);
				
		IBook[] book = new IBook[15];


//		IBook controlBook = bookDAO.addBook("author1", "title2", "callNo2");
//		book[2] = bookDAO.addBook("author1", "title3", "callNo3");
//		book[3] = bookDAO.addBook("author1", "title4", "callNo4");
//		book[4] = bookDAO.addBook("author2", "title5", "callNo5");
//		book[5] = bookDAO.addBook("author2", "title6", "callNo6");
//		book[6] = bookDAO.addBook("author2", "title7", "callNo7");
//		book[7] = bookDAO.addBook("author2", "title8", "callNo8");
//		book[8] = bookDAO.addBook("author3", "title9", "callNo9");
//		book[9] = bookDAO.addBook("author3", "title10", "callNo10");
//		book[10] = bookDAO.addBook("author4", "title11", "callNo11");
//		book[11] = bookDAO.addBook("author4", "title12", "callNo12");
//		book[12] = bookDAO.addBook("author5", "title13", "callNo13");
//		book[13] = bookDAO.addBook("author5", "title14", "callNo14");
//		book[14] = bookDAO.addBook("author5", "title15", "callNo15");


	}

	@Test
	public void testBook() {
		Book constructor1 = new Book("Kristin Cashore", "Graceling", "H2FGFF",2);

		assertEquals("Kristin Cashore", constructor1.getAuthor());
		assertEquals("Graceling", constructor1.getTitle());
		assertEquals("H2FGFF", constructor1.getCallNumber());
		assertEquals(2, constructor1.getID());
	}

	 @Test
	 public void testBorrow() {
//		 Book borrowBookLoan = new Book(author, title, callNumber,id);
//		 borrowBookLoan.borrow(loan);
//		 Book borrowBookNull = new Book(author, title, callNumber,id);
//		 borrowBookNull.borrow(null);
//		 
//		 IBook borrowBookNull = bookDAO.addBook("author3", "title3", "callNumber3");
//		 borrowBookNull.borrow(null);
//		 IBook borrowBookLoan = bookDAO.addBook("author2", "title2", "callNumber2");
//		 borrowBookLoan.borrow(loan);
//		 
//		 assertEquals(borrowBookNull, null);
//		 assertEquals(borrowBookLoan, loan);
	

	
	 }
	
//	 @Test
//	 public void testGetLoan() {
//		 
//		 IBook borrowBookNull = bookDAO.addBook("author3", "title3", "callNumber3");
//		 borrowBookNull.borrow(null);
//		 IBook borrowBookLoan = bookDAO.addBook("author2", "title2", "callNumber2");
//		 borrowBookLoan.borrow(loan);
//		 
//		 assertEquals(borrowBookNull, null);
//		 assertEquals(borrowBookLoan, loan);
//	 }
	//
	//
	// @Test
	// public void testReturnBook() {
	//
//	
//	 }
//	
//	 @Test
//	 public void testLose() {
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
	// @Test
	// public void testGetState() {
	//
	//
	// }


	@Test
	public void testGetAuthor() {

		assertEquals("Stephenie Meyer", testBook.getAuthor());

	}

	@Test
	public void testGetTitle() {

		assertEquals("Twilight", testBook.getTitle());

	}

	@Test
	public void testGetCallNumber() {

		assertEquals("JHFJDS", testBook.getCallNumber());
	}

	@Test
	public void testGetId() {

		assertEquals(1, testBook.getID());
	}
}
