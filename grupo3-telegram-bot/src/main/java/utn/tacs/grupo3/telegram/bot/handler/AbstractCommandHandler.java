package utn.tacs.grupo3.telegram.bot.handler;

import org.telegram.telegrambots.meta.api.objects.User;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.request.ApiRequest;
import utn.tacs.grupo3.telegram.bot.request.ApiRequestImpl;
import utn.tacs.grupo3.telegram.bot.user.LoggedUsers;
import utn.tacs.grupo3.telegram.bot.user.LoginStatusChecker;

public abstract class AbstractCommandHandler implements CommandHandler{
	
	protected LoginStatusChecker loginStatusChecker;
	
	protected ApiRequest apiRequest;
	
	public AbstractCommandHandler() {}
		
	public AbstractCommandHandler(LoginStatusChecker loginStatusChecker) {
		this.loginStatusChecker = loginStatusChecker;
		this.apiRequest = new ApiRequestImpl();
	}

	protected String getUsername(String messageText) {
		return messageText.replaceAll("\\s+", "").split(PlacesBotConstants.LOGIN_COMMAND)[1].split(",")[0];
	}
	
	protected String getPassword(String messageText) {
		return messageText.replaceAll("\\s+", "").split(PlacesBotConstants.LOGIN_COMMAND)[1].split(",")[1];
	}
	
	protected String getUsernameByUser(User user) {
		return LoggedUsers.getUsername(user.getId());
	}
	
}