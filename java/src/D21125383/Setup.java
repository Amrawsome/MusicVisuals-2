package D21125383;

import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;



public class Setup extends PApplet {

     // pause/play key
     int mode = 0;
     int cols;
     int rows;
     int scale =20;
     int w = 5000;
     int h = 3000;
     
     final int SX = 1024;
     final int SY = 600;
     private int frameSize = 512;
     private int sampleRate = 44100;

     float y = 0;
     float smoothedY = 0;
     float smoothedAmplitude = 0;
     float off = 0;
     float n4;
     float n6;
     float per = 0;
     float sum =0;
     float average =0;
     float value =0;
     
     //buffer
     float[] lerpedBuffer;
     float[][] land;
     private float[] bands;
     private float[] smoothedBands;

    private Minim minim;
	private AudioPlayer audioPlayer;
	private AudioBuffer audioBuffer;
	private FFT fft;
     

    public void setup(){
        colorMode(RGB);
        y = height / 2;
        smoothedY = y;
        cols =w/scale;
        rows=h/scale;
        lerpedBuffer = new float[width];
	}

    public void startMinim() 
	{
		minim = new Minim(this);

		fft = new FFT(frameSize, sampleRate);

		bands = new float[(int) log2(frameSize)];
  		smoothedBands = new float[bands.length];

	}
    
    float log2(float f) {
		return log(f) / log(2.0f);
	}

    public void calculateAverageAmplitude()
	{
        for(int i = 0 ; i < audioBuffer.size() ; i ++)
        {
            sum += abs(audioBuffer.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], audioBuffer.get(i), 0.05f);
        }
        average= sum / (float) getAudioBuffer().size();

        smoothedAmplitude = lerp(smoothedAmplitude, average, 0.01f);
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

    

    public float[] getSmoothedBands() {
        return smoothedBands;
    }

    public void setSmoothedBands(float[] smoothedBands) {
        this.smoothedBands = smoothedBands;
    }

    public FFT getFft() {
        return fft;
    }

    public void setFft(FFT fft) {
        this.fft = fft;
    }
   
    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public void setAudioPlayer(AudioPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    public AudioBuffer getAudioBuffer() {
        return audioBuffer;
    }

    public void setAudioBuffer(AudioBuffer audioBuffer) {
        this.audioBuffer = audioBuffer;
    }
    
	public void loadAudio(String filename)
	{
		audioPlayer = minim.loadFile(filename, frameSize);
		audioBuffer = audioPlayer.mix;
	}

    
    
}


