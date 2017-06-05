/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL.Facades;

import BLL.ScreenImage;
import DAL.Excel.SaveJPanelAsImage;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 *
 * @author Simon, Martin, Alex, Casper
 */
public class FacadeToDAL {

    private static FacadeToDAL instance;
    
    private FacadeToDAL() {
    }

    public static FacadeToDAL getInstance() {
        if (instance == null) {
            instance = new FacadeToDAL();
        }
        return instance;
    }

    private SaveJPanelAsImage savePanelAsImage = new SaveJPanelAsImage();

    /**
     * Attempts to save the given JComponent on the given directory path.
     *
     * @param panel - The panel to be saved as an image.
     * @param path - The directory path to save the file to.
     * @return If successful, return true, if failed, return false.
     */
    public boolean savePanelAsImage(BufferedImage image, String path) {
        return savePanelAsImage.saveImage(image, path);
    }
}
