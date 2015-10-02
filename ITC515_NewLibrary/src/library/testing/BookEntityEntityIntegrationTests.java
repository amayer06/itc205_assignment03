package library.testing;

import static org.junit.Assert.*;
import library.entities.Book;
import library.entities.Loan;
import library.entities.Member;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;

public class BookEntityEntityIntegrationTests {

	private  IBook testBook_;
    private ILoan testLoan_;
    private IMember testMember_;
	
	private final String author_ = "Stephenie Meyer";
	private final String title_ = "Twilight";
	private final String callNumber_ = "JHFJDS";
	private int id_ = 1;
	
    Date borrowDate_, dueDate_;
    Calendar cal_;
    

	@Before
	public void setUp() throws Exception {

	    cal_ = Calendar.getInstance();
	    borrowDate_ = new Date();
	    cal_.setTime(borrowDate_);
	    cal_.add(Calendar.DATE, ILoan.LOAN_PERIOD);
	    dueDate_ = cal_.getTime();

		testBook_ = new Book(author_, title_, callNumber_, id_);
		testMember_ = new Member("firstName", "lastName", "contactPhone", "email", 1);
		
		testLoan_ = new Loan(testBook_, testMember_, borrowDate_, dueDate_);

	}
	
    @After
    public void tearDown() throws Exception {
        
    }

	
	
    
	
    @Test
    public void testBorrowWhenAvailable() {

        //check
        assertEquals(EBookState.AVAILABLE, testBook_.getState());
        
        //execute
        testBook_.borrow(testLoan_);  
        
        
        //asserts
        assertEquals(EBookState.ON_LOAN, testBook_.getState());

    }

  
    
    @Test(expected=RuntimeException.class)
    public void testBorrowWhenNotAvailable() {

        //setup
        testBook_.borrow(testLoan_); 
        
        //check
        assertNotEquals(EBookState.AVAILABLE, testBook_.getState());
        
        //execute
        testBook_.borrow(testLoan_);        
        
        //asserts
        fail("Should have thrown an exception");

    }

    
	
    @Test
    public void testGetLoan() {

        //setup
        testBook_.borrow(testLoan_); 
        //check
        assertEquals(EBookState.ON_LOAN, testBook_.getState());
        
        
        //execute
        ILoan actual = testBook_.getLoan();
        
        //asserts
        assertEquals(actual, testLoan_);
    }

    @Test
    public void testGetLoanWhenAvailable() {

         //check
        assertEquals(EBookState.AVAILABLE, testBook_.getState());
        
        
        //execute
        ILoan actual = testBook_.getLoan();
        
        //asserts
        assertNull(actual);
    }

    @Test
    public void testGetLoanWhenLost() {
        
        //setup
        testBook_.borrow(testLoan_);
        testBook_.lose();

         //check
        assertEquals(EBookState.LOST, testBook_.getState());
        
        
        //execute
        ILoan actual = testBook_.getLoan();
        
        //asserts
        assertNull(actual);
    }

	
	

	
	
}