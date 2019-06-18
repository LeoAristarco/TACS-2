package utn.tacs.grupo3.telegram.bot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import utn.tacs.grupo3.telegram.bot.constants.PlacesBotConstants;
import utn.tacs.grupo3.telegram.bot.exception.TelegramBotException;
import utn.tacs.grupo3.telegram.bot.factory.ReplyKeyboardFactory;
import utn.tacs.grupo3.telegram.bot.handler.CallbackQueryHandler;
import utn.tacs.grupo3.telegram.bot.handler.CommandHandler;
import utn.tacs.grupo3.telegram.bot.handler.InlineQueryHandler;
import utn.tacs.grupo3.telegram.bot.handler.inlineQuery.SearchByNameInlineQueryHandler;
import utn.tacs.grupo3.telegram.bot.handler.inlineQuery.SearchNearMeInlineQueryHandler;
import utn.tacs.grupo3.telegram.bot.handler.locator.CallbackQueryHandlerLocator;
import utn.tacs.grupo3.telegram.bot.handler.locator.CommandHandlerLocator;
import utn.tacs.grupo3.telegram.bot.request.exception.RequestException;
import utn.tacs.grupo3.telegram.bot.request.exception.SessionExpiredException;
import utn.tacs.grupo3.telegram.bot.request.exception.TelegramUserNotLoggedException;

public class PlacesBot extends TelegramLongPollingBot{
	
	private static Logger logger = Logger.getLogger(PlacesBot.class.getName());
	
	private static final String BOT_USERNAME = "TACS20191CGrupo3Bot";
	private static final String BOT_TOKEN = "837736990:AAGVZ27HyFKKyc-ZCbUhgIHE7iddP6-wchY";
	
	private static Map<String, InlineQueryHandler> inlineQueries;
		
	static {		
		inlineQueries = new HashMap<String, InlineQueryHandler>();
		inlineQueries.put(PlacesBotConstants.SEARCH_NEAR_ME_INLINE, new SearchNearMeInlineQueryHandler());
		inlineQueries.put(PlacesBotConstants.SEARCH_BY_NAME, new SearchByNameInlineQueryHandler());
	}
	
	/**
	 * Manages all updates sent by a telegram user. 
	 * Only sends answers to registered commands, callback queries and inline queries.
	 */
	@Override
	public void onUpdateReceived(Update update) {
		List<BotApiMethod<?>> answers = null;
		
		try {			
			if (update.hasMessage() && update.getMessage().getText() != null) {
				CommandHandler handler = CommandHandlerLocator.getHandler(update.getMessage());
				answers = handler.handle(update.getMessage());
			}
			if (update.hasCallbackQuery()) {
				CallbackQueryHandler handler = CallbackQueryHandlerLocator.getHandler(update.getCallbackQuery());
				answers = handler.handle(update.getCallbackQuery());
			}
			if (update.hasInlineQuery()) {
				String inlineQuery = getInlineQueryCommand(update.getInlineQuery().getQuery());
				if (inlineQueries.containsKey(inlineQuery)) {
					InlineQueryHandler handler = inlineQueries.get(inlineQuery);
					answers = handler.handle(update.getInlineQuery());
				}
			}
						
		} catch (TelegramBotException e) {
			answers = this.handleException(e, update);
		} catch (SessionExpiredException | TelegramUserNotLoggedException e) {
			 answers = this.handleUserNotLogged(update);
		} catch (RequestException e) {
			answers = this.handleRequestException(e, update);
		}
		finally {
			this.sendAnswers(answers);
		}
	}

	@Override
	public String getBotUsername() {
		return BOT_USERNAME;
	}
	@Override
	public String getBotToken() {
		return BOT_TOKEN;
	}
	
	/**
	 * Handles any TelegramBotException and creates an answer for the user based on the exception message.
	 * @param e
	 * @param update
	 * @return answer
	 */
	private List<BotApiMethod<?>> handleException(Exception e, Update update){
		SendMessage errorAnswer = new SendMessage()
				.setChatId(getChatIdFromUpdate(update))
				.setText(e.getMessage());
		
		return Arrays.asList(errorAnswer);
	}
	
	/**
	 * Handles errors when a user is not logged in
	 * @param update
	 * @return answer
	 */
	private List<BotApiMethod<?>> handleUserNotLogged(Update update){		
		SendMessage userNotLoggedAnswer = new SendMessage()
				.setChatId(getChatIdFromUpdate(update))
				.setText("You need to login")
				.setReplyMarkup(ReplyKeyboardFactory.createInitialKeyBoard());
		
		return Arrays.asList(userNotLoggedAnswer);
	}
	
	/**
	 * Handles requests exceptions
	 * @param e
	 * @param update
	 * @return
	 */
	private List<BotApiMethod<?>> handleRequestException(RequestException e, Update update) {
		SendMessage errorAnswer = new SendMessage()
				.setChatId(getChatIdFromUpdate(update))
				.setText(e.getMessage());
		
		return Arrays.asList(errorAnswer);
	}
	
	/**
	 * Sends all answers to the user in order.
	 * @param answers
	 */
	private void sendAnswers(List<BotApiMethod<?>> answers) {
		if (answers != null) {
			answers.forEach(answer -> {
				try {
					execute(answer);
				} catch (TelegramApiException e) {
					logger.log(Level.WARNING, e.getMessage(), e);
				}
			});
		}		
	}
	
	
	private String getInlineQueryCommand(String query) {
		if (query.startsWith(PlacesBotConstants.SEARCH_BY_NAME)) {
			return PlacesBotConstants.SEARCH_BY_NAME;
		} else if (query.startsWith(PlacesBotConstants.SEARCH_NEAR_ME_INLINE)) {
			return PlacesBotConstants.SEARCH_BY_NAME;
		}
		return "";
	}
	
	private String getChatIdFromUpdate(Update update) {
		String chatId = "";
		
		if (update.hasMessage()) {
			chatId = update.getMessage().getChatId().toString();
		} else if(update.hasCallbackQuery()){
			chatId = update.getCallbackQuery().getFrom().getId().toString();
		} else if (update.hasInlineQuery()) {
			chatId = update.getInlineQuery().getFrom().getId().toString();
		}
		
		return chatId;
	}
}
