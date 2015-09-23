package test.lowlevelunittesting;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.Before;
import org.junit.Test;

public class BookTest {

	private IBookDAO bookDAO;
	private static Book testBook;
	private static EBookState nullLoan;
	private static EBookState loan;

	private final String author = "Stephenie Meyer";
	private final String title = "Twilight";
	private final String callNumber = "JHFJDS";
	private final int id = 1;

	@Before
	public void setUp() throws Exception {
		
		testBook = new Book(author, title, callNumber, id);
		
//		IBook[] book = new IBook[15];

//		IBook controlBook = bookDAO.addBook("author1", "title1", "callNo1");
//		I = bookDAO.addBook("author1", "title2", "callNo2");
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
//
//		Calendar cal = Calendar.getInstance();
//		Date now = cal.getTime();
	}

	@Test
	public void testBook() {
		Book constructor1 = new Book("Kristin Cashore", "Graceling", "H2FGFF",2);

		assertEquals("Kristin Cashore", constructor1.getAuthor());
		assertEquals("Graceling", constructor1.getTitle());
		assertEquals("H2FGFF", constructor1.getCallNumber());
		assertEquals(2, constructor1.getID());
	}

	// @Test
	// public void testBorrow() {
	//
	//
	// }
	//
	// @Test
	// public void testGetLoan() {
	//
	//
	// // assertNull(EBookState.ON_LOAN,nullLoan);
	// }
	//
	//
	// @Test
	// public void testReturnBook() {
	//
	//
	// }
	//
	// @Test
	// public void testLost() {
	//
	//
	// }
	//
	// @Test
	// public void testRepair() {
	//
	//
	// }
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
