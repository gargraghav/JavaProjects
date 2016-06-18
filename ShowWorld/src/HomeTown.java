import processing.core.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.*;

public class HomeTown extends PApplet{
	
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	AbstractMapProvider provider = new Google.GoogleMapProvider();
	int zoomLevel = 10;
	public void setup(){
		size(800, 600);
		background(0, 0, 0);
		map = new UnfoldingMap(this, 0, 0, width, height, provider);
		MapUtils.createDefaultEventDispatcher(this, map);
		map.zoomAndPanTo(zoomLevel, new Location(28.8f, 76.1f));
	}
	public void draw(){
		map.draw();
	}
}
