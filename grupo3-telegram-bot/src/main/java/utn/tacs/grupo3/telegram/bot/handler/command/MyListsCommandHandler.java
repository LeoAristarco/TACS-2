package utn.tacs.grupo3.telegram.bot.handler.command;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.factory.MessageFactory;
import utn.tacs.grupo3.telegram.bot.handler.AbstractCommandHandler;
import utn.tacs.grupo3.telegram.bot.helper.HtmlHelper;

/**
 * Handler for /mylists handler
 */
public class MyListsCommandHandler extends AbstractCommandHandler{

	private static final String CLIPBOARD_EMOJI = "\uD83D\uDCCB ";
		
	@Override
	public List<BotApiMethod<?>> handle(Message message) {

		List<String> myListsNames = apiRequest.listNames(message.getFrom().getId());
		
		SendMessage answer = MessageFactory.createSendMessage(message)
				.setText(createText(myListsNames));
		
		return Arrays.asList(answer);
	}

	private String createText(List<String> myListsNames) {
		if (!myListsNames.isEmpty()) {
			StringBuilder text = new StringBuilder(HtmlHelper.bold("My lists")).append(HtmlHelper.multipleBr(2));
			
			myListsNames.forEach(name -> text.append(CLIPBOARD_EMOJI)
					.append(name)
					.append(HtmlHelper.br())
					.append("See places: ")
					.append(PlacesBotConstants.VIEW_LIST_COMMAND).append(PlacesBotConstants.COMMAND_SEPARATOR)
					.append(name.toLowerCase().replace(" ", PlacesBotConstants.COMMAND_SEPARATOR))
					.append(HtmlHelper.multipleBr(2))
					);
			
			return text.toString();						
		} else {
			return "You don't have any list of places";			
		}
	}
	
	

}
