package utn.tacs.grupo3.telegram.bot.handler;

import java.util.List;

import org.telegram.telegrambots.meta.api.interfaces.BotApiObject;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import utn.tacs.grupo3.telegram.bot.request.ApiRequest;
import utn.tacs.grupo3.telegram.bot.request.ApiRequestImpl;

public interface Handler<T extends BotApiObject> {
	
	ApiRequest apiRequest = new ApiRequestImpl();
	
	List<BotApiMethod<?>> handle(T input);
	
}
