package ie.tudublin;
import processing.core.PApplet;
import ddf.minim.*;
import ddf.minim.analysis.FFT;


public class Setup extends PApplet {

    private int frameSize = 512;
	private int sampleRate = 44100;

	private float[] bands;
	private float[] smoothedBands;
	float [] lerpedBuffer;

	private Minim minim;
	private AudioPlayer audioPlayer;
	private AudioBuffer audioBuffer;
	private FFT fft;

	private float amplitude  = 0;
	private float smoothedAmplitude = 0;

    public void setup(){
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
        float total =0;
        for(int i = 0 ; i < audioBuffer.size() ; i ++)
        {
            total += abs(audioBuffer.get(i));
            lerpedBuffer[i] = lerp(lerpedBuffer[i], audioBuffer.get(i), 0.05f);
        }
        amplitude= total / (float) audioBuffer.size();

        smoothedAmplitude = lerp(smoothedAmplitude, amplitude, 0.01f);
	}

  

	public void loadAudio(String filename)
	{
		audioPlayer = minim.loadFile(filename, frameSize);
		audioBuffer = audioPlayer.mix;
	}

    public int getFrameSize() {
        return frameSize;
    }

    public void setFrameSize(int frameSize) {
        this.frameSize = frameSize;
    }

    public int getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(int sampleRate) {
        this.sampleRate = sampleRate;
    }

    public float[] getBands() {
        return bands;
    }

    public void setBands(float[] bands) {
        this.bands = bands;
    }

    public float[] getSmoothedBands() {
        return smoothedBands;
    }

    public void setSmoothedBands(float[] smoothedBands) {
        this.smoothedBands = smoothedBands;
    }

    public float[] getLerpedBuffer() {
        return lerpedBuffer;
    }

    public void setLerpedBuffer(float[] lerpedBuffer) {
        this.lerpedBuffer = lerpedBuffer;
    }

    public Minim getMinim() {
        return minim;
    }

    public void setMinim(Minim minim) {
        this.minim = minim;
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

    public FFT getFft() {
        return fft;
    }

    public void setFft(FFT fft) {
        this.fft = fft;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(float amplitude) {
        this.amplitude = amplitude;
    }

    public float getSmoothedAmplitude() {
        return smoothedAmplitude;
    }

    public void setSmoothedAmplitude(float smoothedAmplitude) {
        this.smoothedAmplitude = smoothedAmplitude;
    }

    
}


