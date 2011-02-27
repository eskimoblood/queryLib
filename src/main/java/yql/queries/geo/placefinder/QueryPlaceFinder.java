package yql.queries.geo.placefinder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import yql.Table;
import yql.pojos.Response;
import yql.pojos.geo.placefinder.PlaceFinder;
import yql.pojos.geo.placefinder.PlaceFinderResult;
import yql.queries.Query;
import yql.queries.QueryCommand;

public class QueryPlaceFinder {

	public static Query<PlaceFinder> createQueryByText(String text) {
		String queryString = ("text=\"" + text + "\"");

		return new Query<PlaceFinder>(Table.GEO_PLACEFINDER, queryString,
				getCommand());
	}

	private static QueryCommand<PlaceFinder> getCommand() {
		QueryCommand<PlaceFinder> command = new QueryCommand<PlaceFinder>() {

			@Override
			public PlaceFinder execute(URL url) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(
						DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);

				Response<PlaceFinderResult> r = null;
				try {
					r = mapper.readValue(url,
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

		};
		return command;
	}
}
