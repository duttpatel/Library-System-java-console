package Model;

import java.util.ArrayList;
import java.util.List;

import View.Author;
import View.Book;
import View.Category;
import View.Faculty;
import View.Member;
import View.Publication;
import View.Student;
import View.Transactions;

public class Database {

	private List<Author> listOfAuthor = new ArrayList<Author>();
	private List<Publication> listOfPublication = new ArrayList<Publication>();
	private List<Category> listOfCategory = new ArrayList<Category>();
	private List<Book> listOfBook = new ArrayList<Book>();
	private List<Student> listOfStudent = new ArrayList<Student>();
	private List<Faculty> listOfFaculty = new ArrayList<Faculty>();
	private List<Transactions> listOfTrans = new ArrayList<Transactions>();
	private List<Member> listOfMember = new ArrayList<Member>();
	
	/**
	 * @return the listOfAuthor
	 */
	public List<Author> getListOfAuthor() {
		return listOfAuthor;
	}
	/**
	 * @param listOfAuthor the listOfAuthor to set
	 */
	public void setListOfAuthor(List<Author> listOfAuthor) {
		this.listOfAuthor = listOfAuthor;
	}
	/**
	 * @return the listOfPublication
	 */
	public List<Publication> getListOfPublication() {
		return listOfPublication;
	}
	/**
	 * @param listOfPublication the listOfPublication to set
	 */
	public void setListOfPublication(List<Publication> listOfPublication) {
		this.listOfPublication = listOfPublication;
	}
	/**
	 * @return the listOfCategory
	 */
	public List<Category> getListOfCategory() {
		return listOfCategory;
	}
	/**
	 * @param listOfCategory the listOfCategory to set
	 */
	public void setListOfCategory(List<Category> listOfCategory) {
		this.listOfCategory = listOfCategory;
	}
	/**
	 * @return the listOfBook
	 */
	public List<Book> getListOfBook() {
		return listOfBook;
	}
	/**
	 * @param listOfBook the listOfBook to set
	 */
	public void setListOfBook(List<Book> listOfBook) {
		this.listOfBook = listOfBook;
	}
	/**
	 * @return the listOfStudent
	 */
	public List<Student> getListOfStudent() {
		return listOfStudent;
	}
	/**
	 * @param listOfStudent the listOfStudent to set
	 */
	public void setListOfStudent(List<Student> listOfStudent) {
		this.listOfStudent = listOfStudent;
	}
	/**
	 * @return the listOfFaculty
	 */
	public List<Faculty> getListOfFaculty() {
		return listOfFaculty;
	}
	/**
	 * @param listOfFaculty the listOfFaculty to set
	 */
	public void setListOfFaculty(List<Faculty> listOfFaculty) {
		this.listOfFaculty = listOfFaculty;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public List<Member> getListOfMember() {
		return listOfMember;
	}
	public void setListOfMember(List<Member> listOfMember) {
		this.listOfMember = listOfMember;
	}	
	
	@Override
	public String toString() {
		return "DataBase [listOfAuthor=" + listOfAuthor + ", listOfPublication=" + listOfPublication
				+ ", listOfCategory=" + listOfCategory + ", listOfBook=" + listOfBook + ", listOfStudent="
				+ listOfStudent + ", listOfFaculty=" + listOfFaculty + "]";
	}

	public List<Transactions> getListOfTrans() {
		return listOfTrans;
	}
	public void setListOfTrans(List<Transactions> listOfTrans) {
		this.listOfTrans = listOfTrans;
	}

	
}
