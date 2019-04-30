package utn.tacs.grupo3.telegram.bot.util;

import java.util.Collection;
import java.util.List;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;

public class TelegramCommandUtil {
	
	private static final String LINE_SEPARATOR = "\n";
	private static final String SLASH_COMMAND = "/";
	
	public static String formattedCommandList(Collection<IBotCommand> commands) {
		StringBuffer formattedList = new StringBuffer();
		
		commands.forEach(cmd -> formattedList.append(SLASH_COMMAND + cmd.getCommandIdentifier() + " - " + cmd.getDescription() + LINE_SEPARATOR));
		
		return formattedList.toString();
	}
	
	public static String formattedListsOfPlacesList(List<String> listNames) {
		StringBuilder formattedString = new StringBuilder("<b>Listas</b>");		
		listNames.forEach(name -> formattedString.append(LINE_SEPARATOR + "- " + name));
		return formattedString.toString();
	}

}
