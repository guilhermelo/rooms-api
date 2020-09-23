package melo.guilherme.rooms.api.util.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public LocalDateTime parseDatetime(String dateTime) {
		
		if(Objects.isNull(dateTime)) {
			throw new NullPointerException("Datetime should not be null!");
		}
		
		LocalDateTime localDateTime = null;		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			localDateTime = LocalDateTime.parse(dateTime, formatter);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Error in parse datetime: " + e.getMessage());
		}
		
		return localDateTime;
	}

}
