package utn.tacs.grupo3.telegram.bot.handler;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 * Interface for Telegram-callback-queries handlers
 * 
 */
public interface CallbackQueryHandler extends Handler<CallbackQuery>{
	
	List<BotApiMethod<?>> handle(CallbackQuery callbackQuery);

}
