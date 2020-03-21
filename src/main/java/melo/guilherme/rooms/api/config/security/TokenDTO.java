package melo.guilherme.rooms.api.config.security;

public class TokenDTO {
	private String token;
	private String authenticationType;

	public TokenDTO(String token, String authenticationType) {
		super();
		this.token = token;
		this.authenticationType = authenticationType;
	}
	
	public String getToken() {
		return token;
	}
	
	public String getAuthenticationType() {
		return authenticationType;
	}
}
