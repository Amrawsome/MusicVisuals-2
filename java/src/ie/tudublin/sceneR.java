package ie.tudublin;

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

    Planet Mars;

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
        size(1024, 1000, P3D);
        //fullScreen(P3D, SPAN);
    }

    float off = 0;

    public void setup()
    {
        Mars = new Planet(0, 100);
        minim = new Minim(this);
        // Uncomment this to use the microphone
        // ai = minim.getLineIn(Minim.MONO, width, 44100, 16);
        // ab = ai.mix; 
        audioPlayer = minim.loadFile("Heaven.mp3", 1024);
        audioPlayer.play();
        audioBuffer = audioPlayer.mix;
        colorMode(HSB);

        y = height / 2;
        smoothedY = y;

        lerpedBuffer = new float[width];
    }

    public void draw()
    {
        //background(0);
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
        
        float cx = width / 2;
        float cy = height / 2;

        switch (mode) {
			case 0:
            {
                background(0);
                for(int i = 0 ; i < audioBuffer.size() ; i ++)
                {
                    //float c = map(ab.get(i), -1, 1, 0, 255);
                    float c = map(i, 0, audioBuffer.size(), 0, 255);
                    stroke(c, 255, 255);
                    float f = lerpedBuffer[i] * halfH * 4.0f;
                    fill(0);
                    circle(i+f, halfH-f ,50);
                    
                    
                }
                break;
            }

            case 1:
            {
                translate(cx, cy);
                Mars.show();
                break;
            }
        }
    }

}
