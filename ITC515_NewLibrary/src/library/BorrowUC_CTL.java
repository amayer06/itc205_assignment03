package library;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import library.interfaces.EBorrowState;
import library.interfaces.IBorrowUI;
import library.interfaces.IBorrowUIListener;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.ILoanDAO;
import library.interfaces.daos.IMemberDAO;
import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;
import library.interfaces.entities.IMember;
import library.interfaces.hardware.ICardReader;
import library.interfaces.hardware.ICardReaderListener;
import library.interfaces.hardware.IDisplay;
import library.interfaces.hardware.IPrinter;
import library.interfaces.hardware.IScanner;
import library.interfaces.hardware.IScannerListener;

public class BorrowUC_CTL implements ICardReaderListener, IScannerListener,
		IBorrowUIListener {

	private ICardReader reader_;
	private IScanner scanner_;
	private IPrinter printer_;
	private IDisplay display_;
	// private String state;
	private int scanCount_ = 0;
	private IBorrowUI ui_;
	private EBorrowState state_;
	private IBookDAO bookDAO_;
	private IMemberDAO memberDAO_;
	private ILoanDAO loanDAO_;

	private List<IBook> bookList_;
	private List<ILoan> loanList_;
	private IMember borrower_;

	private JPanel previous;

	public BorrowUC_CTL(ICardReader reader, IScanner scanner, IPrinter printer,
			IDisplay display, IBookDAO bookDAO, ILoanDAO loanDAO,
			IMemberDAO memberDAO) {

		this.display_ = display;
		this.ui_ = new BorrowUC_UI(this);
		state_ = EBorrowState.CREATED;
		

		this.reader_ = reader;
		this.scanner_ = scanner;
		this.printer_ = printer;
		this.bookDAO_ = bookDAO;
		this.loanDAO_ = loanDAO;
		this.memberDAO_ = memberDAO;
		
		setState(EBorrowState.CREATED);
	}

	public void initialise() {
		previous = display_.getDisplay();
		display_.setDisplay((JPanel) ui_, "Borrow UI");
		
		reader_.setEnabled(true);
		scanner_.setEnabled(false);
		setState(EBorrowState.INITIALIZED);
		ui_.setState(EBorrowState.INITIALIZED);
	}

	public void close() {
		display_.setDisplay(previous, "Main Menu");
	}

	@Override
	public void cardSwiped(int memberID) {

		throw new RuntimeException("Not implemented yet");
	}



	@Override
	public void bookScanned(int barcode) {
		throw new RuntimeException("Not implemented yet");
	}


	private void setState(EBorrowState state) {
		this.state_ = state;
		this.reader_.setEnabled(state == EBorrowState.CREATED);
		this.scanner_.setEnabled(state == EBorrowState.SCANNING_BOOKS);
	}

	@Override
	public void cancelled() {
		close();
	}

	@Override
	public void scansCompleted() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void loansConfirmed() {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public void loansRejected() {
		throw new RuntimeException("Not implemented yet");
	}

	private String buildLoanListDisplay(List<ILoan> loans) {
		StringBuilder bld = new StringBuilder();
		for (ILoan ln : loans) {
			if (bld.length() > 0) bld.append("\n\n");
			bld.append(ln.toString());
		}
		return bld.toString();
	}

}