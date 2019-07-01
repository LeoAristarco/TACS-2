package utn.tacs.grupo3.telegram.bot.factory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultVenue;

import utn.tacs.grupo3.telegram.bot.request.entity.Venue;

public class InlineQueryResultFactory {

	
	public static List<InlineQueryResult> createInlineQueryResults(InlineQueryResult...results) {
		return Arrays.asList(results);
	}
	
	public static List<InlineQueryResult> createNearMeQueryResultsWithKeyboard(List<Venue> venues){
		return venues.stream().map(venue -> new InlineQueryResultVenue()
				.setId(venue.getId())
				.setTitle(venue.getName())
				.setLatitude(venue.getLocation().getLatitude().floatValue())
				.setLongitude(venue.getLocation().getLongitude().floatValue())
				.setAddress(getAddress(venue))
				.setReplyMarkup(InlineKeyboardFactory.createAddPlaceToListKeyboard(venue)))
			.collect(Collectors.toList());
	}
	
	private static String getAddress(Venue venue) {
		if (venue.getLocation().getAddress() != null) {
			return venue.getLocation().getAddress();
		} else {
			return " ";
		}
	}
	
}
