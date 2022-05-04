package D21125383;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;



public class assignment extends Setup{
    
    //variables
    Minim minim;
    AudioPlayer audioPlayer;
    AudioInput audioInput;
    AudioBuffer audioBuffer;

    // pause/play key
    int mode = 0;

    //buffer
   //float[] lerpedBuffer;
    // float y = 0;
    // float smoothedY = 0;
    // float smoothedAmplitude = 0;
    // float off = 0;



    //Random rand = new Random(123456789L);
    int cols;
    int rows;
    int scale =20;
    int w = 5000;
    int h = 3000;
    float[][] land;
    float average =0;
    public int random1;
    public int random2;
    public int random3;
    float value =0;

    

    //end controller

    //if space key is pressed pause/play
    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (getAudioPlayer().isPlaying()) {
                getAudioPlayer().pause();
            } else {
                getAudioPlayer().rewind();
                getAudioPlayer().play();
            }
        }
	}

    //set to fullscreen with P3D renderer
    public void settings()
    {
        fullScreen(P3D, SPAN);
        //size(displayWidth, displayHeight, P3D);
        //size(600, 600,P3D);
    }

    public void setup()
    {
        //load music and play
         startMinim();
         loadAudio("Heaven.mp3");
         getAudioPlayer().play();
         setAudioBuffer(getAudioPlayer().mix);
        // minim = new Minim(this);
        // audioPlayer = minim.loadFile("Heaven.mp3", 1024);
        // audioPlayer.play();
        // audioBuffer = audioPlayer.mix;

        //set color mode to RGB
        colorMode(RGB);
        y = height / 2;
        smoothedY = y;
        cols =w/scale;
        rows=h/scale;
       lerpedBuffer = new float[width];
    }
    
     

    public void draw()
    {   

        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        
        for(int i = 0 ; i < getAudioBuffer().size() ; i ++)
        {
            sum += abs(getAudioBuffer().get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], getAudioBuffer().get(i), 0.05f);
        }
        average= sum / (float) getAudioBuffer().size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.01f);
        // calculateAverageAmplitude();
       

        switch (mode) {
			case 0:
            {      
                background(0);
                textSize((float)(25));	
                fill(200, 140); 
                text("Move Mouse Around To Change Colour And Brightness", CENTER, TOP);
                text("Press Mouse To Fill, Move Around To Change Colour", CENTER, TOP-35);
                Stephen(average);
                
            }
            break;            
        }
        
    }
}