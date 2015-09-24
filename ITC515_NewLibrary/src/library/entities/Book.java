package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.IBook;
import library.interfaces.entities.ILoan;

public class Book implements IBook{

	private int id_;
	private String author_;
	private String title_;
	private String callNumber_;
	private ILoan loan_;
	private EBookState state_;
	
	
	
	public Book(String author, String title, String callNumber, int id)
	{
		if(author == null || author.isEmpty())
			throw new IllegalArgumentException ("Error: Author is invalid.");
		if(title == null  || title.isEmpty())
			throw new IllegalArgumentException ("Error: Title is invalid.");
		if(callNumber == null || callNumber.isEmpty()) 
			throw new IllegalArgumentException ("Error: Call Number is invalid.");
		if (id <= 0) 
			throw new IllegalArgumentException ("Error: Id is invalid.");
		
		this.author_ = author;
		this.title_ = title;
		this.callNumber_ = callNumber;
		this.id_ = id;
	}

	

	public void borrow(ILoan loan)
	{
		if(loan == null)
			throw new IllegalArgumentException ("Book is Available.");
		if(state_ != EBookState.AVAILABLE)
			throw new RuntimeException("Book not Available");
		
		this.loan_ = loan;
		this.state_ = EBookState.ON_LOAN;
	}
	
	
	
	public ILoan getLoan()
	{
		if(state_ != EBookState.ON_LOAN)
			return null;
		else
			return loan_;
	}
	
	
	
	public void returnBook(boolean damaged)
	{
		if(state_ != EBookState.ON_LOAN && state_ != EBookState.LOST )
			throw new RuntimeException("Book is Available");
		
		if (damaged) 
		{
			state_ = EBookState.DAMAGED;
			loan_ = null;
		}
		else {
			state_ = EBookState.AVAILABLE;
			loan_ = null;
		}
	}
	
	
	
	public void lose()
	{
		if(state_ != EBookState.ON_LOAN)
			throw new RuntimeException("Book is not Lost");
		
		state_ = EBookState.LOST;
	}
	
	
	
	public void repair()
	{
		if(state_ != EBookState.DAMAGED)
			throw new RuntimeException("Book is not Damaged");
		
		state_ = EBookState.AVAILABLE;
	}
	
	
	
	public void dispose()
	{
		if(state_ != EBookState.AVAILABLE)
			throw new RuntimeException("Book is not Available");
		if(state_ != EBookState.DAMAGED)
			throw new RuntimeException("Book is not Damaged");
		if(state_ != EBookState.LOST)
			throw new RuntimeException("Book is not Lost");
		
		state_ = EBookState.DISPOSED;
	}
	
	
	
	public EBookState getState()
	{
		return state_;
	}

	
	
	public String getAuthor()
	{
		return author_;
	}
	
	
	
	public String getTitle()
	{
		return title_;
	}
	
	
	
	public String getCallNumber()
	{
		return callNumber_;
	}
	
	
	
	public String toString()
	{
		return "Book Call Number: " + callNumber_;
	}
	
	
	
	public int getID()
	{
		return id_;
	}
}