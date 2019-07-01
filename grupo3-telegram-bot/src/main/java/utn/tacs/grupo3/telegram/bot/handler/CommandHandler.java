package utn.tacs.grupo3.telegram.bot.handler;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Interface for Telegram-commands handlers
 * 
 */
public interface CommandHandler extends Handler<Message>{

	List<BotApiMethod<?>> handle(Message message);

}
