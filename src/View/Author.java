package View;

public class Author {
	private String authorId;
	
	private String authorName;
	
	private String authorDescription;
	
	private static int authorCount = 101;
	
	public Author(String authorName, String authorDescription) {
		super();
		this.authorId = autoIncrementId();
		this.authorName = authorName;
		this.authorDescription = authorDescription;
	}
	
	public String autoIncrementId(){
		String tempAuthorCount = "A" + authorCount;
        authorCount += 1;
        return tempAuthorCount;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorDescription() {
		return authorDescription;
	}

	public void setAuthorDescription(String authorDescription) {
		this.authorDescription = authorDescription;
	}
	
	public String toString(){
	    return this.authorId + " " + this.authorName + " " + this.authorDescription;
	}
}
