package ie.tudublin;
import D21125383.Test;
import example.CubeVisual;
import example.MyVisual;
import example.RotatingAudioBands;

public class Main
{	

	public void startUI()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new MyVisual());		
	}

	public static void assignment()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new D21125383.assignment());		
	}

	public static void Assignment()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new Assignment());		
	}
	public static void main(String[] args)
	{
		
		Assignment();			
	}

	
}