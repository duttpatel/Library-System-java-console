package View;

import java.util.Date;

public class Member {
	private String memberId;
	
	private String memberName;
	
	private Date memberJoiningDate;
	
	private String memberAddress;
	
	private String memberContact;
	
	private int bookHoldingLimit;
	
	private String memberType;

	private static int studentCount = 1001;
	
	private static int facultyCount = 1001;
	

	public Member(String memberName, Date memberJoiningDate,
			String memberAddress, String memberContact, int bookHoldingLimit,String memberType) {
		super();
		this.memberName = memberName;
		this.memberJoiningDate = memberJoiningDate;
		this.memberAddress = memberAddress;
		this.memberContact = memberContact;
		this.bookHoldingLimit = bookHoldingLimit;
		this.memberType = memberType;
		this.memberId = autoIncrementId();
	}
	
	public Member() {
		
	}
	
	public String autoIncrementId(){
		
		String memberId =getMemberType();
		char firstChar = memberId.charAt(0);
		
		if(firstChar == 'F'){
			String tempFacultyCount = "F" + facultyCount;
			facultyCount += 1;
	        return tempFacultyCount;
		}else if(firstChar == 'S'){
			String tempStudentCount = "S" + studentCount;
			studentCount += 1;
	        return tempStudentCount;			
		}else{
			System.out.println("Invalid Entry");
		}
		
		return "";

	}
	
	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getMemberJoiningDate() {
		return memberJoiningDate;
	}

	public void setMemberJoiningDate(Date memberJoiningDate) {
		this.memberJoiningDate = memberJoiningDate;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public String getMemberContact() {
		return memberContact;
	}

	public void setMemberContact(String memberContact) {
		this.memberContact = memberContact;
	}

	public int getBookHoldingLimit() {
		return bookHoldingLimit;
	}

	public void setBookHoldingLimit(int bookHoldingLimit) {
		this.bookHoldingLimit = bookHoldingLimit;
	}
}
