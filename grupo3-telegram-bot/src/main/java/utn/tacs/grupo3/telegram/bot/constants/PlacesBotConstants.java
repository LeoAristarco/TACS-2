package utn.tacs.grupo3.telegram.bot.constants;

import java.util.Arrays;
import java.util.List;

public final class PlacesBotConstants {
	
	//Commands
	public static final String START_COMMAND = "/start";
	public static final String LOGIN_COMMAND = "/login";
	public static final String HELP_COMMAND = "/help";
	public static final String MY_LISTS_COMMAND = "/mylists";
	public static final String VIEW_LIST_COMMAND = "/viewlist";
	public static final String SEARCH_COMMAND = "/search";
	public static final String LOGOUT_COMMAND = "/logout";
	
	//Callback queries
	public static final String VIEW_PLACE_CALLBACK = "/viewplace";
	public static final String ADD_PLACE_TO_LIST = "/addplace";
	public static final String ADD_PLACE_TO_SELECTED_LIST = "/addplacetolist";

	//Inline queries
	public static final String SEARCH_NEAR_ME_INLINE = "near me";
	public static final String SEARCH_BY_NAME = "name ";
	
	public static final String COMMAND_SEPARATOR = "_";
	
	public static List<String> allCommands(){
		return Arrays.asList(
				START_COMMAND, LOGIN_COMMAND, HELP_COMMAND, MY_LISTS_COMMAND, VIEW_LIST_COMMAND, SEARCH_COMMAND, LOGOUT_COMMAND, 
				VIEW_PLACE_CALLBACK, ADD_PLACE_TO_LIST, ADD_PLACE_TO_SELECTED_LIST,
				SEARCH_NEAR_ME_INLINE, SEARCH_BY_NAME);
	}

}
