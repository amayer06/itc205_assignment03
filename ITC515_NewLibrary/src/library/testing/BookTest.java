package library.testing;

import static org.junit.Assert.*;
import library.entities.Book;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BookTest {

	private static Book testBook_;
	private final String author_ = "Stephenie Meyer";
	private final String title_ = "Twilight";
	private final String callNumber_ = "JHFJDS";
	private int id_ = 1;

	ILoan mockLoan_;

	@Before
	public void setUp() throws Exception {

		testBook_ = new Book(author_, title_, callNumber_, id_);
		mockLoan_ = mock(ILoan.class);

	}

	@Test
	public void testConstructorAllParamsOK() {
		Book testBook2 = new Book("Kristin Cashore", "Graceling", "H2FGFF", 2);

		// asserts
		assertNotNull(testBook2);

		assertEquals("Kristin Cashore", testBook2.getAuthor());
		assertEquals("Graceling", testBook2.getTitle());
		assertEquals("H2FGFF", testBook2.getCallNumber());
		assertEquals(2, testBook2.getID());
		assertEquals(EBookState.AVAILABLE, testBook2.getState());
	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadParamAuthorIsNull() {

		Book bookAuthorNull = new Book(null, "If I Stay", "78SJD5", 11);

		// execute
		bookAuthorNull.getAuthor();

		fail("Should have thrown IllegalArgumentException");
	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadParamAuthorIsBlank() {

		Book bookAuthorBlank = new Book("", "If I Stay", "78SJD5", 11);

		// execute
		bookAuthorBlank.getAuthor();

		fail("Should have thrown IllegalArgumentException");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadParamTitleIsNull() {

		Book bookTitleNull = new Book("Gayle Forman", null, "78SJD5", 11);

		// execute
		bookTitleNull.getTitle();

		// asserts
		fail("Should have thrown IllegalArgumentException");
	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadParamTitleIsBlank() {

		Book bookTitleBlank = new Book("Gayle Forman", "", "78SJD5", 11);

		// execute
		bookTitleBlank.getTitle();

		// asserts
		fail("Should have thrown IllegalArgumentException");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadParamCallNumberIsNull() {

		Book bookCallNumberNull = new Book("Gayle Forman", "If I Stay", null,
				11);

		// execute
		bookCallNumberNull.getCallNumber();

		// asserts
		fail("Should have thrown IllegalArgumentException");
	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadParamCallNumberIsBlank() {

		Book bookCallNumberBlank = new Book("Gayle Forman", "If I Stay", "", 11);

		//execute
		bookCallNumberBlank.getCallNumber();

		//asserts
		fail("Should have thrown IllegalArgumentException");
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadParamIdLessThanZero() {

		Book bookIdLessThanZero = new Book("Gayle Forman", "If I Stay",
				"78SJD5", -2);

		fail("Should have thrown IllegalArgumentException");
	}

	
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadParamIdEqualsZero() {

		Book bookIdZero = new Book("Gayle Forman", "If I Stay", "78SJD5", 0);

		fail("Should have thrown IllegalArguementException");
	}

	

	@Test
	public void testBorrow() {

		Book book = new Book("Maggie Stifvater", "Shiver", "JFJDJ1", 3);

		// execute
		when(mockLoan_.getBook()).thenReturn(book);
		when(mockLoan_.isOverDue()).thenReturn(false);

		// asserts
		assertEquals(mockLoan_.getBook(), book);
	}

	
	
	@Test(expected = RuntimeException.class)
	public void testBorrowBookNotAvailable() {

		ILoan loan = null;
		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.borrow(loan);

		fail("Should have thrown RuntimeException");
	}

	
	
	@Test
	public void testGetLoan() {

		Book book = new Book("Maggie Stifvater", "Linger", "H2J760", 4);

		// execute
		book.borrow(mockLoan_);

		// asserts
		assertEquals(mockLoan_, book.getLoan());
	}

	
	
	@Test
	public void testReturnBookTrue() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		when(mockLoan_.getBook()).thenReturn(book);

		assertEquals(book.getState(), EBookState.AVAILABLE);

		// execute
		book.borrow(mockLoan_);
		book.returnBook(true);

		// asserts
		assertEquals(book.getState(), EBookState.DAMAGED);
	}

	
	
	@Test
	public void testReturnBookFalse() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		when(mockLoan_.getBook()).thenReturn(book);

		assertEquals(book.getState(), EBookState.AVAILABLE);

		// execute
		book.borrow(mockLoan_);
		book.returnBook(false);

		// asserts
		assertEquals(book.getState(), EBookState.AVAILABLE);
	}

	
	
	@Test(expected = RuntimeException.class)
	public void testReturnBookNotOnLoan() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.returnBook(true);

		// asserts
		fail("Should have thrown RuntimeException");
	}

	
	
	@Test(expected = RuntimeException.class)
	public void testReturnBookNotLost() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.returnBook(true);

		// asserts
		fail("Should have thrown RuntimeException");
	}

	
	
	@Test
	public void testLose() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.borrow(mockLoan_);
		book.lose();

		// asserts
		assertEquals(book.getState(), EBookState.LOST);
	}

	
	
	@Test(expected = RuntimeException.class)
	public void testLoseThrowsRunTimeException() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.lose();

		fail("Should have thrown RuntimeException");

	}

	
	
	@Test
	public void testRepair() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// asserts
		assertEquals(book.getState(), EBookState.AVAILABLE);
	}

	
	
	@Test(expected = RuntimeException.class)
	public void testRepairBookIsNotDamaged() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.repair();

		fail("Should have thrown RuntimeException");
	}

	
	
	@Test
	public void testDisposeWhenBookIsAvailable() {
		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.dispose();

		// asserts
		assertEquals(book.getState(), EBookState.DISPOSED);
	}

	
	
	@Test
	public void testDisposeWhenBookIsDamaged() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);
		book.borrow(mockLoan_);
		book.returnBook(true);

		// execute
		book.dispose();

		// asserts
		assertEquals(book.getState(), EBookState.DISPOSED);
	}

	
	
	@Test
	public void testDisposeWhenBookIsLost() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);
		book.borrow(mockLoan_);
		book.lose();

		// execute
		book.dispose();

		// asserts
		assertEquals(book.getState(), EBookState.DISPOSED);
	}

	
	
	@Test(expected = RuntimeException.class)
	public void testDisposeWhenBookIsOnLoan() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.borrow(mockLoan_);
		book.dispose();

		fail("Should have thrown RuntimeException");
	}

	
	
	@Test
	public void testGetState() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		// execute
		book.borrow(mockLoan_);

		// asserts
		assertEquals(book.getState(), EBookState.ON_LOAN);
	}

	
	
	@Test
	public void testGetAuthor() {

		// asserts
		assertEquals("Stephenie Meyer", testBook_.getAuthor());
	}

	

	@Test
	public void testGetTitle() {

		// asserts
		assertEquals("Twilight", testBook_.getTitle());

	}

	

	@Test
	public void testGetCallNumber() {

		// asserts
		assertEquals("JHFJDS", testBook_.getCallNumber());
	}

	

	@Test
	public void testGetId() {

		assertEquals(1, testBook_.getID());
	}
}