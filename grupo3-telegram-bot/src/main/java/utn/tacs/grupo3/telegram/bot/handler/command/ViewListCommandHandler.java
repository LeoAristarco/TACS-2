package utn.tacs.grupo3.telegram.bot.handler.command;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.factory.MessageFactory;
import utn.tacs.grupo3.telegram.bot.handler.AbstractCommandHandler;
import utn.tacs.grupo3.telegram.bot.helper.HtmlHelper;
import utn.tacs.grupo3.telegram.bot.request.entity.ListOfPlaces;
import utn.tacs.grupo3.telegram.bot.request.entity.Place;

/**
 * Handler for /viewlist command
 */
public class ViewListCommandHandler extends AbstractCommandHandler {

	@Override
	public List<BotApiMethod<?>> handle(Message message) {

		String listName = removeCommandFromMessageText(message.getText(), PlacesBotConstants.VIEW_LIST_COMMAND);

		ListOfPlaces listOfPlaces = apiRequest.listByName(listName, message.getFrom().getId());
		String text = createText(listOfPlaces);

		SendMessage answer = MessageFactory.createSendMessage(message)
				.setText(text)
				.setReplyMarkup(createKeyboard(listName, listOfPlaces.getPlaces()));

		return Arrays.asList(answer);
	}

	private InlineKeyboardMarkup createKeyboard(String listName, List<Place> places) {
		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();		
		List<List<InlineKeyboardButton>> buttons = places.stream()
				.map(place -> Arrays.asList(new InlineKeyboardButton(place.getName())
						.setCallbackData(createCallbackData(listName, place.getName()))))
				.collect(Collectors.toList());
		keyboard.setKeyboard(buttons);
		return keyboard;
	}

	private String createCallbackData(String listName, String placeName) {
		return PlacesBotConstants.VIEW_PLACE_CALLBACK + PlacesBotConstants.COMMAND_SEPARATOR + listName
				+ PlacesBotConstants.COMMAND_SEPARATOR + placeName;
	}
	
	private String createText(ListOfPlaces listOfPlaces) {
		List<Place> places = listOfPlaces.getPlaces();
		if (places == null || (places != null && places.isEmpty())) {
			return "The selected list does not have any place";
		}
		
		return HtmlHelper.bold("Select a place");
	}


}
