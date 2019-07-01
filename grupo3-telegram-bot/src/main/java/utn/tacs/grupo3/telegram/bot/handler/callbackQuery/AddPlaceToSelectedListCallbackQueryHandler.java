package utn.tacs.grupo3.telegram.bot.handler.callbackQuery;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.factory.MessageFactory;
import utn.tacs.grupo3.telegram.bot.handler.CallbackQueryHandler;

public class AddPlaceToSelectedListCallbackQueryHandler implements CallbackQueryHandler {

	@Override
	public List<BotApiMethod<?>> handle(CallbackQuery callbackQuery) {
		String[] parsed = callbackQuery.getData()
				.split(PlacesBotConstants.ADD_PLACE_TO_SELECTED_LIST + PlacesBotConstants.COMMAND_SEPARATOR)[1]
						.split(PlacesBotConstants.COMMAND_SEPARATOR);

		apiRequest.addPlaceToList(
				parsed[0],
				parsed[1], 
				callbackQuery.getFrom().getId());

		SendMessage answer = MessageFactory.createSendMessage(callbackQuery.getMessage())
				.setText("Place added to list successfully");

		return Arrays.asList(answer);
	}

}
