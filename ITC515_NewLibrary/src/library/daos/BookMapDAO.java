package library.daos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.entities.Book;
import library.interfaces.daos.IBookDAO;
import library.interfaces.daos.IBookHelper;
import library.interfaces.entities.IBook;

public class BookMapDAO implements IBookDAO {

	private int nextId_;
	private Map<Integer, IBook> bookMap_;
	private IBookHelper helper_;

	public BookMapDAO (IBookHelper helper)
	{
		if(helper == null)
		{
			throw new IllegalArgumentException ("Error: Helper is null.");
		}
		this.helper_ = helper;
		nextId_ = 1;
		bookMap_ = new HashMap<Integer, IBook>();
	}

	
	
	@Override
	public IBook addBook(String author, String title, String callNo) {

		if (author == null || author.isEmpty()) {
			throw new IllegalArgumentException("Error: Author is invalid.");
		}

		if (title == null || title.isEmpty()) {
			throw new IllegalArgumentException("Error: Title is invalid.");
		}

		if (callNo == null || callNo.isEmpty()) {
			throw new IllegalArgumentException("Error: Call Number is invalid.");
		}

		IBook book = helper_.makeBook(author, title, callNo, nextId_);

		bookMap_.put(nextId_, book);

		nextId_++;

		return book;
	}

	
	
	@Override
	public IBook getBookByID(int id) {

		if (bookMap_.containsKey(id))
		{
			return bookMap_.get(id);
		}
		else
			return null;
	}

	
	
	@Override
	public List<IBook> listBooks() {

		List<IBook> books = new ArrayList<IBook>(bookMap_.values());

		return books;
	}

	
	
	@Override
	public List<IBook> findBooksByAuthor(String author) {

		List<IBook> authorList = new ArrayList<IBook>();
		List<IBook> allBooks = new ArrayList<IBook>(bookMap_.values());

		for (int i = 0; i < allBooks.size(); i++) {
			if (allBooks.get(i).getAuthor().equals(author))
				authorList.add(allBooks.get(i));
		}

		return authorList;
	}

	
	
	@Override
	public List<IBook> findBooksByTitle(String title) {

		List<IBook> bookList = new ArrayList<>();
		List<IBook> allBooks = new ArrayList<IBook>(bookMap_.values());

		for (int i = 0; i < allBooks.size(); i++) {
			if (allBooks.get(i).getTitle().equals(title))
				bookList.add(allBooks.get(i));
		}

		return bookList;
	}

	
	
	@Override
	public List<IBook> findBooksByAuthorTitle(String author, String title) {

		List<IBook> findBooks = new ArrayList<>();
		List<IBook> allBooks = new ArrayList<IBook>(bookMap_.values());

		for (int i = 0; i < allBooks.size(); i++) {
			if (allBooks.get(i).getAuthor().equals(author)
					&& allBooks.get(i).getTitle().equals(title))
				findBooks.add(allBooks.get(i));
		}

		return findBooks;
	}
}
