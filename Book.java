import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Book {
	String name;
	String author;
	int year;
	int BookScore;
	boolean is_available;
	User Occupied_by_user;
	User Registered_user;
	Calendar occypying_date;
	Calendar deadline;
	public Calendar getDeadline() {
		return deadline;
	}
	public void setDeadline() {
		deadline = new GregorianCalendar();
		deadline.add(Calendar.DATE, 30);
	}
	public Calendar getC() {
		return occypying_date;
	}
	public void setC() {
		occypying_date=new GregorianCalendar();
	}
	public User getRegistered_user() {
		return Registered_user;
	}
	public void setRegistered_user(User registered_user) {
		Registered_user = registered_user;
	}
	public Book(String name, String author, int year, int bookScore, boolean is_available, User RU) {
		this.name = name;
		this.author = author;
		this.year = year;
		this.BookScore = bookScore;
		this.is_available = is_available;
		this.Registered_user = RU;
	}
	public User getOccupied_by_user() {
		return Occupied_by_user;
	}
	public void setOccupied_by_user(User occupied_by_user) {
		Occupied_by_user = occupied_by_user;
	}
	public boolean getIs_available() {
		return is_available;
	}
	public void setIs_available(boolean is_available) {
		this.is_available = is_available;
	}
	public int getBookScore() {
		return BookScore;
	}
	public void setBookScore(int bookScore) {
		BookScore += bookScore;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void print_book() {
		System.out.println("Book name : "+name);
		System.out.println("author name : "+author);
		System.out.println("Book year : "+year);
		System.out.println("Register by");
		Registered_user.print_user();
		if(!is_available) {
			System.out.println("Occupied by");
			Occupied_by_user.print_user();
			Date datee = occypying_date.getTime();
			DateFormat currentDate = DateFormat.getDateInstance();
			System.out.println("Occupying Date : "+currentDate.format(datee));
			datee = deadline.getTime();
			System.out.println("Deadline : "+currentDate.format(datee));
			
			
		}
		else
			System.out.println("The book is available to  occupy");
	}
	
}
