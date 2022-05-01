package ie.tudublin;

import ddf.minim.*;
import processing.core.PApplet;

public class caAssignment extends PApplet
{

    Minim minim;
    AudioPlayer heaven;

    int mode = 0;

    float y = 0;
    float smoothedY = 0;
    float smoothedAmplitude = 0;

    float[] lerpedBuffer;
    float off = 0;
    

    //for case 6
    float n4;
    float n6;

    //for progress bar
    float per = 0;
    final int SX = 1024;
    final int SY = 600;


    public void keyPressed() {
		if (key >= '0' && key <= '9') {
			mode = key - '0';
		}
		if (keyCode == ' ') {
            if (heaven.isPlaying()) {
                heaven.pause();
            } else {
                heaven.rewind();
                heaven.play();
            }
        }
	}

    public void settings() 
    {
        size(1024, 600);
    }

    public void setup()
    {
        colorMode(RGB);

        minim = new Minim(this);
        heaven = minim.loadFile("differentheaven.mp3", 1024);
        heaven.loop();

        lerpedBuffer = new float[width];

        
    }

    public void draw()
    {
        
        // Lerped buffer
        float halfH = height / 2;
        float average = 0;
        float sum = 0;
        off += 1;
        // Calculate sum and average of the samples
        // Also lerp each element of buffer;
        for(int i = 0 ; i < heaven.bufferSize(); i ++)
        {
            sum += abs(heaven.bufferSize());
            lerpedBuffer[i] = lerp(lerpedBuffer[i], heaven.bufferSize(), 0.05f);
        }
        average = sum / (float) heaven.bufferSize();
        

        switch (mode) {
			case 0:
                {
                    //progress bar
                    background(0);
                    per = (float) ((per + 0.15) % 100); 
                    textSize(30);
                    text("Loading ... " + per + " %", SX / 4, (float) (SY / 2.5));
                    rect(SX / 4, SY / 2, per * 2, 20, 7);
                    
                }
                break;
            case 1:
                {
                    // like a speaker circle thingy

                    noCursor();
                    smooth();
                    background (0);
                    frameRate(24);

                    fill(0,50);  
                    noStroke();
                    rect(0, 0, width, height);
                    translate(width/2, height/2);
                    
                    for (int i = 0; i < heaven.bufferSize() - 1; i++) {
                    
                        float angle = sin(i+n4)* 10; 
                        float angle2 = sin(i+n6)* 300; 
                        
                        float x = sin(radians(i))*(angle2+30); 
                        float y = cos(radians(i))*(angle2+30);
                        
                        float x3 = sin(radians(i))*(500/angle); 
                        float y3 = cos(radians(i))*(500/angle);
                        
                        fill (128,0,128); // purple
                        ellipse(x, y, heaven.left.get(i)*10, heaven.left.get(i)*10);
                        
                        fill (255,255,255); // white
                        rect(x3, y3, heaven.left.get(i)*20, heaven.left.get(i)*10);
                        
                        fill (186,85,211); // medium orchid
                        rect(x, y, heaven.right.get(i)*10, heaven.left.get(i)*10);
                        
                        fill(255,255,255); // white
                        rect(x3, y3, heaven.right.get(i)*10, heaven.right.get(i)*20);
                    }  

                    n4 += 0.008;
                    n6 += 0.04;
                }
                break;
            case 2:
                {
                    // vertical striped lines
                    background(0);
                    strokeWeight(2);
                    for(int i = 0 ; i < heaven.bufferSize() ; i +=10)
                    {
                        //float c = map(ab.get(i), -1, 1, 0, 255);
                        float cc = map(i, 0, heaven.bufferSize(), 0, 255);
                        stroke(cc, 255, 255);
                        float f = lerpedBuffer[i] * halfH * 4.0f;
                        line(i, halfH + f, i, halfH - f);
                        fill(cc);
                        circle(i, halfH + f, 5);                    
                        circle(i, halfH - f, 5);                    
                    }
                }   
                break;
            case 3:
                {
                    // waveforms

                    background(0);
                    stroke(255);
                    
                    for(int i = 0; i < heaven.bufferSize() - 1; i++)
                    {
                        float x1 = map( i, 0, heaven.bufferSize(), 0, width );
                        float x2 = map( i+1, 0, heaven.bufferSize(), 0, width );
                        line( x1, 50 + heaven.left.get(i)*50, x2, 50 + heaven.left.get(i+1)*50 );
                        line( x1, 150 + heaven.right.get(i)*50, x2, 150 + heaven.right.get(i+1)*50 );
                    }
                    
                    noStroke();
                    fill( 255, 128 );
                    
                    rect( 0, 0, heaven.left.level()*width, 100 );
                    rect( 0, 100, heaven.right.level()*width, 100 );
                }
                break;
            case 4:
                {
                    // little person

                    background(0);
                    ellipseMode(CENTER);
                    rectMode(CENTER); 

                    // Body
                    stroke(0);
                    fill(150);
                    rect(240, 145, 20, 100);

                    // Head
                    fill(255);
                    ellipse(240, 115, 60, 60); 

                    // Eyes
                    fill(0); 
                    ellipse(221, 115, 16, 32); 
                    ellipse(259, 115, 16, 32);

                    // Legs
                    stroke(0);
                    line(230, 195, 220, 205);
                    line(250, 195, 260, 205);  
                }
                break;
            case 5:
                {

                }
                break;
            case 6:
                {
                    
                }
                break;
            case 7:
                {
                    

                }
                break;
            
                
        }// end switch
    }// end draw method
}// end class