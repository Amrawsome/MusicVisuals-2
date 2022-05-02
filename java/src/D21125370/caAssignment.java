package D21125370;

import ddf.minim.*;
import processing.core.PApplet;

public class caAssignment extends PApplet
{

    Minim minim;
    AudioPlayer heaven;

    int mode = 0;    

    // for speaker circle thingy
    float n4;
    float n6;

    // for progress bar
    float per = 0;
    final int SX = 1024;
    final int SY = 600;

    // for text
    String message = "Goodbye!";
    float x, y; // X and Y coordinates of text
    float hr, vr;  // horizontal and vertical radius of the text

    
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
        fullScreen(P3D, SPAN);
    }

    public void setup()
    {
        colorMode(RGB);

        minim = new Minim(this);
        heaven = minim.loadFile("differentheaven.mp3", 1024);
        heaven.play();

        // Create the font
        textSize(36);      
        x = width / 2;
        y = height / 2;
        
    }

    public void draw()
    {     
        switch (mode) {
			case 0:
                {
                    text("Goodbye!", x, y);



                    // little person

                    background(0);
                    ellipseMode(CENTER);
                    rectMode(CENTER); 

                    //text
                    text("Goodbye!", 0, 0);

                    // Body
                    stroke(0);
                    fill(150);
                    rect(500, 45, 20, 100);

                    // Head
                    fill(255);
                    ellipse(500, 15, 60, 60); 

                    // Eyes
                    fill(0); 
                    ellipse(221, 115, 16, 32); 
                    ellipse(259, 115, 16, 32);

                    // Legs
                    stroke(0);
                    line(230, 195, 220, 205);
                    line(250, 195, 260, 205);  


                    /*
                    //progress bar
                    background(0);
                    per = (float) ((per + 0.15) % 100); 
                    textSize(30);
                    text("Loading ... " + per + " %", SX / 4, (float) (SY / 2.5));
                    rect(SX / 4, SY / 2, per * 2, 20, 7); */
                    
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
                
        }// end switch
    }// end draw method
}// end class

/*

*/