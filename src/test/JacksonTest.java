package test;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import yql.geo.places.QueryPlace;
import yql.geo.places.pojos.Place;
import yql.geo.places.pojos.PlaceTypes;


public class JacksonTest {

	public static void main(String[] args) {

		List<Place> a = QueryPlace.getPlaceBelongtos(12521721);
		Logger.getLogger("result").info(a.get(0).boundingBox.northEast.latitude + "");

	}
}
