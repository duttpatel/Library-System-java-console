package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import Model.Database;
import View.Author;
import View.Book;
import View.Category;
import View.Faculty;
import View.Member;
import View.Publication;
import View.Student;
import View.Transactions;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static Database db = new Database();
	static PrintWriter writer;
	static int booksBorrowedStatus=0;
	static Member objMember;
	static File file = new File("transcation.txt");
	public static void main(String[] args) throws ParseException, IOException, BackingStoreException {
		
		LoadAuthor(db.getListOfAuthor());
		LoadPublisher(db.getListOfPublication());
		LoadCategory(db.getListOfCategory());
		LoadBook(db.getListOfBook());
		LoadStudent(db.getListOfStudent());
		LoadFaculty(db.getListOfFaculty());

		showMenu(db);

		
	}


	public static void showMenu(Database db) throws IOException, BackingStoreException {

		do {
			System.out.println();
			System.out.println("Library Management System\n");
			System.out.println("\t1. Rent a Book");
			System.out.println("\t2. Return a Book");
			System.out.println("\t3. List all transactions");
			System.out.println("\t4. Show Reports");
			System.out.println("\t5. Save All Transcations");
			System.out.println("\t6. Exit");

			System.out.println();

			int input = Integer.parseInt(sc.nextLine());

			switch (input) {
			case 1:
				checkPrefernceValue(file);
				writer=new PrintWriter(new FileWriter(file,true));
				showBookRentOption(db);
				break;
			case 2:
				checkPrefernceValue(file);
				writer=new PrintWriter(new FileWriter(file,true));
				showBookReturnOption(db);
				break;
			case 3:
				for (Transactions transactions : db.getListOfTrans()) {
					System.out.println(transactions);
				}
				break;
			case 4:
				if(db.getListOfTrans().size()>0){
					showReports();	
				}else{
					System.out.println("No Trasnation History");	
				}
				
				break;
			case 5:
				if(db.getListOfTrans().size()>0){
					saveTranscations();	
				}else{
					System.out.println("No Trasnation History");	
				}
				break;	
			case 6:
				System.out.println("Thank you for using Application");
				System.exit(0);
				break;
			default:
				System.out.println("Please Select from Above Option. Thank you.!");
				showMenu(db);
				break;
			}

		} while (true); // Display the menu until the user closes the program
	}

	private static void checkPrefernceValue(File file) throws BackingStoreException {
		// TODO Auto-generated method stub
		if(!file.exists()){
			System.out.println("In file");
			Transactions.prefs.remove(Transactions.ID);
			Transactions.prefs.clear();
		}
	}


	private static void saveTranscations() {
		// TODO Auto-generated method stub
//		writer.close();
		System.out.println("All Transcations saved in a File named transcation.txt ");
	}


	private static void showReports() throws IOException, BackingStoreException {

		do {
			System.out.println();
			System.out.println("Library Management System Reports\n");
			System.out.println("\t1. Report 1 FINE AMOUNT PER MONTH");
			System.out.println("\t2. Report 2 BOOKS THAT NEVER ISSUED");
			System.out.println("\t3. Report 3 BOOKS IN DEMAND");
			System.out.println("\t4. Report 4 MEMBERS DO LATE SUBMISSION");
			System.out.println("\t5. Report 5 MONTH OF MOST TRANSACTION");
			System.out.println("\t6. Back");

			System.out.println();

			int input = Integer.parseInt(sc.nextLine()); // Get user input from the keyboard

			switch (input) {
			case 1:
				report1();
				break;
			case 2:
				report2();
				break;
			case 3:
				report3();
				break;
			case 4:
				report4();
				break;
			case 5:
				report5();
				break;
			case 6:
				showMenu(db);
				break;	
			default:
				break;
			}

		} while (true); // Display the menu until the user closes the program	
	}


	



	private static void showBookReturnOption(Database db) {
		System.out.println("Enter the Transaction ID to return: ");
		String transId = sc.nextLine();
		boolean found = false;
		for (Transactions trans : db.getListOfTrans()) {
			if (trans.getTransId().equalsIgnoreCase(transId)) {
				found = true;
				System.out.println("Count of books you want to return: ");
				try{
					int count = Integer.parseInt(sc.nextLine());
					if (count > 10) {
						System.out.println("Exceeded the limit. Try again later");
					} else {
						if((count + trans.getBookObject().getNumOfBooksAvailable() <= 10) ) {
							processTheReturnOfBook(db, trans, count);	
						}else {
							System.out.println("No books to return or you are returning more books then you borrowed. ");	
						}
						
					}
				}catch (NumberFormatException ex) {
				    //handle exception here
				}
			}
		}
		if (!found) {
			System.out.println("Transaction ID not found.");
		}
	}

	private static void processTheReturnOfBook(Database db, Transactions trans, int count) {
		int index = db.getListOfTrans().indexOf(trans);
	//	booksBorrowedStatus = booksBorrowedStatus-count;
		db.getListOfTrans().get(index).setReturnDate(new Date());
		double fine = 0.0;
		if (db.getListOfTrans().get(index).getReturnDate().after(trans.getDueDate())) {
			fine = (3.0 * ((db.getListOfTrans().get(index).getReturnDate().getTime() - trans.getDueDate().getTime())
					/ (1000 * 60 * 60 * 24)) * count);
		}
		db.getListOfTrans().get(index).setFineAmount(fine);
		System.out.println("booksBorrowedStatus"+booksBorrowedStatus);
		System.out.println("db.getListOfTrans().get(index).getBookObject().getNumOfBooksAvailable());\r\n" + 
				"		"+db.getListOfTrans().get(index).getBookObject().getNumOfBooksAvailable());

		db.getListOfTrans().get(index).getBookObject().setNumOfBooksAvailable(db.getListOfTrans().get(index).getBookObject().getNumOfBooksAvailable() + count);
		

		//db.getListOfBook().get(index)
		//		.setNumOfBooksAvailable(db.getListOfBook().get(index).getNumOfBooksAvailable() + booksBorrowedStatus);
		
		//System.out.println(booksBorrowedStatus);
		
		//System.out.println(booksBorrowedStatus);
		if(db.getListOfTrans().get(index).getPendingBooks()>0) {
			db.getListOfTrans().get(index).setPendingBooks(db.getListOfTrans().get(index).getPendingBooks() - count);	
		}
		System.out.println(db.getListOfTrans().get(index));
		
	//	writer.open();
		WriteInFile(writer,db.getListOfTrans().get(index));

	}

	static void showBookRentOption(Database db) throws IOException, BackingStoreException {
		System.out
				.println("------------------------------------------------------------------------------------------");
		System.out.println("\t\t\t\tList Of Available Books\t\t\t\t");
		System.out.println("Book ID \tBook Name \t\t\t\t\t\t Book Quanitiy");
		System.out
				.println("-------------------------------------------------------------------------------------------");
		for (int i = 0; i < db.getListOfBook().size(); i++) {
			System.out.println(db.getListOfBook().get(i).getBookId() + "\t\t" + db.getListOfBook().get(i).getBookName()
					+ "\t\t" + db.getListOfBook().get(i).getNumOfBooksAvailable());
		}
		System.out.println("Enter the Book ID you want to take: ");
		String bookId = sc.nextLine();
		boolean found = false;
		for (Book book : db.getListOfBook()) {
			if (book.getBookId().equalsIgnoreCase(bookId)) {
				found = true;
				if (book.getNumOfBooksAvailable() > 0) {
					System.out.println("Count of books you want to take:(1,2,3...) ");
					try{
						int count = Integer.parseInt(sc.nextLine());
						if (count > book.getNumOfBooksAvailable()) {
							System.out.println("Exceeded the limit. Try again later");
						} else {
							processTheIssueOfBook(db, bookId, count,book);
						}
					}catch (NumberFormatException ex) {
					    //handle exception here
					}
				}
			}
		}
		if (!found) {
			System.out.println("Book ID not found..try again from the list of Book");
		}
	}

	private static void processTheIssueOfBook(Database db, String bookId, int count,Book bookObj) throws IOException, BackingStoreException {
		int index = 0;
		for (Book book : db.getListOfBook()) {
			if (book.getBookId().equalsIgnoreCase(bookId)) {
				index = db.getListOfBook().indexOf(book);
			}
		}
		System.out.println("Enter the member ID: ");
		String mem_id = sc.nextLine();
		
		
		
		for (Student student : db.getListOfStudent()) {
			if (student.getMemberId().equalsIgnoreCase(mem_id)) {
				objMember = student;
			}
		}
		
		for (Faculty faculty : db.getListOfFaculty()) {
			if (faculty.getMemberId().equalsIgnoreCase(mem_id)) {
				objMember = faculty;
			}
		}
		
		if(objMember==null){
			System.out.println("Not a Valid member Id.. Try again with Proper Member Id.");
			showMenu(db);
		}
		
		System.out.println("Enter Librarian ID: ");
		String lib_id = sc.nextLine();
		int dueCount = 0;
		if (mem_id.startsWith("S")) {
			dueCount = 5;
		} else if (mem_id.startsWith("F")) {
			dueCount = 7;
		}
		
		booksBorrowedStatus = count;
		db.getListOfTrans()
				.add(new Transactions(lib_id, new Date(), null, addDays(new Date(), dueCount), 0.0, lib_id, booksBorrowedStatus, objMember, bookObj));
		
		db.getListOfBook().get(index)
				.setNumOfBooksAvailable(db.getListOfBook().get(index).getNumOfBooksAvailable() - count);
		System.out.println(db.getListOfTrans().get(db.getListOfTrans().size() - 1));
		
		
		WriteInFile(writer,db.getListOfTrans().get(db.getListOfTrans().size() - 1));

	}

	static void LoadAuthor(List<Author> listOfAuthor) throws FileNotFoundException {
		File newFile = new File("Author.txt");

		Scanner authorFile = new Scanner(newFile);
		String bookAuthor = "";

		while (authorFile.hasNextLine()) {
			bookAuthor = authorFile.nextLine();

			String[] authorDetails = bookAuthor.split(",");

			// String authorId = authorDetails[0];
			String authorName = authorDetails[0];
			String authorDescription = authorDetails[1];

			listOfAuthor.add(new Author(authorName, authorDescription));
		}

		authorFile.close();

		for (Author author : listOfAuthor) {
			System.out.println(author);
		}

	}

	static void LoadPublisher(List<Publication> listOfPublication) throws FileNotFoundException {
		File newFile = new File("Publication.txt");

		Scanner publicationFile = new Scanner(newFile);
		String publisherDetail = "";

		while (publicationFile.hasNextLine()) {
			publisherDetail = publicationFile.nextLine();

			String[] publisherDetails = publisherDetail.split(",");

			// String publicationID = publisherDetails[0];
			String publicationName = publisherDetails[0];
			String publicationContact = publisherDetails[1];
			String publicationAddress = publisherDetails[2];

			listOfPublication.add(new Publication(publicationName, publicationContact, publicationAddress));

		}

		publicationFile.close();

		for (Publication publication : listOfPublication) {
			System.out.println(publication);
		}

	}

	static void LoadCategory(List<Category> listOfCategory) throws FileNotFoundException {

		File newFile = new File("Category.txt");

		Scanner categoryFile = new Scanner(newFile);
		String categoryDetail = "";

		while (categoryFile.hasNextLine()) {
			categoryDetail = categoryFile.nextLine();

			String[] categoryDetails = categoryDetail.split(",");

			String categoryName = categoryDetails[0];

			listOfCategory.add(new Category(categoryName));

		}

		categoryFile.close();

		for (Category category : listOfCategory) {
			System.out.println(category);
		}

	}

	static void LoadBook(List<Book> listOfBook) throws FileNotFoundException {
		File newFile = new File("Book.txt");

		Scanner bookFile = new Scanner(newFile);
		String bookDetail = "";

		while (bookFile.hasNextLine()) {
			bookDetail = bookFile.nextLine();

			String[] bookDetails = bookDetail.split(",");

			String bookName = bookDetails[0];
			String bookISBN = bookDetails[1];
			String bookPriceString = bookDetails[2];
			String authorId = bookDetails[3];
			String publisherId = bookDetails[4];
			String cateogryId = bookDetails[5];
			String numOfBooksAvailableString = bookDetails[6];

			double bookPrice = Double.parseDouble(bookPriceString);
			int numOfBooksAvailable = Integer.parseInt(numOfBooksAvailableString);

			listOfBook.add(
					new Book(bookName, bookISBN, bookPrice, authorId, publisherId, cateogryId, numOfBooksAvailable));

		}

		bookFile.close();

		for (Book book : listOfBook) {
			System.out.println(book);
		}

	}

	static void LoadStudent(List<Student> listOfStudent) throws FileNotFoundException, ParseException {

		File newFile = new File("Student.txt");

		Scanner studentFile = new Scanner(newFile);
		String studentDetail = "";

		// String startDateString = "06/27/2007";
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date memberJoiningDate;

		while (studentFile.hasNextLine()) {
			studentDetail = studentFile.nextLine();

			String[] studentDetails = studentDetail.split(",");

			String memberName = studentDetails[0];
			String memberJoinDateTemp = studentDetails[1];
			String memberAddress = studentDetails[2];
			String memberContact = studentDetails[3];
			String numOfBooks = studentDetails[4];
			String memberType = studentDetails[5];
			String gender = studentDetails[6];

			int bookHoldingLimit = Integer.parseInt(numOfBooks);
			memberJoiningDate = df.parse(memberJoinDateTemp);

			listOfStudent.add(new Student(memberName, memberJoiningDate, memberAddress, memberContact, bookHoldingLimit,
					memberType, gender));
			
			
		}

		studentFile.close();

		for (Student student : listOfStudent) {
			System.out.println(student);
		}

	}

	static void LoadFaculty(List<Faculty> listOfFaculty) throws FileNotFoundException, ParseException {

		File newFile = new File("Faculty.txt");

		Scanner facultyFile = new Scanner(newFile);
		String facultyDetail = "";

		// String startDateString = "06/27/2007";
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date memberJoiningDate;

		while (facultyFile.hasNextLine()) {
			facultyDetail = facultyFile.nextLine();

			String[] facultyDetails = facultyDetail.split(",");

			String memberName = facultyDetails[0];
			String memberJoinDateTemp = facultyDetails[1];
			String memberAddress = facultyDetails[2];
			String memberContact = facultyDetails[3];
			String numOfBooks = facultyDetails[4];
			String memberType = facultyDetails[5];
			String facultyDept = facultyDetails[6];
			String gender = facultyDetails[7];

			int bookHoldingLimit = Integer.parseInt(numOfBooks);
			memberJoiningDate = df.parse(memberJoinDateTemp);

			listOfFaculty.add(new Faculty(memberName, memberJoiningDate, memberAddress, memberContact, bookHoldingLimit,
					memberType, facultyDept, gender));

		}

		facultyFile.close();

		for (Faculty faculty : listOfFaculty) {
			System.out.println(faculty);
		}

	}
	



	static public Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		System.out.println(cal.getTime());
		return cal.getTime();
	}
	
	
	//REPORTS----------------------------------------------------------------------------------------------
	
	
	//Report 1:
	private static void report1() {
		System.out.println("---------------------FINE AMOUNT PER MONTH--------------------");
		
		double[] fineAmountArray = new double[12];
		for(int i=0;i<db.getListOfTrans().size();i++)
			{
			if(db.getListOfTrans().get(i).getReturnDate()!=null)
			{
				@SuppressWarnings("deprecation")
				int month = db.getListOfTrans().get(i).getReturnDate().getMonth();
				fineAmountArray[month] += db.getListOfTrans().get(i).getFineAmount();
			}
			}
		for(int i=0;i<12;i++) {
			System.out.println("Fine Amount for Month " + (i+1) + " is " + fineAmountArray[i]);
		}
		
	}
	private static void report2() {
		System.out.println("---------------------BOOKS THAT NEVER ISSUED--------------------");		
		
		
		Set<Book>bookSet = new HashSet<Book>();
		for(int i=0;i<db.getListOfBook().size();i++)
		{
			bookSet.add(db.getListOfBook().get(i));
		}
		
		Set<Book>transBookId = new HashSet<Book>();
		for(int j = 0;j<db.getListOfTrans().size();j++) {
			transBookId.add(db.getListOfTrans().get(j).getBookObject());
		}
		
		Set<Book> unionSet = new HashSet<Book>(bookSet);
		unionSet.addAll(transBookId);
		// unionSet now contains the union
		Set<Book> intersectionSet = new HashSet<Book>(bookSet);
		intersectionSet.retainAll(transBookId);
		// intersectionSet now contains the intersection
		unionSet.removeAll(intersectionSet);
		
		for (Book temp : unionSet) {
			System.out.println(temp + " :Issued: " + Collections.frequency(transBookId, temp)+ " times");
		}
		
		/*Set<String>bookID = new HashSet<String>();
		
		for(int i=0;i<db.getListOfBook().size();i++)
		{
			bookID.add(db.getListOfBook().get(i).getBookId());
		}
		
		
		Set<String>transBookId = new HashSet<String>();
		for(int j = 0;j<db.getListOfTrans().size();j++) {
			transBookId.add(db.getListOfTrans().get(j).getBookObject().getBookId());
		}
		Set<String> unionSet = new HashSet<String>(bookID);
		unionSet.addAll(transBookId);
		// unionSet now contains the union
		Set<String> intersectionSet = new HashSet<String>(bookID);
		intersectionSet.retainAll(transBookId);
		// intersectionSet now contains the intersection
		unionSet.removeAll(intersectionSet);
		System.out.println("Books that are never issued"+unionSet);*/
		
		
	}
	private static void report3() {
		System.out.println("----------------------BOOKS IN DEMAND--------------------");	
		int count = 5;
		
		ArrayList<Book>transBookId = new ArrayList<Book>();
		for(int j = 0;j<db.getListOfTrans().size();j++) {
			transBookId.add(db.getListOfTrans().get(j).getBookObject());
		}
		
		
		Set<Book> uniqueSet = new HashSet<Book>(transBookId);
		//Collections.sort(uniqueSet,Collections.reverseOrder());
	//	List<Book>top5Books = uniqueSet.subList(0, 5);
		
		for (Book temp : uniqueSet) {
			System.out.println(temp + " :Issued: " + Collections.frequency(transBookId, temp)+ " times");
		}
		
	}


	private static void report4() {
		System.out.println("----------------------MEMBERS DO LATE SUBMISSION--------------------");	
	
		for(int i=0;i<db.getListOfTrans().size();i++)

		{
			if(db.getListOfTrans().get(i).getFineAmount()>0)
			{
				System.out.println("Member ID "+db.getListOfTrans().get(i).getMembObject().getMemberId()+ " Name: "+db.getListOfTrans().get(i).getMembObject().getMemberName());
			}
		}
		
		
	}

	private static void report5() {
		System.out.println("----------------------MONTH OF MOST TRANSACTION--------------------");	
		ArrayList<Integer> monthArray = new ArrayList<Integer>();
		for(int i = 0;i<db.getListOfTrans().size();i++)
		{
			int month = db.getListOfTrans().get(i).getIssueDate().getMonth();
			monthArray.add(month+1);
		}
		
		Set<Integer> uniqueSet = new HashSet<Integer>(monthArray);
		for (Integer temp : uniqueSet) {
			System.out.println("Month:  "+temp + "Transactions: " + Collections.frequency(monthArray, temp));
		}
		
		
	}
	
	private static void WriteInFile(PrintWriter writer, Transactions transactions) {
		// TODO Auto-generated method stub
		writer.println("Transcation Id: " + transactions.getTransId());
		writer.println("Issue Date: " + transactions.getIssueDate());
		if(transactions.getReturnDate()!=null){
			writer.println("Return Date: " + transactions.getReturnDate());	
		}
		if(transactions.getDueDate()!=null){
			writer.println("Due Date: " + transactions.getDueDate());	
		}
		writer.println("Fine Amount: " + transactions.getFineAmount());
		writer.println("Lib Id: " + transactions.getLibId());
		writer.println("Pendig Books: " + transactions.getPendingBooks());
		writer.println("Member Id: " + transactions.getMembObject().getMemberId());
		writer.println("Member Name: " + transactions.getMembObject().getMemberName());
		writer.println("Member Type: " + transactions.getMembObject().getMemberType());
		writer.println("Book Id: "+ transactions.getBookObject().getBookId());
		writer.println("Book Name: "+ transactions.getBookObject().getBookName());
		writer.println("Number Of Books Available "+ transactions.getBookObject().getNumOfBooksAvailable());
		writer.close();
	}

}
