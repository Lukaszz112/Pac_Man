package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaySong {
    public void play(String soundFilePath) {
        try {
            Path relativePath = Paths.get("src", "Model", "resources", soundFilePath);
            File soundFile = relativePath.toFile();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}