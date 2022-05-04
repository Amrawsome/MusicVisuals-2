package ie.tudublin;
import D21125370.caAssignment;
import example.MyVisual;

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

	public static void assignmentLaura()
	{
		String[] a = {"MAIN"};
        processing.core.PApplet.runSketch( a, new caAssignment());		
	}
	public static void main(String[] args)
	{
		//assignment();	
		assignment();
	}

	
}