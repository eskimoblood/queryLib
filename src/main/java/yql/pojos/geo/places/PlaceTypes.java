package yql.pojos.geo.places;

public enum PlaceTypes {
	TOWN("Town", 	7),
	ADMIN("Admin", 	8),
	ADMIN2("Admin2", 	9),
	ADMIN3("Admin3", 	10),
	POSTALCODE("Postal Code", 	11),
	COUNTRY("Country", 	12),
	SUPERNAME("Supername", 	19),
	SUPURB("Suburb", 	22),
	COLLOQUIAL("Colloquial", 	24),
	CONTINENT("Continent",	29),
	TIMEZONE("Time Zone", 	31);
	;
	private String name;
	private int code;

	PlaceTypes(String name, int code){
		this.name = name ;
		this.code = code;
	}
	
public String toString(){
	return name;
}
public int getCode(){
	return code;
}
}
