package utn.tacs.grupo3.telegram.bot.user;

public class LoggedUser {
	
	private String userName;
//	private String chatId;
	private String token;
		
//	public LoggedUser(String userName, String chatId) {
//		super();
//		this.userName = userName;
//		this.chatId = chatId;
//	}
	
	public LoggedUser(String userName, String token) {
		super();
		this.userName = userName;
//		this.chatId = chatId;
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public String getUserName() {
		return userName;
	}

}
