package Audio;
import javax.sound.sampled.*;

public class Audio_player{

	private Clip clip;
	
	public Audio_player(String s){
		
		try {
			AudioInputStream AIS = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));
			AudioFormat baseFormat = AIS.getFormat();
			AudioFormat decodeFormat = new AudioFormat(	AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), 
									   baseFormat.getChannels() * 2,	baseFormat.getSampleRate(),	false);
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, AIS);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void play(){
		
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop(){
		
		if(clip.isRunning()) clip.stop();
	}
	
	public void close(){
		
		stop();
		clip.close();
	}
	
}
