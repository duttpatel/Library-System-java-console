package View;

public class Publication {
	
	private String publicationID;
	
	private String publicationName;
	
	private String publicationContact;
	
	private String publicationAddress;
	
	private static int publisherCount = 101;

	public Publication(String publicationName,
			String publicationContact, String publicationAddress) {
		super();
		this.publicationID = autoIncrementId();
		this.publicationName = publicationName;
		this.publicationContact = publicationContact;
		this.publicationAddress = publicationAddress;
	}
	
	public String autoIncrementId(){
		String tempPublisherCount = "P" + publisherCount;
		publisherCount += 1;
        return tempPublisherCount;
	}
	
	public String getPublicationID() {
		return publicationID;
	}

	public void setPublicationID(String publicationID) {
		this.publicationID = publicationID;
	}

	public String getPublicationName() {
		return publicationName;
	}

	public void setPublicationName(String publicationName) {
		this.publicationName = publicationName;
	}

	public String getPublicationContact() {
		return publicationContact;
	}

	public void setPublicationContact(String publicationContact) {
		this.publicationContact = publicationContact;
	}

	public String getPublicationAddress() {
		return publicationAddress;
	}

	public void setPublicationAddress(String publicationAddress) {
		this.publicationAddress = publicationAddress;
	}
	
	public String toString(){
	    return this.publicationID + " " + this.publicationName + " " + this.publicationContact + " " + this.publicationAddress;
	}

}
