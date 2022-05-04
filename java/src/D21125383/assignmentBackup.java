package D21125383;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;



public class assignmentBackup extends assignmentSetup {
    
    //variables
    Minim minim;
    AudioPlayer audioPlayer;
    AudioInput audioInput;
    AudioBuffer audioBuffer;

    // pause/play key
    int mode = 0;

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
         loadAudio("differentheaven.mp3");
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

        float halfH = height / 2;
        float widthH = width / 2;
        float average = 0;
        float sum = 0;
        int size = 100;
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
                 loadingBar();
                 if(getPer() >= 99.6f){
                    mode=1;     
                }
                break;
            }
            
            case 1:
            {
                background(0);
                speaker();
                break;    
            }
            case 2:
            {
                background(0);
                textSize((float)(25));	
                fill(200, 140); 
                text("Move Mouse Around To Change Colour And Brightness", CENTER, TOP);
                text("Press Mouse To Fill, Move Around To Change Colour", CENTER, TOP-35);
                Stephen(average);
                break;
            }

            case 3:
            {
                background(0); 

                //print instructions on screen
                textSize((float) (25));	
                fill(200, 140); 
                text("Press Mouse to turn on Lights on this Crazy Planet", CENTER, TOP);
                
                //draw 3 planets
                drawPlanet(widthH+200, halfH, size);
                drawPlanet(widthH-200, halfH/2, size* 0.3f);
                drawPlanet(widthH-50, halfH, size*0.5f);      
                break;
            }
            
        }
        
    }
}