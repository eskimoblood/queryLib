package yql.queries.places;

import java.util.Locale;

import junit.framework.TestCase;
import yql.pojos.geo.places.PlaceTypes;
import yql.queries.Query;
import yql.queries.geo.places.QueryPlace;

public class QueryPlaceTest extends TestCase {

	public void test() {
		Query<?> q = QueryPlace.getPlace(1);
		assertEquals(q.toString(), "SELECT * FROM geo.places WHERE woeid=\"1\"");

		q = QueryPlace.getPlace("test");
		assertEquals(q.toString(),
				"SELECT * FROM geo.places WHERE text=\"test\"");

		q = QueryPlace.getPlace("test", Locale.GERMANY);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places WHERE text=\"test\" and focus=\"DE\"");

		q = QueryPlace.getPlace("test", PlaceTypes.ADMIN);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places WHERE text=\"test\" and placetype=\"Admin\"");

		q = QueryPlace.getPlace(1, PlaceTypes.ADMIN);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places WHERE woeid=\"1\" and placetype=\"Admin\"");

		q = QueryPlace.getPlace("test", PlaceTypes.ADMIN, Locale.GERMANY);
		assertEquals(
				q.toString(),
				"SELECT * FROM geo.places WHERE text=\"test\" and placetype=\"Admin\" and focus=\"DE\"");

		q = QueryPlace.getPlace(1, PlaceTypes.ADMIN, Locale.GERMANY);
		assertEquals(
				q.toString(),
				"SELECT * FROM geo.places WHERE woeid=\"1\" and placetype=\"Admin\" and focus=\"DE\"");

		q = QueryPlace.getPlaceParent(1);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.parent WHERE child_woeid=\"1\"");

		q = QueryPlace.getPlaceBelongtos(1);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.belongtos WHERE member_woeid=\"1\"");

		q = QueryPlace.getPlaceBelongtos(1, PlaceTypes.ADMIN);
		assertEquals(
				q.toString(),
				"SELECT * FROM geo.places.belongtos WHERE member_woeid=\"1\" and placetype=\"Admin\"");

		q = QueryPlace.getPlaceChildren(1);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.children WHERE parent_woeid=\"1\"");

		q = QueryPlace.getPlaceChildren(1, PlaceTypes.ADMIN);
		assertEquals(
				q.toString(),
				"SELECT * FROM geo.places.children WHERE parent_woeid=\"1\" and placetype=\"Admin\"");

		try {
			int[] woeids = new int[1];
			q = QueryPlace.getPlaceCommon(woeids);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertNotNull(e.getMessage());
		}

		try {
			int[] woeids = new int[8];
			q = QueryPlace.getPlaceCommon(woeids);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertNotNull(e.getMessage());
		}

		int[] woeids = { 1, 2 };
		q = QueryPlace.getPlaceCommon(woeids);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.common WHERE woeid1=\"1\" and woeid2=\"2\"");

		q = QueryPlace.getPlaceAncestors(2);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.ancestors WHERE descendant_woeid=\"2\"");

		q = QueryPlace.getPlaceNeighbor(2);
		assertEquals(q.toString(),
		"SELECT * FROM geo.places.neighbors WHERE neighbor_woeid=\"2\"");
		
		q = QueryPlace.getPlaceSibling(2);
		assertEquals(q.toString(),
		"SELECT * FROM geo.places.siblings WHERE sibling_woeid=\"2\"");
	}

}
