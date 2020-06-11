package melo.guilherme.rooms.api.util.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

import org.springframework.stereotype.Component;

import melo.guilherme.rooms.api.config.exception.BusinessException;
import melo.guilherme.rooms.api.config.exception.Message;
import melo.guilherme.rooms.api.config.exception.MessageType;

@Component
public class DateUtil {

	public LocalDateTime parseDatetime(String dateTime) {
		
		if(Objects.isNull(dateTime)) {
			BusinessException.of(new Message("Invalid Date", MessageType.VALIDATION));
		}
		
		LocalDateTime localDateTime = null;		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			localDateTime = LocalDateTime.parse(dateTime, formatter);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Date shouldn't be empty");
		}
		
		return localDateTime;
	}

}
