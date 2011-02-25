package test;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import yql.geo.placefinder.pojo.PlaceFinder;
import yql.geo.placefinder.pojo.QueryPlaceFinder;
import yql.geo.places.QueryPlace;
import yql.geo.places.pojos.Place;
import yql.geo.places.pojos.PlaceTypes;


public class JacksonTest {

	public static void main(String[] args) {

//		List<Place> a = QueryPlace.getPlaceBelongtos(12521721);
		PlaceFinder p = QueryPlaceFinder.queryByText(" moabit");
		Logger.getLogger("result").info(p.city + "");

	}
}
