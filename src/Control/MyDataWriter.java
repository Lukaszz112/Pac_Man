package Control;

import Model.PacMan.PacMan;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class MyDataWriter {
    String filePath = "highscore.txt";
    Path relativePath = Paths.get("src", "Model", filePath);
    File file = relativePath.toFile();
    public void writeDataToFile(PacMan pacMan) {
        String name = JOptionPane.showInputDialog(null, "Your name:");

        if (name != null && !name.isEmpty()) {
            String data = name + "," + pacMan.getScore() + "," + pacMan.getTimer().toString() ;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(data);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
