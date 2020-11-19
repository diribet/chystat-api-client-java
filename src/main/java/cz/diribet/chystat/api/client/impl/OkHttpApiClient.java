package cz.diribet.chystat.api.client.impl;

import static java.util.Objects.requireNonNull;

import java.io.IOException;

import com.google.gson.Gson;

import cz.diribet.chystat.api.client.IApiClient;
import cz.diribet.chystat.api.client.InvalidResponseException;
import cz.diribet.chystat.api.client.ResponsePayload;
import lombok.NonNull;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpApiClient implements IApiClient {

	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	private final Gson gson;
	private final String baseUrl;
	private OkHttpClient httpClient;

	public OkHttpApiClient(String baseUrl, @NonNull Gson gson) {
		requireNonNull(baseUrl, "URL of the API is required");
		this.gson = gson;

		if (baseUrl.endsWith("/")) {
			this.baseUrl = baseUrl.substring(0, baseUrl.length() - 1);

		} else {
			this.baseUrl = baseUrl;
		}
	}

	@Override
	public ResponsePayload get(String path) throws IOException, InvalidResponseException {
		Request request = requestBuilder(path).get().build();
		return execute(request);
	}

	@Override
	public ResponsePayload post(String path, Object payload) throws IOException, InvalidResponseException {
		String payloadJson = toPayloadString(payload);
		RequestBody requestBody = RequestBody.create(payloadJson, JSON);
		Request request = requestBuilder(path).post(requestBody).build();

		return execute(request);
	}

	@Override
	public ResponsePayload put(String path, Object payload) throws IOException, InvalidResponseException {
		String payloadJson = toPayloadString(payload);
		RequestBody requestBody = RequestBody.create(payloadJson, JSON);
		Request request = requestBuilder(path).put(requestBody).build();

		return execute(request);
	}

	@Override
	public ResponsePayload patch(String path, Object payload) throws IOException, InvalidResponseException {
		String payloadJson = toPayloadString(payload);
		RequestBody requestBody = RequestBody.create(payloadJson, JSON);
		Request request = requestBuilder(path).patch(requestBody).build();

		return execute(request);
	}

	@Override
	public ResponsePayload delete(String path) throws IOException, InvalidResponseException {
		Request request = requestBuilder(path).delete().build();
		return execute(request);
	}

	private String toPayloadString(Object payload) {
		if (payload instanceof String) {
			return (String) payload;
		}
		return gson.toJson(payload);
	}

	protected Request.Builder requestBuilder(String path) {
		String url = baseUrl + path;
		return new Request.Builder().url(url);
	}

	protected OkHttpClient.Builder clientBuilder() {
		return new OkHttpClient.Builder().addInterceptor(OkHttpLogInterceptor.INSTANCE);
	}

	private ResponsePayload execute(Request request) throws IOException, InvalidResponseException {
		synchronized (this) {
			if (httpClient == null) {
				httpClient = clientBuilder().build();
			}
		}

		try (Response response = httpClient.newCall(request).execute()) {
			String body = response.body().string();

			if (response.isSuccessful()) {
				return new ResponsePayload(body, gson);
			}

			throw new InvalidResponseException(response.code(), body);
		}
	}

}
