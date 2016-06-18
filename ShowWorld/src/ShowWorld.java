import processing.core.*;
import de.fhpotsdam.unfolding.*;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.utils.*;

public class ShowWorld extends PApplet{
	
	private static final long serialVersionUID = 1L;
	UnfoldingMap map;
	AbstractMapProvider provider = new Google.GoogleMapProvider();
	
	public void setup(){
		size(800, 600);
		background(0, 0, 0);
		map = new UnfoldingMap(this, 0, 0, width, height, provider);
		MapUtils.createDefaultEventDispatcher(this, map);
	}
	public void draw(){
		map.draw();
	}
}
