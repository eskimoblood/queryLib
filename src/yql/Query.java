package yql;


public class Query {

	private Table table;
	private String[] what;
	private Query query;
	private String queryString;
	private String BASEURL = "http://query.yahooapis.com/v1/public/yql?format=json&q=";

	public Query(Table table, String queryString) {
		this(null, table, queryString);
	}

	public Query(String[] what, Table table, String queryString) {
		this.what = what;
		this.table = table;
		this.queryString = queryString;
	}

	public Query(Table table, Query query) {
		this(null, table, query);
	}

	public Query(String[] what, Table table, Query query) {
		this.what = what;
		this.table = table;
		this.query = query;
	}

	@Override
	public String toString() {
		StringBuffer w = new StringBuffer();
		if (what == null) {
			w.append("*");
		} else {
			for (int i = 0; i < what.length; i++) {
				w.append(what[i]);
				if (i < what.length - 1) {
					w.append(", ");
				}
			}
		}
		String result = "SELECT " + w + " FROM " + table + " WHERE ";

		if (this.queryString != null) {
			result += queryString;
		} else {
			result += "IN (" + query + ")";
		}

		return result;
	}
	
	public String toURL(){
		return BASEURL + this.toString().replace(" ", "%20");
	}
}
