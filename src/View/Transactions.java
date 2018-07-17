package View;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class Transactions {

	private String transId;
	private Date issueDate;
	private Date returnDate;
	private Date dueDate;
	private double fineAmount;

	private String libId;
	private static int transCount = 500001;
	private int pendingBooks;
	private Member membObject;
	private Book bookObject;
	
	public static String ID = "TranscationId";
	
	public static Preferences prefs = Preferences.userRoot().node(Transactions.class.getName());

	public Transactions(String transId, Date issueDate, Date returnDate,
			Date dueDate, double fineAmount, String libId, int pendingBooks,
			Member membObject, Book bookObject) {
		super();
		this.transId = autoIncrementId();
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.dueDate = dueDate;
		this.fineAmount = fineAmount;
		this.libId = libId;
		this.pendingBooks = pendingBooks;
		this.membObject = membObject;
		this.bookObject = bookObject;
	}

	public String autoIncrementId() {
		
		int tempId = prefs.getInt(ID, transCount);
		
		String transId =  "T" + tempId++;
		
		 prefs.putInt(ID, tempId);

		/* prefs.remove(ID);
		 try {
			prefs.clear();
		} catch (BackingStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return transId;
		
	}
	
/*public String autoIncrementId() {
		return "T" + transCount++;
		
	}*/

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}

	public String getLibId() {
		return libId;
	}

	public void setLibId(String libId) {
		this.libId = libId;
	}

	public static int getTransCount() {
		return transCount;
	}

	public static void setTransCount(int transCount) {
		Transactions.transCount = transCount;
	}

	public int getPendingBooks() {
		return pendingBooks;
	}

	public void setPendingBooks(int pendingBooks) {
		this.pendingBooks = pendingBooks;
	}

	public Member getMembObject() {
		return membObject;
	}

	public void setMembObject(Member membObject) {
		this.membObject = membObject;
	}

	public Book getBookObject() {
		return bookObject;
	}

	public void setBookObject(Book bookObject) {
		this.bookObject = bookObject;
	}

	@Override
	public String toString() {
		return "Transactions [transId=" + transId + ", issueDate=" + issueDate
				+ ", returnDate=" + returnDate + ", dueDate=" + dueDate
				+ ", fineAmount=" + fineAmount + ", libId=" + libId
				+ ", pendingBooks=" + pendingBooks + ", membObject="
				+ membObject.toString() + ", bookObject="
				+ bookObject.toString() + "]";
	}

}
