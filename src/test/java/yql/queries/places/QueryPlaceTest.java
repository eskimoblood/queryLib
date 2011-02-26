package yql.queries.places;

import java.util.Locale;

import yql.pojos.geo.places.PlaceTypes;
import yql.queries.Query;
import yql.queries.geo.places.QueryPlace;
import junit.framework.TestCase;

public class QueryPlaceTest extends TestCase {

	public void test() {
		Query<?> q = QueryPlace.getPlace(1);
		assertEquals(q.toString(), "SELECT * FROM geo.place WHERE woeid=\"1\"");

		q = QueryPlace.getPlace("test");
		assertEquals(q.toString(),
				"SELECT * FROM geo.place WHERE text=\"test\"");

		q = QueryPlace.getPlace("test", Locale.GERMANY);
		assertEquals(q.toString(),
				"SELECT * FROM geo.place WHERE text=\"test\" and focus=\"DE\"");

		q = QueryPlace.getPlace("test", PlaceTypes.ADMIN);
		assertEquals(q.toString(),
				"SELECT * FROM geo.place WHERE text=\"test\" and placetype=\"Admin\"");

		q = QueryPlace.getPlace(1, PlaceTypes.ADMIN);
		assertEquals(q.toString(),
				"SELECT * FROM geo.place WHERE woeid=\"1\" and placetype=\"Admin\"");

		q = QueryPlace.getPlace("test", PlaceTypes.ADMIN, Locale.GERMANY);
		assertEquals(q.toString(),
				"SELECT * FROM geo.place WHERE text=\"test\" and placetype=\"Admin\" and focus=\"DE\"");
		
		q = QueryPlace.getPlace(1, PlaceTypes.ADMIN, Locale.GERMANY);
		assertEquals(q.toString(),
		"SELECT * FROM geo.place WHERE woeid=\"1\" and placetype=\"Admin\" and focus=\"DE\"");
	}
}
