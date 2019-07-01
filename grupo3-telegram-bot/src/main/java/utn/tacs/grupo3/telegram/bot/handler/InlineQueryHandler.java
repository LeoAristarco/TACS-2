package utn.tacs.grupo3.telegram.bot.handler;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;

/**
 * Interface for Telegram-inline-queries handlers 
 *
 */
public interface InlineQueryHandler extends Handler<InlineQuery> {
	
	List<BotApiMethod<?>> handle(InlineQuery inlineQuery);

}
