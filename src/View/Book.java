package View;

public class Book implements Comparable {
	private String bookId;

	private String bookName;

	private String bookISBN;

	private Double bookPrice;

	private String authorId;

	private String publisherId;

	private String cateogryId;

	private int numOfBooksAvailable;

	private static int bookCount = 101;

	public Book(String bookName, String bookISBN, Double bookPrice, String authorId, String publisherId,
			String cateogryId, int numOfBooksAvailable) {
		super();
		this.bookId = autoIncrementId();
		this.bookName = bookName;
		this.bookISBN = bookISBN;
		this.bookPrice = bookPrice;
		this.authorId = authorId;
		this.publisherId = publisherId;
		this.cateogryId = cateogryId;
		this.numOfBooksAvailable = numOfBooksAvailable;
	}

	public String autoIncrementId() {
		String tempBookCount = "B" + bookCount;
		bookCount += 1;
		return tempBookCount;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookISBN() {
		return bookISBN;
	}

	public void setBookISBN(String bookISBN) {
		this.bookISBN = bookISBN;
	}

	public Double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(Double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(String publisherId) {
		this.publisherId = publisherId;
	}

	public String getCateogryId() {
		return cateogryId;
	}

	public void setCateogryId(String cateogryId) {
		this.cateogryId = cateogryId;
	}

	public int getNumOfBooksAvailable() {
		return numOfBooksAvailable;
	}

	public void setNumOfBooksAvailable(int numOfBooksAvailable) {
		this.numOfBooksAvailable = numOfBooksAvailable;
	}

	public static int getBookCount() {
		return bookCount;
	}

	public static void setBookCount(int bookCount) {
		Book.bookCount = bookCount;
	}

	public String toString() {
		return this.bookId + " " + this.bookName + " " + this.bookISBN + " " + this.bookPrice + " " + this.authorId
				+ " " + this.publisherId + " " + this.cateogryId + " " + this.numOfBooksAvailable;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
