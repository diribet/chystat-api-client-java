package cz.diribet.chystat.api.client;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import lombok.Data;

@Data
public class ResponsePayload {

	private final String responseBody;
	private final Gson gson;

	public String asString() {
		return responseBody;
	}

	public Map<?, ?> asMap() {
		return asObject(Map.class);
	}

	public List<Map<?, ?>> asListOfMaps() {
		return asObject(new TypeToken<List<Map<?, ?>>>(){}.getType());
	}

	public Object asMapOrList() {
		if (responseBody != null && responseBody.startsWith("[")) {
			return asListOfMaps();

		} else {
			return asMap();
		}
	}

	public <T> T asObject(Class<T> clazz) {
		return asObject((Type) clazz);
	}

	public <T> T asObject(Type type) {
		if (StringUtils.isBlank(responseBody)) {
			return null;
		}
		return gson.fromJson(responseBody, type);
	}

}
