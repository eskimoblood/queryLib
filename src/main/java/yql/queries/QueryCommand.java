package yql.queries;

import java.net.URL;

public interface QueryCommand<T> {

	T execute(URL url);
}
