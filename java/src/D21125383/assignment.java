package D21125383;

import javax.xml.crypto.dsig.Transform;

import ie.tudublin.Visual;
import processing.core.PApplet;

public class assignment extends Visual {
    int cols;
    int rows;
    int scale =20;
    int w = 5000;
    int h = 3000;
    float[][] land;

    public void settings(){
        //size(600, 600, P3D);
        fullScreen(P3D,SPAN);
        }
        
    public void setup(){
        color(RGB);
        startMinim();
        // Call loadAudio to load an audio file to process 
        loadAudio("Different Heaven & EH!DE - My Heart [NCS Release].mp3");   
        cols =w/scale;
        rows=h/scale;
        getAudioPlayer().play();
        
    }     
   
    public void draw(){
        background(0);
        calculateAverageAmplitude();
        calculateFrequencyBands();
        System.out.println(getAmplitude()+" A");
        System.out.println(getSmoothedAmplitude()+" SA");
    
        land =new float [cols][rows];
        float yoff = getAmplitude();
        for(int y =0; y < rows;y++){
            float xoff = 0;
            for(int x =0; x < cols; x++){
                land[x][y] =map(noise(xoff, yoff),0,1,-50,50) ;
                xoff +=1;
            } 
            yoff+=getAmplitude()*2;
        }

        pushMatrix();
        translate(950, height/20, -200);
        noFill();
        stroke(map(getSmoothedAmplitude()*100, 0, 10, 0, 255), 255, 255);
        sphere(getAmplitude()*1000);
        popMatrix();

        translate(width/2, height/2);
        rotateX(PI/2.5f);
        translate(-w/2, -h/2);
        for(int y =0; y < rows-1;y++){
            beginShape(TRIANGLE_STRIP);
            for(int x =0; x < cols; x++){
                vertex(x*scale, y*scale,land[x][y]);
                vertex(x*scale, (y+1)*scale, land[x][y+1]);
            }
            endShape();
        }

           
            
    }
}