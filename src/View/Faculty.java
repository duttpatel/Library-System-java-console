package View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Faculty extends Member {

	private String facultyDept;
	
	private String gender;	
	DateFormat df = new SimpleDateFormat("yyyy/MM/dd"); 

	public String getFacultyDept() {
		return facultyDept;
	}

	public void setFacultyDept(String facultyDept) {
		this.facultyDept = facultyDept;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	
	public Faculty(String memberName, Date memberJoiningDate,
			String memberAddress, String memberContact, int bookHoldingLimit,String memberType,String facultyDept,String gender) {
		super(memberName, memberJoiningDate, memberAddress, memberContact,
				bookHoldingLimit,memberType);
		// TODO Auto-generated constructor stub
		this.facultyDept = facultyDept;
		this.gender = gender;
	}
	
	public String toString(){
	    return super.getMemberId() + " " + super.getMemberName() + " " + super.getMemberAddress() + " " + super.getMemberContact() + " " + 
	    		df.format(super.getMemberJoiningDate()) + " "
	    		+ super.getBookHoldingLimit() + " " + this.gender + " " + this.facultyDept;
	}

}
