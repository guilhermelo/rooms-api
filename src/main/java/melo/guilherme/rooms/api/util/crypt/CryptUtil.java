package melo.guilherme.rooms.api.util.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptUtil {
	
	public static String encryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}
}
