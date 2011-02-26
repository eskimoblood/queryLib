package test;

import java.util.logging.Logger;

import yql.geo.placefinder.pojo.PlaceFinder;
import yql.queries.Query;
import yql.queries.QueryPlaceFinder;

public class JacksonTest {

	public static void main(String[] args) {

		// List<Place> a = QueryPlace.getPlaceBelongtos(12521721);
		Query<PlaceFinder> p = QueryPlaceFinder.createQueryByText(" moabit");
		PlaceFinder r = p.query();
		Logger.getLogger("result").info(r.city + "");

	}
}
