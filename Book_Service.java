import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Book_Service {
	ArrayList<Book> books = new ArrayList<Book>();
	BufferedReader bf;
	ArrayList<User> users = new ArrayList<User>();
	int userindex = -1;
	
	public void menu() throws IOException {
		 bf = new BufferedReader(new InputStreamReader(System.in));
		 char s = '0';
		 while(s != '3') {
			System.out.println("Press 1 for SignIn");
		 	System.out.println("Press 2 for SignUp");
		 	System.out.println("Press 3 to Exit");
		 	s = bf.readLine().charAt(0);
		 	boolean in = false;
		 	if (s == '1') {
		 		in = signin(bf);
		 		if(!in) {
		 			System.out.println("No such username exist");
		 		}
		 	}
		 	else if(s == '2') {
		 		signup(bf);
		 		in = true;
		 	}
		 	else {
			 	System.out.println("Invalid Option");
		 	}
		 	if(in) {
		 		char s1 = '0';
		 		while(s1 != '8') {
			 		System.out.println("Press 1 to Register Books");
				 	System.out.println("Press 2 to track users and books");
				 	System.out.println("Press 3 to take a book");
				 	System.out.println("Press 4 to return a book");
				 	System.out.println("Press 5 to display users stats");
				 	System.out.println("Press 6 search a book by applying filters");
				 	System.out.println("Press 7 to get Top_N books");
				 	System.out.println("Press 8 to log out");
				 	s1 = bf.readLine().charAt(0);
				 	if(s1 == '1') {
				 		System.out.println("Enter the number of books : ");
				 		Task_1_register_books(Integer.valueOf(bf.readLine()), bf);
				 	}
				 	else if(s1 == '2') {
				 		Task_2_track();
				 	}
				 	else if(s1 == '3') {
				 		Task_5_search_book(0, "", "");
				 		System.out.print("Enter the book number you want to take : ");
				 		int ind = Integer.valueOf(bf.readLine());
				 		Task_3_take_book(ind);
				 	}
				 	else if(s1 == '4') {
				 		System.out.print("Enter the book name you want to return : ");
				 		String name = bf.readLine();
				 		System.out.print("Enter the book's year you want to return : ");
				 		int yearr = Integer.parseInt(bf.readLine());
				 		Task_4_return_book(name, yearr, bf);
				 	}
				 	else if(s1 == '5') {
				 		int len = users.size();
				 		for(int i=0;i<len;++i) {
				 			users.get(i).print_user();
				 			System.out.println("*****************************************");
				 		}
				 	}
				 	else if(s1 == '6') {
				 		System.out.print("Enter the book name if you want to filter by book name or enter 0 if dont : ");
				 		String name = bf.readLine();
				 		System.out.print("Enter the author name if you want to filter by author name or enter 0 if dont : ");
				 		String author = bf.readLine();
				 		System.out.print("Enter the book's year if you want to filter by year or enter 0 if dont : ");
				 		int year = Integer.valueOf(bf.readLine());
				 		if(name.charAt(0) == '0')
				 			name = "";
				 		if(author.charAt(0) == '0')
				 			author = "";
				 		Task_5_search_book(year, name, author);
				 	}
				 	else if(s1 == '7') {
				 		System.out.print("Enter the Top N number : ");
				 		int number = Integer.parseInt(bf.readLine());
				 		Task_6_top_n(number);
				 	}
				 	else if(s1 == '8') {
				 		userindex = -1;
				 	}
		 		}
		 	}
		 }
	}
	public void signup(BufferedReader bf) throws IOException {
		System.out.print("Enter your username : ");
		String name = bf.readLine();
		users.add(new User(name, 0));
		userindex = users.size() - 1;
	}
	public void Task_3_take_book(int book_index) {
		books.get(book_index).setDeadline();
		books.get(book_index).setC();
		books.get(book_index).setIs_available(false);
		books.get(book_index).setOccupied_by_user(users.get(userindex));
		books.get(book_index).getRegistered_user().setUserscore(5);
	}
	public boolean signin(BufferedReader bf) throws IOException {
		System.out.print("Enter your username : ");
		String name = bf.readLine();
		int len = users.size();
		for(int i = 0;i < len; ++i) {
			if(users.get(i).getUsername().equals(name)) {
				userindex = i;
				return true;
			}
		}
		return false;
	}
	public void Task_2_track() {
		int len = books.size();
		for(int i=0;i<len;++i) {
			Book bk = books.get(i);
			if (!bk.getIs_available()) {
				bk.print_book();
				System.out.println("**************************************");
			}
		}
	}
	public void logout() {
		userindex = -1;
	}
	public void Task_1_register_books(int no_of_books, BufferedReader bf) throws IOException {
		User us = users.get(userindex);
		for(int i=0;i<no_of_books;++i) {
			System.out.print("Enter the book name : ");
			String book_name = bf.readLine();
			System.out.print("Enter the book's author name : ");
			String author_name = bf.readLine();
			System.out.print("Enter the book's year : ");
			String year = bf.readLine();
			System.out.println("**************************************************");
			books.add(new Book(book_name, author_name, Integer.valueOf(year), 0, true, us));
		}
		users.get(userindex).setUserscore(no_of_books);
	}
	public void Task_5_search_book(int year, String book_name, String author_name) {
		int len = books.size();
		for (int i=0;i<len;++i) {
			boolean year1 = false;
			boolean name1 = false;
			boolean author1 = false;
			Book bk = books.get(i);
			if (year!=0) {
				if(year == bk.getYear())
					year1 = true;
			}
			else
				year1 = true;
			if(book_name != "") {
				if(book_name.equals(bk.getName()))
					name1 = true;
			}
			else
				name1 = true;
			if(author_name != "") {
				if(author_name.equals(bk.getAuthor()))
					author1 = true;
			}
			else
				author1 = true;
			if(name1 && author1 && year1) {
				System.out.println("Book number : "+i);
				bk.print_book();
				System.out.println("*****************************");
			}
		}
	}
	public void Task_4_return_book(String book_name, int year, BufferedReader bf) throws IOException {
		int ind = -1;
		int len = books.size();
		for(int i=0;i<len;++i) {
			Book bk = books.get(i);
			if(bk.getYear() == year && bk.getName().equals(book_name)) {
				ind = i;
				break;
			}
		}
		if(ind == -1) {
			System.out.println("No such book exist in library");
		}
		else {
			System.out.println("Is book is in bad condition or not (y for yes / N for no)");
			char r = bf.readLine().charAt(0);
			if (r == 'y') {
				books.get(ind).getOccupied_by_user().setUserscore(-10);
			}
			else {
				books.get(ind).getOccupied_by_user().setUserscore(2);
			}
			Calendar ob1 = books.get(ind).getDeadline();
			Calendar ob2 = new GregorianCalendar();
			long daysBetween = ChronoUnit.DAYS.between(ob1.toInstant(), ob2.toInstant());
			if (daysBetween > 0) {
				books.get(ind).getOccupied_by_user().setUserscore((int)daysBetween*-2);
			}
			books.get(ind).setOccupied_by_user(null);
			books.get(ind).setIs_available(true);
			System.out.println("Do you like this book (y for yes / N for no)");
			r = bf.readLine().charAt(0);
			if (r == 'y') {
				books.get(ind).setBookScore(3);
				for(int i = ind; i>0;--i) {
					Book bk = books.get(i);
					if(bk.getBookScore() > books.get(i-1).getBookScore()) {
						books.set(i, books.get(i-1));
						books.set(i-1, bk);
					}
					else
						break;
				}
			}
			else {
				books.get(ind).setBookScore(-3);
				for(int i = ind; i<len-1;++i) {
					Book bk = books.get(i);
					if(bk.getBookScore() < books.get(i+1).getBookScore()) {
						books.set(i, books.get(i+1));
						books.set(i+1, bk);
					}
					else
						break;
				}
			}
		}
	}
	public void Task_6_top_n(int number) {
		for(int i=0;i<number;++i) {
			books.get(i).print_book();
			System.out.println("**********************************");
		}
	}

}
