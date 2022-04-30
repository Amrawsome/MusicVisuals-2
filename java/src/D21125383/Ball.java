    package D21125383;

import processing.core.PVector;

public class Ball extends assignment {
    PVector location;
    PVector velocity;

    public void settings(){
        size(800, 200);
        smooth();
      
        }
        
        public void setup(){
        background(255);
        location = new PVector(width/2, height/2);
        velocity = new PVector(2.5f, -2);
        }
        
        public void draw(){
        background(255);
       
       
       location.add(velocity);
    
        if ((location.x > width) || (location.x < 0)){
         velocity.x = velocity.x * -1;   
        }
        if ((location.y > height) || (location.y < 0)){
            velocity.y = velocity.y * -1;   
        }
    

        stroke(0);
        strokeWeight(2);
        fill(175);
        ellipse(location.x, location.y, 48, 48);
    
    
    }
}


