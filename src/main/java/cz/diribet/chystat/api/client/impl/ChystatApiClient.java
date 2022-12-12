package cz.diribet.chystat.api.client.impl;

import java.time.Duration;

import com.google.gson.Gson;

import cz.diribet.chystat.api.client.jwt.JwtTokenProvider;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

/**
 * {@link OkHttpApiClient} that creates authenticated requests using the given {@link JwtTokenProvider}
 */
public class ChystatApiClient extends OkHttpApiClient {

	private static final Duration READ_TIMEOUT = Duration.ofMinutes(1);

	private final JwtTokenProvider jwtTokenProvider;

	public ChystatApiClient(String baseUrl, JwtTokenProvider jwtTokenProvider) {
		this(baseUrl, new Gson(), jwtTokenProvider);
	}

	public ChystatApiClient(String baseUrl, Gson gson, JwtTokenProvider jwtTokenProvider) {
		super(baseUrl, gson);
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected OkHttpClient.Builder clientBuilder() {
		OkHttpClient.Builder builder = super.clientBuilder();
		builder.readTimeout(READ_TIMEOUT);
		return builder;
	}

	@Override
	protected Builder requestBuilder(String path) {
		Builder requestBuilder = super.requestBuilder(path);
		requestBuilder.header("Authorization", createAuthorizationHeaderValue());
		return requestBuilder;
	}

	private String createAuthorizationHeaderValue() {
		String jwtToken = jwtTokenProvider.getJwtToken();
		return "Bearer " + jwtToken;
	}

}
