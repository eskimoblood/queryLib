package yql.pojos.geo.placefinder;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class PlaceFinderSettings {

	private Map<String, String> settings;
	
	private boolean any;

	public PlaceFinderSettings() {
		settings = new HashMap<String, String>();
	}

	public void street(String street) {
		settings.put("street", street);
	}

	public void city(String city) {
		settings.put("city", city);
	}

	public void house(String house) {
		settings.put("house", house);
	}

	public void unittype(String unittype) {
		settings.put("unittype", unittype);
	}

	public void unit(String unit) {
		settings.put("unit", unit);
	}

	public void xstreet(String xstreet) {
		settings.put("xstreet", xstreet);
	}

	public void postal(String postal) {
		settings.put("postal", postal);
	}

	public void level4(String level4) {
		settings.put("level4", level4);
	}

	public void level3(String level3) {
		settings.put("level3", level3);
	}

	public void level2(String level2) {
		settings.put("level2", level2);
	}

	public void level1(String level1) {
		settings.put("level1", level1);
	}

	public void level0(String level0) {
		settings.put("level0", level0);
	}

	public void neighborhood(String neighborhood) {
		settings.put("neighborhood", neighborhood);
	}

	public void county(String county) {
		settings.put("county", county);
	}

	public void state(String state) {
		settings.put("state", state);
	}

	public void lang(Locale lang) {
		settings.put("lang", lang.getLanguage());
	}

	public void focus(String focus) {
		settings.put("focus", focus);
	}
	
	public void offset(Integer offset) {
		offset = Math.max(0, Math.min(100, offset));
		settings.put("focus", String.valueOf(offset));
	}

	public void unitType(String unitType) {
		settings.put("unitType", unitType);
	}
}
