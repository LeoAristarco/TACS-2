package utn.tacs.grupo3.telegram.bot.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores logged users in a map (Key: TelegramId, Value: Username) 
 *
 */
public final class LoggedUsers {
	
	private static Map<Integer, LoggedUser> loggedUsers;
	
	static {
		loggedUsers = new HashMap<Integer, LoggedUser>();
	}
	
	public static String getUsername(Integer telegramId) {
		return loggedUsers.get(telegramId).getUserName();
	}
	
//	public static String getChatId(Integer telegramId) {
//		return loggedUsers.get(telegramId).getChatId();
//	}
	
	public static String getToken(Integer telegramId) {
		return loggedUsers.get(telegramId).getToken();
	}
	
	public static void addLoggedUser(Integer telegramId, String username, String token) {
		loggedUsers.put(telegramId, new LoggedUser(username, token));
	}
	
	public static void removeLoggedUser(Integer telegramId) {
		loggedUsers.remove(telegramId);
	}
	
	public static boolean isLogged(Integer telegramId) {
		return loggedUsers.containsKey(telegramId);
	}

}
