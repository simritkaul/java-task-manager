import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundUtils {
    public static void playSound (String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            Thread.sleep(clip.getMicrosecondLength()/1000);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
