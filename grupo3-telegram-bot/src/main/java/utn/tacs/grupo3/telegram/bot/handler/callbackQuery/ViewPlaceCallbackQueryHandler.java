package utn.tacs.grupo3.telegram.bot.handler.callbackQuery;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendVenue;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.handler.CallbackQueryHandler;
import utn.tacs.grupo3.telegram.bot.request.entity.Place;

public class ViewPlaceCallbackQueryHandler implements CallbackQueryHandler{

	@Override
	public List<BotApiMethod<?>> handle(CallbackQuery callbackQuery) {
		String[] parsed = callbackQuery.getData().split(PlacesBotConstants.COMMAND_SEPARATOR);
		
		List<Place> places = apiRequest
				.listByName(parsed[1], callbackQuery.getFrom().getId())
				.getPlaces();
		
		Place place = places.stream().filter(aPlace -> aPlace.getName().equalsIgnoreCase(parsed[2])).findFirst().get();		
		SendVenue venue = createVenue(callbackQuery, place);
		
		return Arrays.asList(venue);
	}

	private SendVenue createVenue(CallbackQuery callbackQuery, Place place) {
		SendVenue venue = new SendVenue();
		venue.setAddress(place.getAddress())
			.setChatId(callbackQuery.getMessage().getChatId())
			.setTitle(place.getName())
			.setLatitude(place.getCoordinates().getLatitude().floatValue())
			.setLongitude(place.getCoordinates().getLongitude().floatValue());
		return venue;
	}

}
