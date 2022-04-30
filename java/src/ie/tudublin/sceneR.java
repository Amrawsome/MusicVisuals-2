package ie.tudublin;

import java.util.ArrayList;

import com.jogamp.newt.event.WindowUpdateEvent;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PGraphics;

public class sceneR extends PApplet{
    
    //variables
    Minim minim;
    AudioPlayer audioPlayer;
    AudioInput audioInput;
    AudioBuffer audioBuffer;

    Planet planet;

    PGraphics cube;

    // pause/play key
    int mode = 0;

    //buffer
    float[] lerpedBuffer;
    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;
    
    
    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (audioPlayer.isPlaying()) {
                audioPlayer.pause();
            } else {
                audioPlayer.rewind();
                audioPlayer.play();
            }
        }
	}

    public void drawPlanet()
    {
        background(0);
        lights();
        translate(width/2, height/2);
        sphere(200);

        
        /*
        pushMatrix();
        fill(0);
        translate(-25, (float) -12.5, 76);
        sphere(35);
        popMatrix();

        */
    }

    public void settings()
    {
        //size(600, 600 , P3D);
        //size(1000,700,P3D);
        fullScreen(P3D, SPAN);
    }

    float off = 0;

    public void setup()
    {
        
        cube = createGraphics(width, height, P3D);
        minim = new Minim(this);
        audioPlayer = minim.loadFile("Heaven.mp3", 1024);
        audioPlayer.play();
        audioBuffer = audioPlayer.mix;
        colorMode(RGB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }
    
    void drawCube(float f, float g, float size) { 
        float c = map(20, 0, audioBuffer.size(), 0, 255);
        stroke(204, 102, c);
        
        cube.beginDraw();
        cube.lights();
        cube.background(0);
        cube.noStroke();
        cube.translate(f, g);
        cube.rotateY((float) (frameCount/100.0));
        cube.rotateX((float) (frameCount/50.0));
        cube.box(size);
        cube.endDraw();
      }

    public void draw()
    {
        //background(0);
        float halfH = height / 2;
        float widthH = width / 2;
        float average = 0;
        float sum = 0;
        int size = 300;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < audioBuffer.size() ; i ++)
        {
            sum += abs(audioBuffer.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], audioBuffer.get(i), 0.05f);
        }
        average= sum / (float) audioBuffer.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.01f);

        switch (mode) {
			case 0:
            {
                background(0); 
                drawCube(widthH+200, halfH, size);
                image(cube, 0, 0);
                            
                for(int i = 0 ; i < audioBuffer.size() ; i ++)
                {
                    
                    
                    float c = map(i, 0, audioBuffer.size(), 0, 255);
                    //stroke(204, 102, c);
                    
                    noStroke();
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    noFill();
                    float backgroundH = map(f, -1, 1, 0, height);
                    
                    //circle(width - i*2, backgroundH, 10);

                    if (f > 150)
                    {
                        f = 50;
                    }

                    if (c > 255)
                    {
                        c -= 100;
                    }
                    
                    rotate(c);
                    fill(204, 102, c);
                    circle(f, i, f);
                    circle(width-f, i, f);

                    size -= 50;

                    
                    
                }
                
                
                //drawPlanet();
                //Mars.show();
                
                break;
            }
        }
    }

}
