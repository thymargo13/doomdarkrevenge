package Audio;
import javax.sound.sampled.*;

/**
 * 
 * @author Bitnarae Kim
 * Audio_player class is for getting the audio to play
 */
public class Audio_player{

	public static Clip clip;
	
	/**
	 *  plays and loops the music
	 * @param s : the string for the path to the audio file
	 * 
	 */
	public Audio_player(String s){
		
		try {
			
			AudioInputStream AIS = AudioSystem.getAudioInputStream(getClass().getResourceAsStream(s));  //makes the audio file an Audio Input Stream
			AudioFormat baseFormat = AIS.getFormat(); //the audio format
			AudioFormat decodeFormat = new AudioFormat(	AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(), 
									   baseFormat.getChannels() * 2,	baseFormat.getSampleRate(),	false); //decode the format
			AudioInputStream dais = AudioSystem.getAudioInputStream(decodeFormat, AIS); //get the decoded format
			clip = AudioSystem.getClip();
			clip.open(dais); 
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Check if clip is there and play
	 */
	public void play(){		
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	/**
	 *  Stops the music
	 */
	public void stop(){		
		if(clip.isRunning()) clip.stop();
	}
	
	
}
