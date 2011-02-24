package yql.geo.places;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import yql.Query;
import yql.QueryBuilder;
import yql.Table;
import yql.geo.places.pojos.Place;
import yql.geo.places.pojos.PlaceListResult;
import yql.geo.places.pojos.PlaceResult;
import yql.geo.places.pojos.PlaceTypes;
import yql.pojos.Response;

public class QueryPlace {

	/**
	 * Query against the geo.places table
	 * 
	 * <p>
	 * <a href=
	 * "http://developer.yahoo.com/yql/console/?q=select%20*%20from%20ukpostcode.latlng%20where%20lat%3D'51.5181'%20and%20lon%3D'-0.127115'%20and%20format%3D'xml'%3B#h=select%20*%20from%20geo.places%20where%20text%3D%22sfo%22"
	 * >Example</a>
	 * 
	 * @param place
	 * 
	 * @return
	 */
	public static List<Place> getPlace(String place) {
		String query = "text=\"" + place + "\"";
		return queryPlaceList(query);
	}

	public static List<Place> getPlace(int woeid) {
		String query = "text=\"" + woeid + "\"";
		return queryPlaceList(query);
	}

	public static List<Place> getPlace(String place, Locale focus) {
		String query = "text=\"" + place + "\" and focus=\"" + focus.getCountry() + "\"";
		return queryPlaceList(query);
	}

	public static List<Place> getPlace(int woeid, Locale focus) {
		String query = "text=\"" + woeid + "\" and focus=\"" + focus.getCountry() + "\"";
		return queryPlaceList(query);
	}

	public static List<Place> getPlace(String place, PlaceTypes placetype) {
		String query = "text=\"" + place + "\" and placetype=\"" + placetype + "\"";
		return queryPlaceList(query);
	}

	public static List<Place> getPlace(int woeid, PlaceTypes placetype) {
		String query = "text=\"" + woeid + "\" and placetype=\"" + placetype + "\"";
		return queryPlaceList(query);
	}

	public static List<Place> getPlace(String place, PlaceTypes placetype, Locale focus) {
		String query = "text=\"" + place + "\" and placetype=\"" + placetype + "\" and focus=\"" + focus.getCountry() + "\"";
		return queryPlaceList(query);
	}

	public static List<Place> getPlace(int woeid, PlaceTypes placetype, Locale focus) {
		String query = "text=\"" + woeid + "\" and placetype=\"" + placetype + "\" and focus=\"" + focus.getCountry() + "\"";
		return queryPlaceList(query);
	}

	/**
	 * Query against the geo.places.parent table
	 * 
	 * @param woeid
	 * @return
	 */
	public static Place getPlaceParent(int woeid) {
		return queryPlace(Table.GEO_PLACE_PARENT, "child_woeid=\"" + woeid + "\"");
	}

	/**
	 * Query against the geo.places.belongtos table
	 * 
	 * @param woeid
	 * @return
	 */
	public static List<Place> getPlaceBelongtos(int woeid) {
		return queryPlaceList(Table.GEO_PLACES_BELONGSTOS, "member_woeid=\"" + woeid + "\"");
	}

	public static List<Place> getPlaceBelongtos(int woeid, PlaceTypes place) {
		return queryPlaceList(Table.GEO_PLACES_BELONGSTOS, "member_woeid=\"" + woeid + "\" and placetype=\"" + place + "\"");
	}

	/**
	 * Query against the geo.places.belongtos table
	 * 
	 * @param woeid
	 * @return
	 */
	public static List<Place> getPlaceChildren(int woeid) {
		return queryPlaceList(Table.GEO_PLACES_CHILDREN, "member_woeid=\"" + woeid + "\"");
	}

	public static List<Place> getPlaceChildren(int woeid, PlaceTypes place) {
		return queryPlaceList(Table.GEO_PLACES_CHILDREN, "member_woeid=\"" + woeid + "\" and placetype=\"" + place + "\"");
	}

	/**
	 * Query against the geo.places.belongtos table
	 * 
	 * @param woeid
	 * @return
	 */
	public static List<Place> getPlaceCommon(int[] woeids) {
		int l = woeids.length;
		StringBuffer sb = new StringBuffer();
		if (l < 2 || l > 7) {
			throw new IllegalArgumentException("the query needs 2 woeids min and max 7");
		}
		for (int i = 0; i < woeids.length; i++) {
			sb.append(" woeid" + i + "=\"");
			sb.append(woeids[i] + "\"");
			if(i<l-1){
				sb.append(" and ");
			}

		}

		return queryPlaceList(Table.GEO_PLACES_COMMON, "member_woeid=\"" + sb + "\"");
	}

	public static List<Place> getPlaceCommon(int woeid, PlaceTypes place) {
		return queryPlaceList(Table.GEO_PLACES_COMMON, "member_woeid=\"" + woeid + "\" and placetype=\"" + place + "\"");
	}

	/**
	 * Query against the geo.places.ancestors table
	 * 
	 * @param woeid
	 * @return
	 */
	public static List<Place> getPlaceAncestors(int woeid) {
		return queryPlaceList(Table.GEO_PLACES_ANCESTORS, "descendant_woeid=\"" + woeid + "\"");
	}

	private static List<Place> queryPlaceList(String query) {
		return queryPlaceList(Table.GEO_PLACE, query);
	}

	private static List<Place> queryPlaceList(Table table, String queryString) {
		Query query = new Query(table, queryString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Response<PlaceListResult> r = null;
		try {
			r = mapper.readValue(new URL(query.toURL()), new TypeReference<Response<PlaceListResult>>() {
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
			return r.query.results.place;
		} else {
			return null;
		}
	}

	private static Place queryPlace(Table table, String queryString) {
		Query query = new Query(table, queryString);
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		Response<PlaceResult> r = null;
		try {
			r = mapper.readValue(new URL(query.toURL()), new TypeReference<Response<PlaceResult>>() {
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
			return r.query.results.place;
		} else {
			return null;
		}
	}

}
