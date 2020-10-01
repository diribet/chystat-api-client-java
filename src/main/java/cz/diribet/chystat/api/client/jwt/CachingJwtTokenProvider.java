package cz.diribet.chystat.api.client.jwt;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * JWT token provider that caches the created JWT tokens for 9 minutes (10 minutes is the validity of the JWT token)
 */
public abstract class CachingJwtTokenProvider implements JwtTokenProvider {

	private static final Object CACHE_KEY = new Object();

	private final LoadingCache<Object, String> cache;

	public CachingJwtTokenProvider() {
		CacheLoader<Object, String> cacheLoader = CacheLoader.from(this::getFreshJwtToken);
		cache = CacheBuilder.newBuilder()
							.expireAfterWrite(Duration.ofMinutes(9))
							.build(cacheLoader);
	}

	@Override
	public String getJwtToken() {
		try {
			return cache.get(CACHE_KEY);
		} catch (ExecutionException e) {
			throw new RuntimeException("Failed to get JWT token from cache", e);
		}
	}

	protected abstract String getFreshJwtToken();
}
