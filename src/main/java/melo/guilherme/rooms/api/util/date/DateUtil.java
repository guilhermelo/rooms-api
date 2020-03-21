package melo.guilherme.rooms.api.util.date;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class DateUtil {

	public static final String parseDatetime(LocalDateTime dateTime) {
		DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

		return pattern.format(dateTime);
	}

	public static final LocalDateTime parseDatetime(String dateTime) {
		return LocalDateTime.parse(dateTime);
	}

}
