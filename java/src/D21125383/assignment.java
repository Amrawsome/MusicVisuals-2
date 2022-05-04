package D21125383;

import java.util.Random;

import ddf.minim.AudioBuffer;
import ddf.minim.AudioInput;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import ie.tudublin.Visual;
import processing.core.PApplet;

public class assignment extends Visual{
    
    //variables
    Visual visuals;
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
    float off = 0;

    //laura
    // for speaker circle thingy
    float n4;
    float n6;

    // for progress bar
    float per = 0;
    final int SX = 1024;
    final int SY = 600;

    //end laura

    //stephen
    Random rand = new Random(123456789L);
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
    
    //end stephen
    
    //if space key is pressed pause/play
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
        // startMinim();
        // loadAudio("Heaven.mp3");
        // getAudioPlayer().play();
        minim = new Minim(this);
        audioPlayer = minim.loadFile("Heaven.mp3", 1024);
        audioPlayer.play();
        audioBuffer = audioPlayer.mix;

        //set color mode to RGB
        colorMode(RGB);
        y = height / 2;
        smoothedY = y;
        cols =w/scale;
        rows=h/scale;
        lerpedBuffer = new float[width];
    }
    public void Stephen(float q){
        land =new float [cols][rows];
                float yoff = q;
                for(int y =0; y < rows;y++){
                    float xoff = 0;
                    for(int x =0; x < cols; x++){
                        land[x][y] =map(noise(xoff, yoff),0,1,-50,50) ;
                        xoff +=1;
                    } 
                    yoff+=q;
                }
                stroke(map(mouseX, 0, width, 100, 225),map(mouseY, 0, height, 100, 225),0);
                noFill(); 
                if(mousePressed){
                    fill(map(mouseY, 0, width, 70, 225),0,map(mouseX, 0, height, 70, 120));
                } 
                pushMatrix();
                translate(width/2, height/2-280);
                sphere(q*1000);
                popMatrix();

                noFill(); 
                if(mousePressed){
                    fill(map(mouseY, 0, width, 70, 120),0,map(mouseX, 0, height, 70, 120));
                } 
                translate(width/2, height/2);
                rotateX(PI/2.2f);
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
     void loadingBar(){
        //progress bar
        background(0);
        per = (float) ((per + 0.16) % 100); 
        //System.out.println(per);
        textSize(30);
        fill(255);
        text("Loading ... " + per + " %", SX / 4, (float) (SY / 2.5));
        rect(SX / 4, SY / 2, per * 2, 20, 7); 
        //System.out.println(per);
    }
    void speaker(){
        // like a speaker circle thingy
        noCursor();
        smooth();
        background (0);
        frameRate(24);

        fill(0,50);  
        noStroke();
        rect(0, 0, width, height);
        translate(width/2, height/2);
        
        for (int i = 0; i < audioPlayer.bufferSize() - 1; i++) {
        
            float angle = sin(i+n4)* 10; 
            float angle2 = sin(i+n6)* 300; 
            
            float x = sin(radians(i))*(angle2+30); 
            float y = cos(radians(i))*(angle2+30);
            
            float x3 = sin(radians(i))*(500/angle); 
            float y3 = cos(radians(i))*(500/angle);
            
            fill(128,0,128); // purple
            ellipse(x, y, audioPlayer.left.get(i)*10, audioPlayer.left.get(i)*10);
            
            fill(255,255,255); // white
            rect(x3, y3, audioPlayer.left.get(i)*20, audioPlayer.left.get(i)*10);
            
            fill(186,85,211); // medium orchid
            rect(x, y, audioPlayer.right.get(i)*10, audioPlayer.left.get(i)*10);
            
            fill(255,255,255); // white
            rect(x3, y3, audioPlayer.right.get(i)*10, audioPlayer.right.get(i)*20);
        }  

        n4 += 0.008;
        n6 += 0.04;
   }

    //draw planet method 
    void drawPlanet(float s, float g, float size) 
    { 

        //position of planet
        translate(s, g);
        if(mousePressed)
        {
            lights();
            ambientLight(200, 20, 250);
            directionalLight(255, 60, 126, -1, 0, 0);
        }
        
        ambientLight(100, 20, 100);
        directionalLight(51, 102, 126, -1, 0, 0);
        
        //rotate planet on x, y , z axis
        rotateY((float) (frameCount/100.0));
        rotateX((float) (frameCount/50.0));
        rotateZ((float) (frameCount/50.0));

        //fill
        fill(100);
        noStroke();

        //draw sphere
        sphere(size);

        //loop through buffer and draw the hoops around the planet
        for(int i = 0 ; i < audioBuffer.size()/2 ; i ++)
        {
            
            float c = map(i, 0, audioBuffer.size(), 0, 255);
            float f = lerpedBuffer[i] * height/2 * 4.0f;

            noStroke();
            
            //rotate the hoops around planet
            rotate(c);
            fill(s, f, c);

            //draw hoops
            circle(f*0.6f , i, f*0.4f);
            circle(f * 0.8f, i, f *0.4f);  
        }
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
                 //progress bar
                 background(0);
                loadingBar();
                 if(per >= 99.6f){
                    mode=1;
                }
                 
                break;
            }
            case 1:
            {
                 // like a speaker circle thingy
                speaker();
                break;  
            }
            
            case 2:
            {
                background(0); 
                //print instructions on screen
                textSize((float)(25));	
                fill(200, 140); 
                text("Press Mouse to turn on Lights on this Crazy Planet", CENTER, TOP);
                
                //draw 3 planets
                drawPlanet(widthH+200, halfH, size);
                drawPlanet(widthH-200, halfH/2, size* 0.3f);
                drawPlanet(widthH-50, halfH, size*0.5f);    
               
                 break;
                
            }

            case 3:
            {
                background(0);
                textSize((float)(25));	
                fill(200, 140); 
                text("Move Mouse Around To Change Colour And Brightness", CENTER, TOP);
                text("Press Mouse To Fill, Move Around To Change Colour", CENTER, TOP-35);
                Stephen(average);
                break;
            }
        }
    }

}
