package yql.queries.geo.places;

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

import yql.Table;
import yql.pojos.Response;
import yql.pojos.geo.placefinder.PlaceFinder;
import yql.pojos.geo.places.Place;
import yql.pojos.geo.places.PlaceListResult;
import yql.pojos.geo.places.PlaceResult;
import yql.pojos.geo.places.PlaceTypes;
import yql.queries.Query;
import yql.queries.QueryCommand;

public class QueryPlace {

	/**
	 * Query against the geo.places table
	 *
	 * <p>
	 * <a href=
	 *
	 * "http://developer.yahoo.com/yql/console/?q=select%20*%20from%20ukpostcode.latlng%20where%20lat%3D'51.5181'%20and%20lon%3D'-0.127115'%20and%20format%3D'xml'%3B#h=select%20*%20from%20geo.places%20where%20text%3D%22sfo%22"
	 * >Example</a>
	 *
	 * @param place
	 *
	 * @return
	 */
	public static Query<PlaceListResult> getPlace(String place) {
		return getListQuery("text=\"" + place + "\"");
	}

	public static Query<PlaceListResult> getPlace(int woeid) {
		return getListQuery("woeid=\"" + woeid + "\"");
	}

	public static Query<PlaceListResult> getPlace(String place, Locale focus) {
		return getListQuery("text=\"" + place + "\" and focus=\""
				+ focus.getCountry() + "\"");
	}

	public static Query<PlaceListResult> getPlace(int woeid, Locale focus) {
		return getListQuery("text=\"" + woeid + "\" and focus=\""
				+ focus.getCountry() + "\"");
	}

	public static Query<PlaceListResult> getPlace(String place,
			PlaceTypes placetype) {
		return getListQuery("text=\"" + place + "\" and placetype=\""
				+ placetype + "\"");
	}

	public static Query<PlaceListResult> getPlace(int woeid,
			PlaceTypes placetype) {
		return getListQuery("woeid=\"" + woeid + "\" and placetype=\""
				+ placetype + "\"");
	}

	public static Query<PlaceListResult> getPlace(String place,
			PlaceTypes placetype, Locale focus) {
		return getListQuery("text=\"" + place + "\" and placetype=\""
				+ placetype + "\" and focus=\"" + focus.getCountry() + "\"");
	}

	public static Query<PlaceListResult> getPlace(int woeid,
			PlaceTypes placetype, Locale focus) {
		return getListQuery("woeid=\"" + woeid + "\" and placetype=\""
				+ placetype + "\" and focus=\"" + focus.getCountry() + "\"");
	}

	private static Query<PlaceListResult> getListQuery(String query) {
		return new Query<PlaceListResult>(Table.GEO_PLACES, query,
				getListCommand());
	}

	/**
	 * Query against the geo.places.parent table
	 *
	 * @param woeid
	 * @return
	 */
	public static Query<PlaceResult> getPlaceParent(int woeid) {
		return new Query<PlaceResult>(Table.GEO_PLACES_PARENT, "child_woeid=\""
				+ woeid + "\"", getCommand());
	}

	/**
	 * Query against the geo.places.belongtos table
	 *
	 * @param woeid
	 * @return
	 */
	public static Query<PlaceListResult> getPlaceBelongtos(int woeid) {
		return new Query<PlaceListResult>(Table.GEO_PLACES_BELONGSTOS,
				"member_woeid=\"" + woeid + "\"", getListCommand());
	}

	public static Query<PlaceListResult> getPlaceBelongtos(int woeid,
			PlaceTypes place) {
		return new Query<PlaceListResult>(Table.GEO_PLACES_BELONGSTOS,
				"member_woeid=\"" + woeid + "\" and placetype=\"" + place
						+ "\"", getListCommand());
	}

	/**
	 * Query against the geo.places.children table
	 *
	 * @param woeid
	 * @return
	 */
	public static Query<PlaceListResult> getPlaceChildren(int woeid) {
		return new Query<PlaceListResult>(Table.GEO_PLACES_CHILDREN,
				"parent_woeid=\"" + woeid + "\"", getListCommand());
	}

	public static Query<PlaceListResult> getPlaceChildren(int woeid,
			PlaceTypes place) {
		return new Query<PlaceListResult>(Table.GEO_PLACES_CHILDREN,
				"parent_woeid=\"" + woeid + "\" and placetype=\"" + place
						+ "\"", getListCommand());
	}

	/**
	 * Query against the geo.places.common table
	 *
	 * @param woeid
	 * @return
	 */
	public static Query<PlaceListResult> getPlaceCommon(int[] woeids) {
		int l = woeids.length;
		if (l < 2 || l > 7) {
			throw new IllegalArgumentException(
					"the query needs 2 woeids min and max 7");
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < woeids.length; i++) {
			sb.append("woeid" + (i + 1) + "=\"");
			sb.append(woeids[i] + "\"");
			if (i < l - 1) {
				sb.append(" and ");
			}

		}

		return new Query<PlaceListResult>(Table.GEO_PLACES_COMMON,
				sb.toString(), getListCommand());
	}

	/**
	 * Query against the geo.places.ancestors table
	 *
	 * @param woeid
	 * @return
	 */
	public static Query<PlaceListResult> getPlaceAncestors(int woeid) {
		return new Query<PlaceListResult>(Table.GEO_PLACES_ANCESTORS,
				"descendant_woeid=\"" + woeid + "\"", getListCommand());
	}

	/**
	 * Query against the geo.places.n table
	 *
	 * @param woeid
	 * @return
	 */
	public static Query<PlaceListResult> getPlaceNeighbor(int woeid) {
		return new Query<PlaceListResult>(Table.GEO_PLACES_NEIGHBORS,
				"neighbor_woeid=\"" + woeid + "\"", getListCommand());
	}

	/**
	 * Query against the geo.places.ancestors table
	 *
	 * @param woeid
	 * @return
	 */
	public static Query<PlaceListResult> getPlaceSibling(int woeid) {
		return new Query<PlaceListResult>(Table.GEO_PLACES_SIBLINGS,
				"sibling_woeid=\"" + woeid + "\"", getListCommand());
	}

	/**
	 * TODO create a generic query after the bug is fixed in jackson:
	 *
	 * http://jira.codehaus.org/browse/JACKSON-479?page=com.atlassian.jira.
	 * plugin.system.issuetabpanels%3Aall-tabpanel
	 *
	 * @return
	 */
	private static QueryCommand<PlaceListResult> getListCommand() {
		QueryCommand<PlaceListResult> command = new QueryCommand<PlaceListResult>() {
			@Override
			public PlaceListResult execute(URL url) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(
						DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);

				Response<PlaceListResult> r = null;
				try {
					r = mapper.readValue(url,
							new TypeReference<Response<PlaceListResult>>() {
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
					return r.query.results;
				} else {
					return null;
				}
			}
		};
		return command;

	}

	private static QueryCommand<PlaceResult> getCommand() {
		QueryCommand<PlaceResult> command = new QueryCommand<PlaceResult>() {
			@Override
			public PlaceResult execute(URL url) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(
						DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
						false);

				Response<PlaceResult> r = null;
				try {
					r = mapper.readValue(url,
							new TypeReference<Response<PlaceResult>>() {
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
					return r.query.results;
				} else {
					return null;
				}
			}
		};
		return command;

	}
}
