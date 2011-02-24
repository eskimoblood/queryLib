package yql;

public class QueryBuilder {

	public static final String BASEURL = "http://query.yahooapis.com/v1/public/yql?format=json&q=";
	public static final String BASEURLFOROAUTH = "http://query.yahooapis.com/v1/yql?format=json&q=";

	public static String query(String select, String from, String where) {
		StringBuffer sb = new StringBuffer("select%20");
		sb.append(select.replace(" ", "%20")).append("%20from%20").append(from.replace(" ", "%20")).append("%20where%20")
				.append(where.replace(" ", "%20"));
		return sb.toString();
	}
}
