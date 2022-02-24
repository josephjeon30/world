import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Audio {

	Long currentFrame;
	Clip clip;

	String status;

	AudioInputStream audioInputStream;

	static String filePath;

    public Audio(String filePath) throws 	
    	UnsupportedAudioFileException, 
		IOException, 
		LineUnavailableException {

		this.filePath = filePath;

    	audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());

    	clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-30.0f);
        clip.loop(0);
    }

    public void play(){
        clip.start();
          
        status = "play";
    }

    public void pause() {
        if (status.equals("paused")) 
        {
            System.out.println("audio is already paused");
            return;
        }

        this.currentFrame = this.clip.getMicrosecondPosition();
        clip.stop();
        status = "paused";
    }

    public void resumeAudio() throws 
    	UnsupportedAudioFileException,
        IOException, 
        LineUnavailableException {

        if (status.equals("play")){
            System.out.println("Audio is already being played");
            return;
        }

        clip.close();
        resetAudioStream();
        clip.setMicrosecondPosition(currentFrame);
        this.play();
    }

    public void stop() throws 
    	UnsupportedAudioFileException,
    	IOException, 
    	LineUnavailableException {

        currentFrame = 0L;
        clip.stop();
        clip.close();
    }

    public void resetAudioStream() throws 
    	UnsupportedAudioFileException, 
    	IOException,
       	LineUnavailableException{

        audioInputStream = AudioSystem.getAudioInputStream(
        new File(filePath).getAbsoluteFile());
        clip.open(audioInputStream);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public int getLength(String poopyButt){
    	return ((int) clip.getMicrosecondLength()) / 1000000;
    }

    public String getLength(){
    	return "" + (this.getLength("")/60) + ":" + (this.getLength("")%60);
    }

    public static String[] generatePlaylist(){
        String[] ans = new String[]{};
        String[] arr = generatePlaylist("songs");
        if (arr.length > 0){
            if (arr[0].equals(".DS_S")){
                ans = new String[arr.length - 1];
                for (int i = 0; i < ans.length; i++){
                    ans[i] = arr[i + 1];
                }
            }
        }
        return ans;
    }

    public static String[] generatePlaylist(String folderName){
        File dir = new File(folderName);
        File[] files = dir.listFiles();
        String[] ans = new String[files.length];
        for (int i = 0; i < files.length; i++){
            String temp = files[i].getPath();
            ans[i] = temp.substring(6, temp.length() - 4);
        }
        return ans;
    }
}