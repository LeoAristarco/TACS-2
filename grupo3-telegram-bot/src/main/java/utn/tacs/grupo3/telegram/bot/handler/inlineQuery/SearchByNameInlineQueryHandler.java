package utn.tacs.grupo3.telegram.bot.handler.inlineQuery;

import java.util.Arrays;
import java.util.List;

import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.factory.InlineQueryResultFactory;
import utn.tacs.grupo3.telegram.bot.factory.MessageFactory;
import utn.tacs.grupo3.telegram.bot.handler.InlineQueryHandler;
import utn.tacs.grupo3.telegram.bot.request.entity.Venue;

public class SearchByNameInlineQueryHandler implements InlineQueryHandler{

	@Override
	public List<BotApiMethod<?>> handle(InlineQuery inlineQuery) {
		String searchedName = inlineQuery.getQuery().substring(PlacesBotConstants.SEARCH_BY_NAME.length()).trim();
		
		List<Venue> venues = apiRequest.searchPlacesByName(searchedName, inlineQuery.getFrom().getId());
		
		AnswerInlineQuery answer = MessageFactory.createAnswerInlineQuery(inlineQuery);	
		answer.setResults(InlineQueryResultFactory.createNearMeQueryResultsWithKeyboard(venues));		

		return Arrays.asList(answer);

	}

}
