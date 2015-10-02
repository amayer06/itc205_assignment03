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

	@Before
	public void setUp() throws Exception {

		testBook_ = new Book(author_, title_, callNumber_, id_);

	}

	
	
	@Test
	public void testConstructorAllParamsOK() {
		Book testBook2 = new Book("Kristin Cashore", "Graceling", "H2FGFF", 2);
		
		assertNotNull(testBook2);

		assertEquals("Kristin Cashore", testBook2.getAuthor());
		assertEquals("Graceling", testBook2.getTitle());
		assertEquals("H2FGFF", testBook2.getCallNumber());
		assertEquals(2, testBook2.getID());
		assertEquals(EBookState.AVAILABLE, testBook2.getState());
	}

    @Test(expected=IllegalArgumentException.class)
    //public void testGetIdThrowsIllegalArgumentExceptionLessThanZero() {
    public void testConstructorBadParamIDLessThanZero() {

        Book bookIdLessThanZero = new Book("Gayle Forman", "If I Stay",
                    "78SJD5", -2);
        
        fail("Should have thrown IllegalArgumentException");
    }

    
    
    @Test    
    public void testConstructorBadParamIDEqualsZero() {

        try {
            Book bookIdZero = new Book("Gayle Forman", "If I Stay", "78SJD5", 0);
            fail("Should have thrown IllegalArguementException");
        } 
        catch (IllegalArgumentException e) {}
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testConstructorBadParamIDAuthorNull() {

        Book bookIdZero = new Book(null, "If I Stay", "78SJD5", 1);
        fail("Should have thrown IllegalArguementException");
    }
    
	
	@Test
	public void testBorrow() {

		// create mock
		ILoan testBorrow = mock(ILoan.class);

		Book book = new Book("Maggie Stifvater", "Shiver", "JFJDJ1", 3);

		when(testBorrow.getBook()).thenReturn(book);
		when(testBorrow.isOverDue()).thenReturn(false);

		assertEquals(testBorrow.getBook(), book);
	}

	
	
	@Test
	public void testBorrowThrowsRuntimeExceptionNotAvailable() {

		ILoan loan = null;
		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		book.setState(EBookState.AVAILABLE);
		try {
			book.borrow(loan);
			fail("Should have thrown RuntimeException");
		} catch (RuntimeException r) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testGetLoan() {

		// create mock
		ILoan testGetLoan = mock(ILoan.class);

		Book book = new Book("Maggie Stifvater", "Linger", "H2J760", 4);

		when(testGetLoan.getBook()).thenReturn(book);
		when(testGetLoan.isOverDue()).thenReturn(false);

		assertEquals(testGetLoan.getBook(), book);
	}

	
	
	@Test
	public void testReturnBookTrue() {

		ILoan mockBook = mock(ILoan.class);

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);
		book.setState(EBookState.ON_LOAN);

		when(mockBook.getBook()).thenReturn(book);
		when(mockBook.isOverDue()).thenReturn(false);
	
		assertEquals(book.getState(), EBookState.ON_LOAN);
		
		book.returnBook(true);

		assertEquals(book.getState(), EBookState.DAMAGED);
	}

	
	
	@Test
	public void testReturnBookFalse() {

		ILoan mockBook = mock(ILoan.class);

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);
		book.setState(EBookState.ON_LOAN);

		when(mockBook.getBook()).thenReturn(book);
		when(mockBook.isOverDue()).thenReturn(false);
		
		assertEquals(book.getState(), EBookState.ON_LOAN);
		
		book.returnBook(false);

		assertEquals(book.getState(), EBookState.AVAILABLE);
	}

	
	
	@Test
	public void testReturnBookThrowsRunTimeExceptionNotOnLoan() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		book.setState(EBookState.AVAILABLE);
		try {
			book.returnBook(true);
			fail("Should have thrown RuntimeException");
		} catch (RuntimeException r) {
			assertTrue(true);
		}
	}
	
	
	
	@Test
	public void testReturnBookThrowsRunTimeExceptionNotLost() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		book.setState(EBookState.AVAILABLE);
		try {
			book.returnBook(true);
			fail("Should have thrown RuntimeException");
		} catch (RuntimeException r) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testLose() {

		IBook mockBook = mock(IBook.class);

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		book.setState(EBookState.LOST);
		when(mockBook.getState()).thenReturn(EBookState.LOST);

		assertEquals(book.getState(), EBookState.LOST);
	}

	
	
	@Test
	public void testLoseThrowsRunTimeException() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		book.setState(EBookState.AVAILABLE);
		try {
			book.lose();
			fail("Should have thrown RuntimeException");
		} catch (RuntimeException r) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testRepair() {

		IBook mockBook = mock(IBook.class);

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		book.setState(EBookState.AVAILABLE);
		when(mockBook.getState()).thenReturn(EBookState.AVAILABLE);

		assertEquals(book.getState(), EBookState.AVAILABLE);
	}

	
	
	@Test
	public void testRepairThrowsRuntimeExceptionNotDamaged() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		book.setState(EBookState.AVAILABLE);
		try {
			book.repair();
			fail("Should have thrown RuntimeException");
		} catch (RuntimeException r) {
			assertTrue(true);
		}
	}

	
	
    @Test
    public void testDisposeWhenAvailable() {
        Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);
        book.dispose();

        assertEquals(book.getState(), EBookState.DISPOSED);
    }

    @Test
    public void testDisposeWhenDamaged() {
        //setup
        ILoan mockLoan = mock(ILoan.class);
        
        Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);       
        book.borrow(mockLoan);
        book.returnBook(true);
        
        //execute
        book.dispose();

        //asserts
        assertEquals(book.getState(), EBookState.DISPOSED);
    }

    
    @Test
    public void testDisposeWhenLost() {
        //setup
        ILoan mockLoan = mock(ILoan.class);
        
        Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);       
        book.borrow(mockLoan);
        book.lose();
        
        //execute
        book.dispose();

        //asserts
        assertEquals(book.getState(), EBookState.DISPOSED);
    }

    
	
	@Test(expected=RuntimeException.class)
	public void testDisposeThrowsRuntimeExceptionWhenOnLoan() {
        //setup
        ILoan mockLoan = mock(ILoan.class);

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);
        book.borrow(mockLoan);

		book.dispose();
		//aaserts
		fail("Should have thrown RuntimeException");
	}

	
		
	@Test
	public void testGetState() {

		Book book = new Book("Maggie Stifvater", "Forever", "W68XYK", 9);

		book.setState(EBookState.ON_LOAN);

		assertEquals(book.getState(), EBookState.ON_LOAN);
	}

	
	
	@Test
	public void testGetAuthor() {

		assertEquals("Stephenie Meyer", testBook_.getAuthor());
	}

	
	
	@Test
	public void testGetAuthorThrowsIllegalArgumentExceptionNull() {

		try {
			Book bookAuthorNull = new Book(null, "If I Stay", "78SJD5", 11);
			bookAuthorNull.getAuthor();
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testGetAuthorThrowsIllegalArgumentExceptionBlank() {

		try {
			Book bookAuthorBlank = new Book("", "If I Stay", "78SJD5", 11);
			bookAuthorBlank.getAuthor();
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testGetTitle() {

		assertEquals("Twilight", testBook_.getTitle());

	}

	
	
	@Test
	public void testGetTitleThrowsIllegalArgumentExceptionNull() {

		try {
			Book bookTitleNull = new Book("Gayle Forman", null, "78SJD5", 11);
			bookTitleNull.getTitle();
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testGetTitleThrowsIllegalArgumentExceptionBlank() {

		try {
			Book bookTitleBlank = new Book("Gayle Forman", "", "78SJD5", 11);
			bookTitleBlank.getTitle();
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testGetCallNumber() {

		assertEquals("JHFJDS", testBook_.getCallNumber());
	}

	
	
	@Test
	public void testGetCallNumberThrowsIllegalArgumentExceptionNull() {

		try {
			Book bookCallNumberNull = new Book("Gayle Forman", "If I Stay",
					null, 11);
			bookCallNumberNull.getCallNumber();
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testGetCallNumberThrowsIllegalArgumentExceptionBlank() {

		try {
			Book bookCallNumberBlank = new Book("Gayle Forman", "If I Stay",
					"", 11);
			bookCallNumberBlank.getCallNumber();
			fail("Should have thrown IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	
	
	@Test
	public void testGetId() {

		assertEquals(1, testBook_.getID());
	}

	
	
}