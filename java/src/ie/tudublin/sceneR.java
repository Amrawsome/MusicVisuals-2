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
        //size(1024, 1000, P3D);
        fullScreen(P3D, SPAN);
    }

    public void setup()
    {
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
        
    }

}
