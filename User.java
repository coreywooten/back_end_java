
public class User {
	String username;
	int userscore;
	
	public void setUserscore(int userscore) {
		this.userscore += userscore;
	}
	public User(String username, int userscore) {
		this.username = username;
		this.userscore = userscore;
	}

	public int getUserscore() {
		return userscore;
	}

	
	public User() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public void print_user() {
		System.out.println("Username : "+username);
		System.out.println("UserScore : "+userscore);
	}
}
