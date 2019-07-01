package utn.tacs.grupo3.telegram.bot.handler.command;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.factory.MessageFactory;
import utn.tacs.grupo3.telegram.bot.factory.ReplyKeyboardFactory;
import utn.tacs.grupo3.telegram.bot.handler.CommandHandler;
import utn.tacs.grupo3.telegram.bot.helper.HtmlHelper;
import utn.tacs.grupo3.telegram.bot.user.LoggedUsers;

/**
 * Handler for /help command
 */
public class HelpCommandHandler implements CommandHandler{

	@Override
	public List<BotApiMethod<?>> handle(Message message) {
		SendMessage answer = MessageFactory.createSendMessage(message);
		String text;
		
		if (LoggedUsers.isLogged(message.getFrom().getId())) {
			text = HtmlHelper.formatText(
					"Use the below keyboard to send a command or type one of the following",
					HtmlHelper.multipleBr(2),
					PlacesBotConstants.MY_LISTS_COMMAND, " to see your list of places", HtmlHelper.br(),
					PlacesBotConstants.SEARCH_COMMAND, " to search places and add them to your lists", HtmlHelper.br(),
					PlacesBotConstants.LOGOUT_COMMAND, " to end your session"
					);
			answer.setReplyMarkup(ReplyKeyboardFactory.createCommandKeyboard());
			
		} else {
			text = HtmlHelper.formatText(
					HtmlHelper.bold("Welcome to UTN Places Bot"),
					HtmlHelper.multipleBr(2),
					"To login, please send your username and password as follows",
					HtmlHelper.br(),
					"/login USERNAME PASSWORD"
					);
			answer.setReplyMarkup(ReplyKeyboardFactory.createInitialKeyBoard());
		}
		
		answer.setText(text);
		
		return Arrays.asList(answer);
	}
}
