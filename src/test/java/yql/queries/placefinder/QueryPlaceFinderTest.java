package yql.queries.placefinder;

import yql.pojos.geo.placefinder.PlaceFinder;
import yql.queries.Query;
import yql.queries.geo.placefinder.PlaceFinderQuery;
import junit.framework.TestCase;

public class QueryPlaceFinderTest extends TestCase {

	public void test(){
		Query<PlaceFinder> q = PlaceFinderQuery.createQueryByText("test");
		assertEquals(q.toString(), "SELECT * FROM geo.placefinder WHERE text=\"test\"");
	}
}
