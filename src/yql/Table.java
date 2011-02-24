package yql;

/**
 * @author andreaskoberle
 *         <p>
 *         repesenting table in yql
 */
public enum Table {
	GEO_PLACE("geo.place"), 
	GEO_PLACE_PARENT("geo.places.parent"), 
	GEO_PLACES_BELONGSTOS("geo.places.belongtos"), 
	GEO_PLACES_ANCESTORS("geo.places.ancestors"), 
	GEO_PLACES_CHILDREN("geo.places.children"), 
	GEO_PLACES_COMMON("geo.places.common"), 
	GEO_PLACES_DESCENDANTS("geo.places.descendants"), 
	GEO_PLACES_NEIGHBORS("geo.places.neighbors"), 
	GEO_PLACES_PARENT("geo.places.parent"), 
	GEO_PLACES_SIBLING("geo.places.sibling"), 
	GEO_PLACEMAKER("geo.placemaker"), 
	GEO_PLACEFINDER("geo.placefinder"), 
	;
	private String tableName;

	Table(String tableName) {
		this.tableName = tableName;
	}

	public String toString() {
		return tableName;
	}
}
