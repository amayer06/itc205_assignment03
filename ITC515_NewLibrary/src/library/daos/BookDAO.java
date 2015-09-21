package library.daos;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class BookDAO implements IBookDAO{

	private int nextId_;
	private Map<Integer, IBook> bookMap_;
	private IBookHelper helper_;
	
	public BookDAO (IBookHelper helper)
	{
		if(helper == null)
			throw new IllegalArgumentException ("Error: Helper is invalid.");
	
		this.helper_ = helper;
		nextId_ = 1;
		bookMap_ = new HashMap();
	}
	
	@Override
	public IBook addBook(String author, String title, String callNo) {
		
		if(author == null || author.isEmpty())
			throw new IllegalArgumentException ("Error: Author is invalid.");
		if(title == null || title.isEmpty())
			throw new IllegalArgumentException ("Error: Title is invalid.");
		if(callNo == null || callNo.isEmpty())
			throw new IllegalArgumentException ("Error: Call Number is invalid.");
		
		IBook book1 = helper_.makeBook(author, title, callNo, nextId_);
	
		bookMap_.put(nextId_, book1);
		
		nextId_++;
		
		return book1;
	}

	

	@Override
	public IBook getBookByID(int id) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public List<IBook> listBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IBook> findBooksByAuthorTitle(String author, String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
