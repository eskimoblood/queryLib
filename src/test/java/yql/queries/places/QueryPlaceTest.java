package yql.queries.places;

import java.util.Locale;

import junit.framework.TestCase;
import yql.pojos.geo.places.PlaceTypes;
import yql.queries.Query;
import yql.queries.geo.places.PlaceQuery;

public class QueryPlaceTest extends TestCase {

	public void test() {
		Query<?> q = PlaceQuery.getPlace(1);
		assertEquals(q.toString(), "SELECT * FROM geo.places WHERE woeid=\"1\"");

		q = PlaceQuery.getPlace("test");
		assertEquals(q.toString(),
				"SELECT * FROM geo.places WHERE text=\"test\"");

		q = PlaceQuery.getPlace("test", Locale.GERMANY);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places WHERE text=\"test\" and focus=\"DE\"");

		q = PlaceQuery.getPlace("test", PlaceTypes.ADMIN);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places WHERE text=\"test\" and placetype=\"Admin\"");

		q = PlaceQuery.getPlace(1, PlaceTypes.ADMIN);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places WHERE woeid=\"1\" and placetype=\"Admin\"");

		q = PlaceQuery.getPlace("test", PlaceTypes.ADMIN, Locale.GERMANY);
		assertEquals(
				q.toString(),
				"SELECT * FROM geo.places WHERE text=\"test\" and placetype=\"Admin\" and focus=\"DE\"");

		q = PlaceQuery.getPlace(1, PlaceTypes.ADMIN, Locale.GERMANY);
		assertEquals(
				q.toString(),
				"SELECT * FROM geo.places WHERE woeid=\"1\" and placetype=\"Admin\" and focus=\"DE\"");

		q = PlaceQuery.getPlaceParent(1);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.parent WHERE child_woeid=\"1\"");

		q = PlaceQuery.getPlaceBelongtos(1);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.belongtos WHERE member_woeid=\"1\"");

		q = PlaceQuery.getPlaceBelongtos(1, PlaceTypes.ADMIN);
		assertEquals(
				q.toString(),
				"SELECT * FROM geo.places.belongtos WHERE member_woeid=\"1\" and placetype=\"Admin\"");

		q = PlaceQuery.getPlaceChildren(1);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.children WHERE parent_woeid=\"1\"");

		q = PlaceQuery.getPlaceChildren(1, PlaceTypes.ADMIN);
		assertEquals(
				q.toString(),
				"SELECT * FROM geo.places.children WHERE parent_woeid=\"1\" and placetype=\"Admin\"");

		try {
			int[] woeids = new int[1];
			q = PlaceQuery.getPlaceCommon(woeids);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertNotNull(e.getMessage());
		}

		try {
			int[] woeids = new int[8];
			q = PlaceQuery.getPlaceCommon(woeids);
			fail("IllegalArgumentException not thrown.");
		} catch (IllegalArgumentException e) {
			assertNotNull(e.getMessage());
		}

		int[] woeids = { 1, 2 };
		q = PlaceQuery.getPlaceCommon(woeids);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.common WHERE woeid1=\"1\" and woeid2=\"2\"");

		q = PlaceQuery.getPlaceAncestors(2);
		assertEquals(q.toString(),
				"SELECT * FROM geo.places.ancestors WHERE descendant_woeid=\"2\"");

		q = PlaceQuery.getPlaceNeighbor(2);
		assertEquals(q.toString(),
		"SELECT * FROM geo.places.neighbors WHERE neighbor_woeid=\"2\"");
		
		q = PlaceQuery.getPlaceSibling(2);
		assertEquals(q.toString(),
		"SELECT * FROM geo.places.siblings WHERE sibling_woeid=\"2\"");
	}

}
