package yql.queries.geo.placefinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import yql.pojos.geo.placefinder.PlaceFinder;
import yql.queries.Query;

public class PlaceFinderQueryBuilder {

	public PlaceFinderQueryBuilder() {
		locationQueries = new HashMap<LocationParameter, List<String>>();
	}

	Map<LocationParameter, List<String>> locationQueries;

	public void addQueryparameter(LocationParameter parameter, String value) {
		if (!locationQueries.containsKey(parameter)) {
			locationQueries.put(parameter, new ArrayList<String>());
		}
		locationQueries.get(parameter).add(value);
	}

	public Query<PlaceFinder> getQuery() {
		return PlaceFinderQuery.createQueryByText(createQueryString());
	}

	public String createQueryString() {
		StringBuilder sb = null;
		for (Map.Entry<LocationParameter, List<String>> locationQuery : locationQueries
				.entrySet()) {
			List<String> values = locationQuery.getValue();
			String parameter = locationQuery.getKey().toString();
			for (String value : values) {
				if(sb!= null){
					sb.append(" and ");
				}
				sb.append(parameter).append(" = \"").append(value).append("\" ");
			}

		}
		return sb.toString();
	}

	public enum LocationParameter {
		LOCATION("loaction"),

		NAME("name"),

		LINE1(" line1"),

		LINE2(" line2"),

		LINE3(" line3"),

		HOUSE("house"),

		STREET("street"),

		UNITTYPE("unittype"),

		UNIT("unit"),

		XSTREET("xstreet"),

		POSTAL("postal"),

		NEIGHBORHOOD("neighborhood"),

		CITY("city"),

		COUNTY("county"),

		STATE("state"),

		COUNTRY("country");

		private String parameter;

		LocationParameter(String parameter) {
			this.parameter = parameter;
		}

		public String toString() {
			return parameter;
		}
	}
}
