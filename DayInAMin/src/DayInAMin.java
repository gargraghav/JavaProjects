import processing.core.*;

public class DayInAMin extends PApplet{
	
	public void setup(){
		PImage img;
		size(1000, 500);
		img = loadImage("bb.jpg", "jpg");
		img.resize(width, height);
		image(img, 0, 0);
	}
	
	public void draw(){
		int[] color = rgbFunction(second());
		fill(color[0], color[1], color[2]);
		ellipse(width/5, height/5, width/6, width/6);
	}
	
	public int[] rgbFunction(float seconds){
		int[] rgb = new int[3];
		float differ = 60-seconds;
		float ratio = differ/60;
		rgb[0] = (int)(255*ratio);
		rgb[1] = (int)(255*ratio);
		rgb[2] = 0;
		return rgb;
	}
}