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
		{
			throw new IllegalArgumentException ("Error: Author is invalid.");
		}
		
		if(title == null  || title.isEmpty())
		{
			throw new IllegalArgumentException ("Error: Title is invalid.");
		}

		if(callNumber == null || callNumber.isEmpty()) 
		{
			throw new IllegalArgumentException ("Error: Call Number is invalid.");
		}
		
		if (id <= 0) {
			throw new IllegalArgumentException ("Error: Id is invalid.");
		}
		
		this.author_ = author;
		this.title_ = title;
		this.callNumber_ = callNumber;
		this.id_ = id;
        this.state_ = EBookState.AVAILABLE;

	}

	

	public void borrow(ILoan loan)
	{
		if(loan == null)
		{
			throw new IllegalArgumentException ("Book is Available.");
		}
		
		if(state_ != EBookState.AVAILABLE)
		{
			throw new RuntimeException("Book not Available");
		}
		
		this.loan_ = loan;
		this.state_ = EBookState.ON_LOAN;
	}
	
	
	
	public ILoan getLoan()
	{
		if(state_ != EBookState.ON_LOAN) 
		{
			return null;
		}
		else
			return loan_;
	}
	
	
	
	public void returnBook(boolean damaged)
	{
		if(state_ != EBookState.ON_LOAN && state_ != EBookState.LOST )
		{
			throw new RuntimeException("Book is Available");
		}
		
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
		{
			throw new RuntimeException("Book is not Lost");
		}
		
		state_ = EBookState.LOST;
		loan_ = null;
	}
	
	
	
	public void repair()
	{
		if(state_ != EBookState.DAMAGED)
		{
			throw new RuntimeException("Book is not Damaged");
		}
		state_ = EBookState.AVAILABLE;
	}
	
	
	
	public void dispose()
	{
	    boolean disposable = (state_ == EBookState.AVAILABLE || state_ == EBookState.DAMAGED || state_ == EBookState.LOST);
	    
		if(!disposable)
		{
			throw new RuntimeException(String.format("Book is not disposable: %s", state_));
		}
		
		state_ = EBookState.DISPOSED;
	}
	
	
	
	public EBookState getState()
	{
		return state_;
	}

	
//	Added this method, to make testing easier.
	public void setState(EBookState state)
	{
		this.state_ = state;
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
		return "Author: " + author_ + " Title: " + title_ + " Book Call Number: " + callNumber_ + " ID:" + id_;
	}
	
	
	
	public int getID()
	{
		return id_;
	}
}