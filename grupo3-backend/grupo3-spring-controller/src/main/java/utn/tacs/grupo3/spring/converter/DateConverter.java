package utn.tacs.grupo3.spring.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
    public static Date year2020() {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return format.parse("26/10/2020");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
