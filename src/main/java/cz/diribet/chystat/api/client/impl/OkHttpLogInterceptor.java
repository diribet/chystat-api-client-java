package cz.diribet.chystat.api.client.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;

class OkHttpLogInterceptor implements Interceptor {

	private static final Logger LOG = LoggerFactory.getLogger(OkHttpLogInterceptor.class);

	public static final OkHttpLogInterceptor INSTANCE = new OkHttpLogInterceptor();

	private final HttpLoggingInterceptor loggingInterceptor;

	private OkHttpLogInterceptor() {
		HttpLoggingInterceptor.Logger logger = LOG::debug;
		loggingInterceptor = new HttpLoggingInterceptor(logger);
		loggingInterceptor.setLevel(Level.BODY);
		loggingInterceptor.redactHeader("Authorization");
		loggingInterceptor.redactHeader("Cookie");
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		if (LOG.isDebugEnabled()) {
			return loggingInterceptor.intercept(chain);
		} else {
			return chain.proceed(chain.request());
		}
	}

}
