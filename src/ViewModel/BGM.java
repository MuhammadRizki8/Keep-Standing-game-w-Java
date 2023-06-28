//kelas untuk memproses background music
package ViewModel;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BGM {
    public Clip playSound(Clip clip, String filename) {
        try {
            // ambil data bgm
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("src/resources/" + filename).getAbsoluteFile());
            clip = AudioSystem.getClip();
            
            clip.open(audioInput);  // buka input
            clip.start();   // mulai
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop
        } catch(UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(LineUnavailableException e) {
            e.printStackTrace();
        }
        
        return clip;
    }
    
    // Stop BGM
    public void stopSound(Clip clip) {
        clip.stop();
    }
}
