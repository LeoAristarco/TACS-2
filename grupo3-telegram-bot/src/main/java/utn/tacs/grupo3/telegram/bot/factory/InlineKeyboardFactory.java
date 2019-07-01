package utn.tacs.grupo3.telegram.bot.factory;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.request.entity.Venue;

public final class InlineKeyboardFactory {

	private InlineKeyboardFactory() {}
	
	public static InlineKeyboardMarkup createSearchKeyboard() {
		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
		
		List<List<InlineKeyboardButton>> buttons = Arrays.asList();		
		
		keyboard.setKeyboard(buttons);
		return keyboard;
	}
	
	public static InlineKeyboardMarkup createAddPlaceToListKeyboard(Venue venue) {
		return createInlineKeyboard(Arrays.asList(
				new InlineKeyboardButton("Add to list").setCallbackData(PlacesBotConstants.ADD_PLACE_TO_LIST + PlacesBotConstants.COMMAND_SEPARATOR + venue.getId()))
				);
	}
	
	public static InlineKeyboardMarkup createAddPlaceToSelectedListKeyboard() {
		return new InlineKeyboardMarkup();
	}
	
	@SafeVarargs
	public static InlineKeyboardMarkup createInlineKeyboard(List<InlineKeyboardButton>... buttonRows) {
		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();		
		keyboard.setKeyboard(Arrays.asList(buttonRows));
		return keyboard;
	}

	
}
