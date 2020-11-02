package melo.guilherme.rooms.api.util.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public LocalDateTime parseDatetime(String dateTime) {

		Objects.requireNonNull(dateTime, "Data não deve ser nula");

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
			
			return LocalDateTime.parse(dateTime, formatter);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Erro ao tentar converter data. Valor informado: " + dateTime);
		}
	}
}
