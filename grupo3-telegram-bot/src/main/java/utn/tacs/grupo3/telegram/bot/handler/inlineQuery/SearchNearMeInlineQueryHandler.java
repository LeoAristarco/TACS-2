package utn.tacs.grupo3.telegram.bot.handler.inlineQuery;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;

import utn.tacs.grupo3.telegram.bot.exception.LocationNotEnabledException;
import utn.tacs.grupo3.telegram.bot.factory.InlineQueryResultFactory;
import utn.tacs.grupo3.telegram.bot.factory.MessageFactory;
import utn.tacs.grupo3.telegram.bot.handler.InlineQueryHandler;
import utn.tacs.grupo3.telegram.bot.request.entity.Venue;

public class SearchNearMeInlineQueryHandler implements InlineQueryHandler{

	@Override
	public List<BotApiMethod<?>> handle(InlineQuery inlineQuery) {
		
		if (inlineQuery.getLocation() == null) {
			throw new LocationNotEnabledException("Please enable location services");
		}
		
		Location location = inlineQuery.getLocation();		
		List<Venue> venues = apiRequest.near(
				location.getLatitude(), 
				location.getLongitude(),
				inlineQuery.getFrom().getId());
		
		AnswerInlineQuery answer = MessageFactory.createAnswerInlineQuery(inlineQuery);		
		answer.setResults(InlineQueryResultFactory.createNearMeQueryResultsWithKeyboard(venues));		

		return Arrays.asList(answer);
	}

}
