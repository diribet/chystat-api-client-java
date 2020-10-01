package cz.diribet.chystat.api.client.jwt;

@FunctionalInterface
public interface JwtTokenProvider {

	String getJwtToken();

}
