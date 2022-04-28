package ie.tudublin;

import processing.core.PApplet;

public class Planet extends PApplet{

    
    public Planet() {
        
    }

    public void drawPlanet()
    {
        background(0);
        lights();
        translate(width/2, height/2);
        sphere(200);
    }

}
