package library.entities;

import library.interfaces.entities.EBookState;
import library.interfaces.entities.ILoan;

public class Book {

	private int id_;
	private String author_;
	private String title_;
	private String callNumber_;
	private ILoan loan_;
	private EBookState state_;
	
	public Book(int id, String author, String title, String callNumber)
	{
		this.id_ = id;
		this.author_ = author;
		this.title_ = title;
		this.callNumber_ = callNumber;
	}
	
	public int getID()
	{
		return id_;
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
	
	public ILoan getLoan()
	{
		return loan_;
	}
	
	public EBookState getState()
	{
		return state_;
	}
	
	public String toString()
	{
		return "Title: " + title_ + " by author " + author_ + ", Availablity: " + state_;
	}
}
