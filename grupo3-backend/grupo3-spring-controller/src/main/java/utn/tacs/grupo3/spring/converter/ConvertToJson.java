package utn.tacs.grupo3.spring.converter;

import java.util.Collections;
import java.util.Map;

public class ConvertToJson {
    public static Map<String, String> start(String message) {
        return Collections.singletonMap("message", message);
    }
}
