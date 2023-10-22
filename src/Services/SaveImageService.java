package Services;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SaveImageService {
    public void saveImage(RBIService raster) {
        // uložení do souboru .jpg
        // otevře se FileChooser a tam si uživatel vybere kam to chce uložit a pod jakým jménem
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.setDialogTitle("Save image to location:");
        int selection = chooseFile.showSaveDialog(null);

        if (selection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooseFile.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.toLowerCase().endsWith(".jpg")) {
                filePath += ".jpg";
                fileToSave = new File(filePath);
            }

            try {
                ImageIO.write(raster.getImg(), "jpg", fileToSave);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
