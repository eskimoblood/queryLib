package yql.queries;

import org.junit.Test;

import yql.Table;
import yql.queries.Query;

import junit.framework.TestCase;

public class QueryTest extends TestCase {

	@Test
	public void test() {
		Query q1 = new Query(Table.GEO_PLACES, "text=\"test\"", null);

		assertEquals(q1.toString(),
				"SELECT * FROM geo.place WHERE text=\"test\"");

		String[] what = { "a", "b" };
		q1 = new Query(what, Table.GEO_PLACES, "text=\"test\"");

		assertEquals(q1.toString(),
				"SELECT a, b FROM geo.place WHERE text=\"test\"");

		Query q2 = new Query(Table.GEO_PLACES, q1);

		assertEquals(
				q2.toString(),
				"SELECT * FROM geo.place WHERE IN (SELECT a, b FROM geo.place WHERE text=\"test\")");

		q2 = new Query(what, Table.GEO_PLACES, q1);
		assertEquals(
				q2.toString(),
				"SELECT a, b FROM geo.place WHERE IN (SELECT a, b FROM geo.place WHERE text=\"test\")");

//		assertEquals(
//				q2.toURL(),
//				"http://query.yahooapis.com/v1/public/yql?format=json&q=SELECT%20a,%20b%20FROM%20geo.place%20WHERE%20IN%20(SELECT%20a,%20b%20FROM%20geo.place%20WHERE%20text=\"test\")");
	}
}
