package yql.pojos;

import java.util.Date;

public class Query <T>{
	public int count;
	public Date created;
	public String lang;
	public T results;
}
