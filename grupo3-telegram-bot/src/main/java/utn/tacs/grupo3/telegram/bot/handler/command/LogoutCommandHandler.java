package utn.tacs.grupo3.telegram.bot.handler.command;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import utn.tacs.grupo3.telegram.bot.factory.MessageFactory;
import utn.tacs.grupo3.telegram.bot.factory.ReplyKeyboardFactory;
import utn.tacs.grupo3.telegram.bot.handler.AbstractCommandHandler;
import utn.tacs.grupo3.telegram.bot.helper.HtmlHelper;

/**
 * Handler for /logout command
 */
public class LogoutCommandHandler extends AbstractCommandHandler{

	@Override
	public List<BotApiMethod<?>> handle(Message message) {

		apiRequest.logout(message.getFrom().getId());
		
		SendMessage answer = MessageFactory.createSendMessage(message)
				.setText(HtmlHelper.bold("Successful logout"))
				.setReplyMarkup(ReplyKeyboardFactory.createInitialKeyBoard());
		
		return Arrays.asList(answer);
		
	}

}
