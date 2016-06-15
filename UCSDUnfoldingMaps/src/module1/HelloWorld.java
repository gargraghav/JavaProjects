package module1;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.AbstractMapProvider;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;

/** HelloWorld, literally ;)
  * An application with two maps side-by-side zoomed in on different locations.
  * Author: mananKalra
  * @author Manan Kalra
  * Date: June 15, 2016
  * */

public class HelloWorld extends PApplet
{
	//It's to keep eclipse from reporting a warning
	private static final long serialVersionUID = 1L;

	/** This is where to find the local tiles, for working without an Internet connection */
	public static String mbTilesString = "blankLight-1-3.mbtiles";
	
	//WORKING OFFLINE? Change the value of this variable to true
	private static final boolean offline = false;
	
	/** The map we use to display: La Jolla, CA */
	UnfoldingMap map1;
	/** The map you will use to display your home town: Bhiwani, Haryana, India */ 
	UnfoldingMap map2;

	public void setup() {
		size(800, 600, P2D);  // Setting up the Applet window to be 800x600
		                      // The OPENGL argument indicates to use the 
		                      // Processing library's 2D drawing

		// This sets the background color for the Applet.  
		this.background(255, 255, 255);
		
		// Select a map provider
		AbstractMapProvider provider = new Google.GoogleTerrainProvider();
		AbstractMapProvider provider2 = new Google.GoogleMapProvider();
		
		// Set a zoom level
		int zoomLevel = 10;
		
		if (offline) {
			provider = new MBTilesMapProvider(mbTilesString);
			// 3 is the maximum zoom level for working offline
			zoomLevel = 3;
		}
		
		// Creating a new UnfoldingMap to be displayed in this window.  
		// The 2nd-5th arguments give the map's x, y, width and height
		// The 6th argument specifies the map provider.  
		// For offline use: MBTilesMapProvider
		map1 = new UnfoldingMap(this, 50, 50, 350, 500, provider);
        map2 = new UnfoldingMap(this, 400, 50, 350, 500, provider2);
		
        // The next line zooms in and centers the map at 
	    map1.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
	    map2.zoomAndPanTo(zoomLevel, new Location(28.8f, 76.1f));
		
		// Making the map interactive
		MapUtils.createDefaultEventDispatcher(this, map1);
		MapUtils.createDefaultEventDispatcher(this, map2);
	}

	/** Drawing the Applet window.  */
	public void draw() {
		map1.draw();
		map2.draw();
	}
}


