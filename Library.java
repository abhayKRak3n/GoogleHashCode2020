//package googleHashCode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Library {
	ArrayList<Book> Library = new ArrayList<Book>();
	//int setupTime;
	int id;
	public int numberOfBooks;
	public int timeForSignUp;		//time in days 
	public int booksSent;		//books scanned/day
	
	public Library(int numOfBooks,int setupTime,int bookSent) {
			
			this.numberOfBooks = numOfBooks;
			this.timeForSignUp = setupTime;
			this.booksSent = bookSent;
	}
	
	public String toString() {
		return Library.toString();
	}
	
	public static void sortLibraries(ArrayList<Library> allLibraries) {
		// Sort the books in each library in descending order
		Comparator<Book> bookComp = new Comparator<Book>() {
		        @Override
		        public int compare(Book book1, Book book2) {
		        	// -1 so the order is from greatest to least
		            return Integer.compare(book1.bookId, book1.bookId);
		        }
		    };
		
		for(int i = 0; i < allLibraries.size(); i++) {
			Collections.sort(allLibraries.get(i).Library, bookComp);
			Collections.reverse(allLibraries.get(i).Library);
		}
		
		// Sort all the libraries in order of earliest first
		Comparator<Library> libraryComp = new Comparator<Library>() {
		        @Override
		        public int compare(Library library1, Library library2) {
		        	// -1 so the order is from greatest to least
		            return Integer.compare(library1.timeForSignUp, library2.timeForSignUp);
		        }
		    };
		Collections.sort(allLibraries, libraryComp);
	}
}