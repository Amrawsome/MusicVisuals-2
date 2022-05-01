package ie.tudublin;

import processing.core.PApplet;
import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PGraphics;

public class Assignment extends PApplet{
    
    //variables
    Minim minim;
    AudioPlayer audioPlayer;
    AudioInput audioInput;
    AudioBuffer audioBuffer;

    float off = 0;

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

    public void settings()
    {
        fullScreen(P3D, SPAN);
        //size(displayWidth, displayHeight, P3D);
    }


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
    
    void drawPlanet(float f, float g, float size) { 
        float c = map(20, 0, audioBuffer.size(), 0, 255);
        stroke(204, 102, c);
        translate(mouseX, mouseY);
        if(mousePressed)
        {
            lights();
        }
        
        ambientLight(100,c,220);
        
        rotateY((float) (frameCount/100.0));
        rotateX((float) (frameCount/50.0));
        rotateZ((float) (frameCount/50.0));
        fill(255);
        noStroke();
        sphere(100);
    }

    public void draw()
    {
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
                //textAlign(CENTER);
                textSize((float) (25));	
                fill(200, 140); 
                text("Press Mouse to turn on Lights on this Crazy Planet", CENTER, TOP);
                drawPlanet(widthH+200, halfH, size);
                image(cube, 0, 0);
                            
                for(int i = 0 ; i < audioBuffer.size() ; i ++)
                {
                    
                    
                    float c = map(i, 0, audioBuffer.size(), 0, 255);
                    
                    noStroke();
                    float f = lerpedBuffer[i] * halfH * 4.0f;

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
                    circle(f , i, f);
                    circle(width-f, i, f);  
                }
                
                break;
            }
            
            case 1:
            {

                break;
            }
        }
    }

}
