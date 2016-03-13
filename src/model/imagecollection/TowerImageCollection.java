package model.imagecollection;

import model.tower.TowerName;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerImageCollection extends ImageCollection {

    public static HashMap<TowerName, Image> towerImages = new HashMap<>();

    static {
        towerImages.put(TowerName.TowerA1, new ImageIcon(BASE_URL + TowerName.TowerA1.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerA2, new ImageIcon(BASE_URL + TowerName.TowerA2.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerA3, new ImageIcon(BASE_URL + TowerName.TowerA3.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerAH, new ImageIcon(BASE_URL + TowerName.TowerAH.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerB1, new ImageIcon(BASE_URL + TowerName.TowerB1.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerB2, new ImageIcon(BASE_URL + TowerName.TowerB2.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerB3, new ImageIcon(BASE_URL + TowerName.TowerB3.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerBH, new ImageIcon(BASE_URL + TowerName.TowerBH.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerC1, new ImageIcon(BASE_URL + TowerName.TowerC1.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerC2, new ImageIcon(BASE_URL + TowerName.TowerC2.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerC3, new ImageIcon(BASE_URL + TowerName.TowerC3.getTowerName() + PNG).getImage());
        towerImages.put(TowerName.TowerCH, new ImageIcon(BASE_URL + TowerName.TowerCH.getTowerName() + PNG).getImage());
    }


}
