package yql.queries;

import java.net.MalformedURLException;
import java.net.URL;

import yql.Table;

public class Query<T> {

	private Table table;
	private String[] what;
	private Query<?> query;
	private String queryString;
	private String BASEURL = "http://query.yahooapis.com/v1/public/yql?format=json&q=";
	private int remoteLimitStart;
	private int remoteLimitOffset;
	private int localLimitOffset;
	private int localLimit;
	private int remoteLimit;
	private QueryCommand<T> command;

	public Query(Table table, String queryString, QueryCommand<T> command) {
		this(null, table, queryString);
		this.command = command;
	}

	public Query(String[] what, Table table, String queryString) {
		this.what = what;
		this.table = table;
		this.queryString = queryString;
	}

	public Query(Table table, Query<?> query) {
		this(null, table, query);
	}

	public Query(String[] what, Table table, Query<?> query) {
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

	private String toURL() {
		return BASEURL + this.toString().replace(" ", "%20");
	}

	public T query() {
		try {
			return command.execute(new URL(toURL()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * A remote limit controls the number of items (rows) that YQL retrieves
	 * from the back-end datasource. To specify a remote limit, enter the offset
	 * (start position) and number of items in parentheses after the table name.
	 *
	 * If no limit is set offset is 0 and limit is 10, so you get the first ten
	 * result sets.
	 *
	 * <a href="http://developer.yahoo.com/yql/guide/paging.html#remote_limits">
	 * http://developer.yahoo.com/yql/guide/paging.html#remote_limits</a>
	 *
	 * @param offset
	 * @param limit
	 */
	void setRemoteLimit(int offset, int limit) {
		remoteLimitOffset = offset;
		remoteLimit = limit;
	};

	void setRemoteLimit(int limit) {
		remoteLimitOffset = 0;
		remoteLimitOffset = limit;
	}

	/**
	 * A local limit controls the number of rows YQL returns to the calling
	 * application. YQL applies a local limit to the data set that it has
	 * retrieved from the back-end datasource.
	 *
	 * If no limit is set offset is 0 and limit is 10, so you get the first ten
	 * result sets.
	 *
	 * <a href="http://developer.yahoo.com/yql/guide/paging.html#local_limits">
	 * http://developer.yahoo.com/yql/guide/paging.html#local_limits</a>
	 *
	 * @param start
	 * @param limit
	 */
	void setLocalLimit(int offset, int limit) {
		localLimitOffset = offset;
		localLimit = limit;
	}

	void setLocalLimit(int limit) {
		localLimitOffset = 0;
		localLimit = limit;
	}
}
