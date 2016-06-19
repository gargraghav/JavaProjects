import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.*;
import de.fhpotsdam.unfolding.providers.*;
import java.util.*;
import processing.core.*;

public class LifeExp extends PApplet{
	
	UnfoldingMap map;
	HashMap<String, Float> lifeExpByCountry;
	List<Feature> countries;
	List<Marker> countryMarkers;
	AbstractMapProvider provider = new Google.GoogleMapProvider();
	public void setup(){
		size(800, 600, OPENGL);
		map = new UnfoldingMap(this, 0, 0, 800, 600, provider);
		MapUtils.createDefaultEventDispatcher(this, map);
		lifeExpByCountry = loadFromCSV("LifeExpectancyWorldBank.csv");
		countries = GeoJSONReader.loadData(this, "countries.geo.json");
		countryMarkers = MapUtils.createSimpleMarkers(countries);
	    map.addMarkers(countryMarkers);
	    shadeCountries();
	}
	
	public void draw(){
		map.draw();
	}
	
	private HashMap<String, Float> loadFromCSV(String fileName){
		 HashMap<String, Float> mapToReturn = new HashMap<String, Float>();
		 String[] rows = loadStrings(fileName);
		 for(String row : rows){
			 String[] columns = row.split(",");
			 if(columns.length == 6 && !columns[5].equals("..")) {
					mapToReturn.put(columns[4], Float.parseFloat(columns[5]));
			 }
		 }
		 return mapToReturn;
	}
	
	private void shadeCountries(){
		for (Marker marker : countryMarkers) {
			String countryId = marker.getId();
			if (lifeExpByCountry.containsKey(countryId)) {
				float lifeExp = lifeExpByCountry.get(countryId);
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255);
				marker.setColor(color(255-colorLevel, 100, colorLevel));
			}
			else {
				marker.setColor(color(150,150,150));
			}
		}
	}
}
