
//package googleHashCode;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class BookScanning {

	public static void main(String[] args) throws IOException {
		// Open the file
		File dataset = new File("b_read_on.txt");
		Scanner fileReader;
		try {
			fileReader = new Scanner(dataset);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		
		// Read our data
		int bookCount = fileReader.nextInt();
		int libraryCount = fileReader.nextInt();
		int daysForScanning = fileReader.nextInt();
		fileReader.nextLine();
		
		String bookScores = fileReader.nextLine();
		ArrayList<Book> allBooks = new ArrayList<Book>();
		
		Scanner bookScanner = new Scanner(bookScores);
		
		while(bookScanner.hasNextInt()) {
			int bookScore = bookScanner.nextInt();
			Book newBook = new Book(bookScore);
			allBooks.add(newBook);
		}
		
		// System.out.println("Books: " + allBooks.toString());
		
		Scanner libraryInfoScanner, libraryBookScanner;
		ArrayList<Library> allLibraries = new ArrayList<Library>();
		for(int i = 0; i < libraryCount; i++) {
			// Populate the libraries here
			libraryInfoScanner = new Scanner(fileReader.nextLine());
			libraryBookScanner = new Scanner(fileReader.nextLine());
			
			int libraryBookCount = libraryInfoScanner.nextInt();
			int librarySignupDuration = libraryInfoScanner.nextInt();
			int libraryBooksShippablePerDay = libraryInfoScanner.nextInt();
			
			Library newLibrary = new Library(libraryBookCount, librarySignupDuration, libraryBooksShippablePerDay);
			newLibrary.id = i;
			ArrayList<Book> libraryBooks = new ArrayList<Book>();
			for(int j = 0; j < libraryBookCount; j++) {
				int bookId = libraryBookScanner.nextInt();
				Book newBook = new Book(bookId);
				libraryBooks.add(newBook);
			}
			newLibrary.Library = libraryBooks;
			allLibraries.add(newLibrary);
		}
		
		// Problem logic
		writeFile(allLibraries);
		
		bookScanner.close();
		fileReader.close();
	}
	
	
	public static void writeFile(ArrayList<Library> allLibraries) throws IOException {
		File output = new File("results.txt");
		FileOutputStream fos = new FileOutputStream(output);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		Library.sortLibraries(allLibraries);
		bw.write(Integer.toString(allLibraries.size()));// Writes Number of Library
		bw.flush();
		System.out.println(allLibraries.size()+"\n");
		bw.newLine();
		int i = 0;
		
		while(i<allLibraries.size()) {
			bw.write(allLibraries.get(i).id+" "+allLibraries.get(i).Library.size());
			System.out.println((allLibraries.get(i).id+" "+allLibraries.get(i).Library.size()));
			bw.newLine();
			int j=0;
			while(j<allLibraries.get(i).numberOfBooks) {
			
				bw.write(allLibraries.get(i).Library.get(j).bookId + " ");
				System.out.print(allLibraries.get(i).Library.get(j).bookId + " ");
				
				j++;	
			}
			bw.newLine();
			System.out.println("\n");
			
		i++;	
		}
		
		bw.flush();
		bw.close();
	}
}