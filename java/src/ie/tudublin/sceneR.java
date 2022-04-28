package ie.tudublin;

import java.util.ArrayList;

import com.jogamp.newt.event.WindowUpdateEvent;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class sceneR extends PApplet{
    
    //variables
    Minim minim;
    AudioPlayer audioPlayer;
    AudioInput audioInput;
    AudioBuffer audioBuffer;

    Planet planet;


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
        //size(600, 600 , P3D);
        fullScreen(P3D, SPAN);
    }

    float off = 0;

    public void setup()
    {
        
        minim = new Minim(this);
        audioPlayer = minim.loadFile("Heaven.mp3", 1024);
        audioPlayer.play();
        audioBuffer = audioPlayer.mix;
        colorMode(RGB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    public void draw()
    {
        background(0);
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < audioBuffer.size() ; i ++)
        {
            sum += abs(audioBuffer.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], audioBuffer.get(i), 0.05f);
        }
        average= sum / (float) audioBuffer.size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.1f);

        switch (mode) {
			case 0:
            {
                background(0);
                
                for(int i = 0 ; i < audioBuffer.size() ; i ++)
                {
                    
                    
                    float c = map(i, 0, audioBuffer.size(), 0, 255);
                    stroke(204, 102, c);
                    

                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    noFill();
                    float backgroundH = map(f, -1, 1, 0, height);
                    circle(width - i*2, backgroundH, 10);
                    circle(f, i, 50+f);
                    circle(width-f, i, 50+f);

                    
                    
                }

                
                //Mars.show();
                
                break;
            }
        }
    }

}
