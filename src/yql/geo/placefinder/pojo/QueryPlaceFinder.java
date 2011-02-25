package yql.geo.placefinder.pojo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import yql.Query;
import yql.Table;
import yql.pojos.Response;

public class QueryPlaceFinder {

	public static PlaceFinder queryByText(String text) {
		return query("text=\"" + text + "\"");
	}

	private static PlaceFinder query(String queryString) {
		Query query = new Query(Table.GEO_PLACEFINDER, queryString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(
				DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Response<PlaceFinderResult> r = null;
		try {
			r = mapper.readValue(new URL(query.toURL()),
					new TypeReference<Response<PlaceFinderResult>>() {
					});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (r != null) {
			return r.query.results.Result;
		} else {
			return null;
		}
	}
}
