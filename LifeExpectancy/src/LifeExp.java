/**
 * A program that shows average life expectancy of a country on a world map.
 * Red- 45
 * Blue- 85
 * 
 * @author Manan Kalra
*/
import java.util.*;
import processing.core.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.data.*;
import de.fhpotsdam.unfolding.marker.*;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.*;

public class LifeExp extends PApplet{
	
	UnfoldingMap map; //creating a map
	HashMap<String, Float> lifeExpByCountry; //creating a HasMap that'll store the data to be displayed on the map
	List<Feature> countries; //a list called countries that'll store its info from the JSON file, of type Feature
	List<Marker> countryMarkers; //a list that'll store markers for each country present in the countries list, of type Marker
	AbstractMapProvider provider = new Google.GoogleMapProvider(); // service that provides world map
	public void setup(){
		size(800, 600, OPENGL); //size of the canvas
		map = new UnfoldingMap(this, 0, 0, 800, 600, provider); //specifying map size and provider
		MapUtils.createDefaultEventDispatcher(this, map); //adding functionality and interactivity to the map
		lifeExpByCountry = loadFromCSV("LifeExpectancyWorldBank.csv"); //loading data from a CSV file
		countries = GeoJSONReader.loadData(this, "countries.geo.json"); //GeoJSONReader.LoadData(this, "JSONfileName.json") loads data from a JSON file; countries.geo.json contains geographical location of each country on the Earth
	    countryMarkers = MapUtils.createSimpleMarkers(countries); //Markers are used to load and display geospatial data
	    map.addMarkers(countryMarkers); //adding markers
	    shadeCountries(); //shading countries according to their life expectancy data
	}
	
	public void draw(){
		map.draw(); //drawing the map
	}
	
	private HashMap<String, Float> loadFromCSV(String fileName){ //function that returns meaningful data(a HashMap) from the CSV file
		 HashMap<String, Float> mapToReturn = new HashMap<String, Float>(); //creating a HashMap which will be returned
		 String[] rows = loadStrings(fileName); //creating an array of strings called rows, this will store each row of the CSV file as a String element, uses the built-in function loadStrings(arg)
		 for(String row : rows){ //for loop used to split each String element in the array rows, row is a String variable used to loop through the array rows
			 String[] columns = row.split(","); //creating an array of strings called columns that splits the string encountered as soon as it sees a comma; CSV stands for Comma Separated Values
			 if(columns.length == 6 && !columns[5].equals("..")) { //?????
					mapToReturn.put(columns[4], Float.parseFloat(columns[5])); //built-in function put(arg0, arg1) is used to put data into a HashMap, arg0 serves as the key while arg0 as its value; Float.parseFloat(arg) is used to convert the String data in this case into a float
			 }// columns[4] contains Country Code and columns[5] contains Life Expactancy of that country
		 }
		 return mapToReturn; // now lifeExpByCountry is equal to mapToReturn
	}
	
	private void shadeCountries(){
		for (Marker marker : countryMarkers) { //for loop that loops through countryMarkers; marker is a Marker type variable
			String countryId = marker.getId(); //gets the marker ID; saved as id field in the JSON file
			if (lifeExpByCountry.containsKey(countryId)) { //checks if the country for which the marker is already set is present on the data list or not
				float lifeExp = lifeExpByCountry.get(countryId); //stores life expectancy value
				int colorLevel = (int) map(lifeExp, 40, 90, 10, 255); //map function takes five arguments
				                                                      //-variable that contains the life expectancy value
				                                                      //-40 and 90 are the range in which life expectancy lies; 
				                                                      //-10 and 255 are the range of colors
				                                                      //it maps 40-90 to 10-255; matches the % that lies between 40 and 90 to a color level
				marker.setColor(color(255-colorLevel, 100, colorLevel)); //setting colors
			}
			else {
				marker.setColor(color(150,150,150)); //setting gray color for the countries for which there is no data
			}
		}
	}
}
