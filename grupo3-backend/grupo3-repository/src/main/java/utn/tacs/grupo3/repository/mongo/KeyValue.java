package utn.tacs.grupo3.repository.mongo;

public class KeyValue {
	
	private String key;
	private String value;
		
	public KeyValue(String key, String value) {	
		this.key = key;
		this.value = value;
	}
	
	public static KeyValue pair(String key, String value) {
		return new KeyValue(key, value);
	}
	
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
}
