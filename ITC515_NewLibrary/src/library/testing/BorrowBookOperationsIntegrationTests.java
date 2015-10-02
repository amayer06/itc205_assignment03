package library.testing;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import java.util.Calendar;
import java.util.Date;

import library.BorrowUC_CTL;
import library.daos.BookHelper;
import library.daos.BookMapDAO;
import library.daos.LoanHelper;
import library.daos.LoanMapDAO;
import library.daos.MemberHelper;
import library.daos.MemberMapDAO;
import library.entities.Book;
import library.entities.Loan;
import library.entities.Member;
import library.interfaces.EBorrowState;
import library.interfaces.IBorrowUI;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;

public class BorrowBookOperationsIntegrationTests {
    BorrowUC_CTL ctl_;
    
    ICardReader reader;
    IScanner scanner;
    IPrinter printer;
    IDisplay display;
    IBorrowUI ui;
    
    IBookDAO bookDAO;
    IMemberDAO memberDAO;
    ILoanDAO loanDAO;
    
    Date borrowDate_, dueDate_;
    Calendar cal_;



    @Before
    public void setUp() throws Exception {
        reader = mock(ICardReader.class);
        scanner = mock(IScanner.class);
        printer = mock(IPrinter.class);
        display = mock(IDisplay.class);
        ui = mock(IBorrowUI.class);
        
        bookDAO = new BookMapDAO(new BookHelper());
        memberDAO = new MemberMapDAO(new MemberHelper());
        loanDAO = new LoanMapDAO(new LoanHelper());
                
        ctl_ = new BorrowUC_CTL(reader, scanner, printer, display, bookDAO, loanDAO, memberDAO, ui );
        
        IBook book = bookDAO.addBook("author1", "title1", "callNo1");
        
        IMember member = memberDAO.addMember("firstName1", "lastName1", "ContactPhone1", "emailAddress1");
        
        cal_ = Calendar.getInstance();
        borrowDate_ = new Date();
        cal_.setTime(borrowDate_);
        cal_.add(Calendar.DATE, ILoan.LOAN_PERIOD);
        dueDate_ = cal_.getTime();

        
        ILoan loan = new Loan(book, member, borrowDate_, dueDate_);
        
        loanDAO.commitLoan(loan);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCardSwipedBorrowingEnabledNoFinesNoRestrictions() {
        //setup
        ctl_.setState(EBorrowState.INITIALIZED);
        
        
        //execute
        ctl_.cardSwiped(1);
        
        //verifies
        verify(reader).setEnabled(false);
        verify(scanner).setEnabled(true);
        verify(ui).setState(EBorrowState.SCANNING_BOOKS);
        verify(ui).displayMemberDetails(1, "firstName1 lastName1" , "ContactPhone1");
        verify(ui).displayExistingLoan(any(String.class));    
        
        //assert
        assertEquals(EBorrowState.SCANNING_BOOKS, ctl_.getState());
    }


}
