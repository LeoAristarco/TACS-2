package utn.tacs.grupo3.telegram.bot.handler.callbackQuery;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendVenue;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.handler.CallbackQueryHandler;
import utn.tacs.grupo3.telegram.bot.request.ApiRequestImpl;
import utn.tacs.grupo3.telegram.bot.request.entity.Place;
import utn.tacs.grupo3.telegram.bot.user.LoggedUsers;

public class ViewPlaceCallbackQueryHandler implements CallbackQueryHandler{

	@Override
	public List<BotApiMethod<?>> handle(CallbackQuery callbackQuery) {
		String[] parsed = callbackQuery.getData().split(PlacesBotConstants.COMMAND_SEPARATOR);
		
		List<Place> places = new ApiRequestImpl().listByName(
				LoggedUsers.getUsername(callbackQuery.getFrom().getId())
				,parsed[1],
				callbackQuery.getFrom().getId()
				).getPlaces();
		
		Place place = places.stream().filter(aPlace -> aPlace.getName().equalsIgnoreCase(parsed[2])).findFirst().get();		
		SendVenue venue = createVenue(callbackQuery, place);
		
		return List.of(venue);
	}

	private SendVenue createVenue(CallbackQuery callbackQuery, Place place) {
		SendVenue venue = new SendVenue();
		venue.setAddress(place.getAddress())
			.setChatId(callbackQuery.getMessage().getChatId())
			.setTitle(place.getName())
			.setLatitude(place.getLatitude().floatValue())
			.setLongitude(place.getLongitude().floatValue());
		return venue;
	}

}
