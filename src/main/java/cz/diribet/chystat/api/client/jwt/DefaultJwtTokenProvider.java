package cz.diribet.chystat.api.client.jwt;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DefaultJwtTokenProvider extends CachingJwtTokenProvider {

	@NonNull private final String tokenId;
	@NonNull private final String secret;

	@Override
	protected String getFreshJwtToken() {
		ZonedDateTime dateTime = ZonedDateTime.now(ZoneOffset.UTC);
		Date currentDate = Date.from(dateTime.toInstant());

		Algorithm signAlgorithm = Algorithm.HMAC256(secret);

		return JWT.create()
		          .withIssuedAt(currentDate)
		          .withSubject(tokenId)
		          .sign(signAlgorithm);
	}
}
