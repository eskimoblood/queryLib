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
		return new Query<PlaceListResult>(Table.GEO_PLACE, query,
				getListCommand());
	}

	// /**
	// * Query against the geo.places.parent table
	// *
	// * @param woeid
	// * @return
	// */
	// public static Place getPlaceParent(int woeid) {
	// return queryPlace(Table.GEO_PLACE_PARENT, "child_woeid=\"" + woeid
	// + "\"");
	// }
	//
	// /**
	// * Query against the geo.places.belongtos table
	// *
	// * @param woeid
	// * @return
	// */
	// public static List<Place> getPlaceBelongtos(int woeid) {
	// return queryPlaceList(Table.GEO_PLACES_BELONGSTOS, "member_woeid=\""
	// + woeid + "\"");
	// }
	//
	// public static List<Place> getPlaceBelongtos(int woeid, PlaceTypes place)
	// {
	// return queryPlaceList(Table.GEO_PLACES_BELONGSTOS, "member_woeid=\""
	// + woeid + "\" and placetype=\"" + place + "\"");
	// }
	//
	// /**
	// * Query against the geo.places.children table
	// *
	// * @param woeid
	// * @return
	// */
	// public static List<Place> getPlaceChildren(int woeid) {
	// return queryPlaceList(Table.GEO_PLACES_CHILDREN, "member_woeid=\""
	// + woeid + "\"");
	// }
	//
	// public static List<Place> getPlaceChildren(int woeid, PlaceTypes place) {
	// return queryPlaceList(Table.GEO_PLACES_CHILDREN, "member_woeid=\""
	// + woeid + "\" and placetype=\"" + place + "\"");
	// }
	//
	// /**
	// * Query against the geo.places.common table
	// *
	// * @param woeid
	// * @return
	// */
	// public static List<Place> getPlaceCommon(int[] woeids) {
	// int l = woeids.length;
	// StringBuffer sb = new StringBuffer();
	// if (l < 2 || l > 7) {
	// throw new IllegalArgumentException(
	// "the query needs 2 woeids min and max 7");
	// }
	// for (int i = 0; i < woeids.length; i++) {
	// sb.append(" woeid" + i + "=\"");
	// sb.append(woeids[i] + "\"");
	// if (i < l - 1) {
	// sb.append(" and ");
	// }
	//
	// }
	//
	// return queryPlaceList(Table.GEO_PLACES_COMMON, "member_woeid=\"" + sb
	// + "\"");
	// }
	//
	// public static List<Place> getPlaceCommon(int woeid, PlaceTypes place) {
	// return queryPlaceList(Table.GEO_PLACES_COMMON, "member_woeid=\""
	// + woeid + "\" and placetype=\"" + place + "\"");
	// }
	//
	// /**
	// * Query against the geo.places.ancestors table
	// *
	// * @param woeid
	// * @return
	// */
	// public static List<Place> getPlaceAncestors(int woeid) {
	// return queryPlaceList(Table.GEO_PLACES_ANCESTORS, "descendant_woeid=\""
	// + woeid + "\"");
	// }
	//
	// /**
	// * Query against the geo.places.n table
	// *
	// * @param woeid
	// * @return
	// */
	// public static List<Place> getPlaceNeighbor(int woeid) {
	// return queryPlaceList(Table.GEO_PLACES_NEIGHBORS, "neighbor_woeid=\""
	// + woeid + "\"");
	// }
	//
	// /**
	// * Query against the geo.places.ancestors table
	// *
	// * @param woeid
	// * @return
	// */
	// public static List<Place> getPlaceSibling(int woeid) {
	// return queryPlaceList(Table.GEO_PLACES_SIBLING, "sibling_woeid=\""
	// + woeid + "\"");
	// }
	//
	// private static List<Place> queryPlaceList(String query) {
	// return queryPlaceList(Table.GEO_PLACE, query);
	// }
	//
	// // TODO create a generic query after the bug is fixed in jackson:
	// //
	// http://jira.codehaus.org/browse/JACKSON-479?page=com.atlassian.jira.plugin.system.issuetabpanels%3Aall-tabpanel

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
