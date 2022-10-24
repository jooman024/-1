package database;

public class UserData {
	public UserData() {
	}
	private boolean LoginCk(String id, String pw) {
		if (id == "aaaa" && pw == "1234") {
			return true;
		}
		else {
			return false;
		}
	}

}