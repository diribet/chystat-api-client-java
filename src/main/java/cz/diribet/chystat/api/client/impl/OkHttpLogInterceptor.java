package cz.diribet.chystat.api.client.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OkHttpLogInterceptor implements Interceptor {

	private static final Logger LOG = LoggerFactory.getLogger(OkHttpLogInterceptor.class);

	public static final OkHttpLogInterceptor INSTANCE = new OkHttpLogInterceptor();

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		LOG.debug("Sending {}", request);

		Response response = chain.proceed(request);
		LOG.debug("Received {}", response);

		return response;
	}

}
