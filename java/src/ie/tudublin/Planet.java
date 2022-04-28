package ie.tudublin;

import processing.core.PApplet;

public class Planet extends PApplet{

    float radius;
    float angle;
    float distance;

    Planet(float radius, float distance)
    {
       this.radius = radius;
       this.distance = distance;
       angle = 0;
    }

    void show()
    {
        ellipse(0, 0, radius*2, radius*2);
    }
} 
