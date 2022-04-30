package D21125383;

import processing.core.PApplet;
import processing.core.PVector;

public class vectorMath extends PApplet {
    public void settings(){
        size(500, 500);
        
    }

    public void setup(){
        background(0);
    }

    public void draw(){
        background(0);
        strokeWeight(2);
        stroke(255);
        noFill();

        translate(width/2, height/2);
        ellipse(0, 0, 4, 4); 

        PVector mouse = new PVector(mouseX,mouseY);
        PVector center = new PVector(width/2, height/2);

        mouse.sub(center);
       // mouse.mult(0.1f);

        //float m = mouse.mag();
        //fill(255,0,0);
        //rect(0,0,m,20);
        
        mouse.normalize();
        mouse.mult(50);
        // set mag equals both using noralize and mult
        mouse.setMag(50);

        line(0, 0, mouse.x, mouse.y);
    }
}
