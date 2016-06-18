import processing.core.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.*;

public class CurrentPlace extends PApplet{
	
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	AbstractMapProvider provider = new Google.GoogleMapProvider();
	int zoomLevel = 14;
	public void setup(){
		size(800, 600);
		background(0, 0, 0);
		map = new UnfoldingMap(this, 0, 0, width, height, provider);
		MapUtils.createDefaultEventDispatcher(this, map);
		map.zoomAndPanTo(zoomLevel, new Location(30.416771f, 77.9661903f));
	}
	public void draw(){
		map.draw();
	}
}
