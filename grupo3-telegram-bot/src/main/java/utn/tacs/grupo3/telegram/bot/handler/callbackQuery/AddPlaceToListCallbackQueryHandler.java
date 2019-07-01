package utn.tacs.grupo3.telegram.bot.handler.callbackQuery;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.handler.CallbackQueryHandler;

public class AddPlaceToListCallbackQueryHandler implements CallbackQueryHandler{

	@Override
	public List<BotApiMethod<?>> handle(CallbackQuery callbackQuery) {
		
		List<String> listNames = apiRequest.listNames(callbackQuery.getFrom().getId());

		String placeId = callbackQuery.getData().split(PlacesBotConstants.COMMAND_SEPARATOR)[1];	
		
		SendMessage answer = new SendMessage()
				.setChatId(callbackQuery.getFrom().getId().toString())
				.setText("Select a list to add a new place")
				.setReplyMarkup(createKeyboard(placeId, listNames));
		
		return Arrays.asList(answer);
	}

	private InlineKeyboardMarkup createKeyboard(String placeId, List<String> listNames) {
		InlineKeyboardMarkup kb = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> buttons = listNames.stream()
				.map(listName -> 
					Arrays.asList(new InlineKeyboardButton(listName).setCallbackData(
							PlacesBotConstants.ADD_PLACE_TO_SELECTED_LIST + PlacesBotConstants.COMMAND_SEPARATOR + 
							listName + PlacesBotConstants.COMMAND_SEPARATOR + 
							placeId)))
				.collect(Collectors.toList());
		
		kb.setKeyboard(buttons);
		return kb;
	}

}
