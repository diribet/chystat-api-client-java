package cz.diribet.chystat.api.client;

import lombok.Getter;

@Getter
public class InvalidResponseException extends RuntimeException {

	private final int statusCode;
	private final String body;

	public InvalidResponseException(int statusCode, String body) {
		super("Invalid response code " + statusCode + " Body: " + body);
		this.statusCode = statusCode;
		this.body = body;
	}
}
