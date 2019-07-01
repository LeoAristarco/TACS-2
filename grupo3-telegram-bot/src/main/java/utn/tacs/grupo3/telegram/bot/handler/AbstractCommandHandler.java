package utn.tacs.grupo3.telegram.bot.handler;


import org.telegram.telegrambots.meta.api.objects.User;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.user.LoggedUsers;

public abstract class AbstractCommandHandler implements CommandHandler{

	public AbstractCommandHandler() {}
	
	protected String getUsernameByUser(User user) {
		return LoggedUsers.getUsername(user.getId());
	}
	
	protected String removeCommandFromMessageText(String message, String command) {
		if (message.startsWith(command + PlacesBotConstants.COMMAND_SEPARATOR)) {
			return message.substring(command.length() + 1).replace(PlacesBotConstants.COMMAND_SEPARATOR, " ");
		} else {
			return message.substring(command.length());
		}
			
	}
	
}
