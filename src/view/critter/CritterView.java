package view.critter;

import view.critter.CritterType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/18/16.
 */
public class CritterView {

    private String BASE_URL = "res/";
    private String PNG = ".png";
    private Image critterImage;
    private ImageIcon critterImageIcon;

    public CritterView(CritterType critterType){
        this.critterImageIcon = new ImageIcon(BASE_URL + critterType.getCritterImageName() + PNG);
        this.critterImage = critterImageIcon.getImage();
    }

    public Image getCritterImage() {
        return critterImage;
    }

}
