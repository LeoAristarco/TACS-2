package utn.tacs.grupo3.telegram.bot.helper;

public class HtmlHelper {
	
	public static String bold(String toBold) {
		return "<b>" + toBold + "</b>";
	}
	
	public static String italic(String toItalic) {
		return "<i>" + toItalic + "</i>";
	}
	
	public static String br() {
		return "\n";
	}
	
	public static String multipleBr(int count) {
		StringBuffer multipleBr = new StringBuffer();
		
		for (int i = 0; i < count; i++) {
			multipleBr.append(br());
		}
		
		return multipleBr.toString();
	}
	
	public static String formatText(String...args) {
		StringBuilder text = new StringBuilder();
		
		for (String string : args) {
			text.append(string);
		}
		
		return text.toString();
	}

}
