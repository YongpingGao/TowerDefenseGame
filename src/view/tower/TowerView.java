package view.tower;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/19/16.
 */
public class TowerView {

    private String BASE_URL = "res/";
    private String PNG = ".png";
    private Image towerImage;


    public TowerView(TowerType towerType) {
        this.towerImage = new ImageIcon(BASE_URL + towerType.getTowerImageName() + PNG).getImage();
    }

    public Image getTowerImage() {
        return towerImage;
    }
}
