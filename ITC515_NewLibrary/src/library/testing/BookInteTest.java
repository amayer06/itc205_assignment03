package library.testing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import library.entities.Book;
import library.entities.Loan;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

public class BookInteTest {

	IBook book_;
	IMember member_;
	ILoan loan_;
	
	private String author_;
	private String title_;
	private String callNumber_;
	private int id_;
	
	Date borrowDate_, dueDate_;
	Calendar _cal;
	
	@Before
	public void setUp() throws Exception {
		//mocks don't use them.
//		member_ = mock(IMember.class);
//		loan_ = mock(ILoan.class);
		
		author_ = "Clare Jones";
		title_ = "Knights";
		callNumber_ = "H3LL55";
		id_ = 1;
		
		book_ = new Book(author_, title_, callNumber_, id_);
		
	}
	
	@After
	public void clearSetUp() throws Exception
	{
		book_ = null;
	}
	
	@Test
	public void testBook()
	{
		IBook book = new Book(author_, title_, callNumber_, id_);
		assertTrue(book instanceof Book);
	}
	
	@Test
	public void testBorrow()
	{		
		Loan loan = new Loan(book_, member_, borrowDate_, dueDate_);
				
		book_.borrow(loan_); 
		
		assertEquals(loan_.getBook(), book_);
	}
}
