package cz.diribet.chystat.api.client;

import java.io.IOException;

public interface IApiClient {

	/**
	 * Executes GET request and returns response if the request was successful [200..300).
	 *
	 * @param path
	 * @return
	 * @throws IOException if there was some problem during request execution
	 * @throws InvalidResponseException if the response code was different to [200..300)
	 */
	ResponsePayload get(String path) throws IOException, InvalidResponseException;

	/**
	 * Executes POST request and returns response if the request was successful [200..300).
	 *
	 * @param path
	 * @param payload
	 * @return
	 * @throws IOException if there was some problem during request execution
	 * @throws InvalidResponseException if the response code was different to [200..300)
	 */
	ResponsePayload post(String path, Object payload) throws IOException, InvalidResponseException;

	/**
	 * Executes PUT request and returns response if the request was successful [200..300).
	 *
	 * @param path
	 * @param payload
	 * @return
	 * @throws IOException if there was some problem during request execution
	 * @throws InvalidResponseException if the response code was different to [200..300)
	 */
	ResponsePayload put(String path, Object payload) throws IOException, InvalidResponseException;

	/**
	 * Executes PATCH request and returns response if the request was successful [200..300).
	 *
	 * @param path
	 * @param payload
	 * @return
	 * @throws IOException if there was some problem during request execution
	 * @throws InvalidResponseException if the response code was different to [200..300)
	 */
	ResponsePayload patch(String path, Object payload) throws IOException, InvalidResponseException;

	/**
	 * Executes DELETE request and returns response if the request was successful [200..300).
	 *
	 * @param path
	 * @return
	 * @throws IOException if there was some problem during request execution
	 * @throws InvalidResponseException if the response code was different to [200..300)
	 */
	ResponsePayload delete(String path) throws IOException, InvalidResponseException;

}
