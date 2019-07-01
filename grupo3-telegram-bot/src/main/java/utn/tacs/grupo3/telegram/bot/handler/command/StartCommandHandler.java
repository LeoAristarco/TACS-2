package utn.tacs.grupo3.telegram.bot.handler.command;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import utn.tacs.grupo3.telegram.bot.factory.MessageFactory;
import utn.tacs.grupo3.telegram.bot.factory.ReplyKeyboardFactory;
import utn.tacs.grupo3.telegram.bot.handler.CommandHandler;
import utn.tacs.grupo3.telegram.bot.helper.HtmlHelper;

/**
 * Handler for /start command
 */
public class StartCommandHandler implements CommandHandler{
	
	@Override
	public List<BotApiMethod<?>> handle(Message message) {
		String text = HtmlHelper.formatText(
				HtmlHelper.bold("Welcome to UTN Places Bot"),
				HtmlHelper.multipleBr(2),
				"Please send your username and password as follows",
				HtmlHelper.br(),
				"/login USERNAME PASSWORD"
				);
				
		SendMessage answer = MessageFactory.createSendMessage(message)
				.setText(text)
				.setReplyMarkup(ReplyKeyboardFactory.createKeyboardRemove());
		
		return Arrays.asList(answer);
	}

}