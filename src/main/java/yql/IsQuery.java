package yql;

/**
 * @author akoeberle
 *
 */
public abstract class IsQuery {

	protected int remoteLimitStart;
	protected int remoteLimitOffset;
	protected int localLimitOffset;
	protected int localLimit;
	protected int remoteLimit;

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
