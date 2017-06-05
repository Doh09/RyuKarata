/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Excel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class SaveJPanelAsImage {

    public SaveJPanelAsImage() {
    }

    /**
     * Attempts to save the given image on the given directory path.
     *
     * @param path - The directory path to save the file to.
     * @return If successful, return true, if failed, return false.
     */
    public boolean saveImage(BufferedImage img, String path) {

        try {
            ImageIO.write(img, "png", new File(path + ".png"));
            return true;
        } catch (IOException ex) {
            System.err.println("Failed to save panel as image...");//EXCEPTION NEEDED
            return false;
        }
    }
}
