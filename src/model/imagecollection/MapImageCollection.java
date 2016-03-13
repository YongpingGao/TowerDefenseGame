package model.imagecollection;


import model.map.CellState;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by yongpinggao on 3/12/16.
 */
public class MapImageCollection extends ImageCollection {

    public static HashMap<CellState, Image> mapImages = new HashMap<>();

    static {
        mapImages.put(CellState.Grass, new ImageIcon(BASE_URL + CellState.Grass.getCellStateName() + PNG).getImage());
        mapImages.put(CellState.Path, new ImageIcon(BASE_URL + CellState.Path.getCellStateName() + PNG).getImage());
        mapImages.put(CellState.Entrance, new ImageIcon(BASE_URL + CellState.Entrance.getCellStateName() + PNG).getImage());
        mapImages.put(CellState.Exit, new ImageIcon(BASE_URL + CellState.Exit.getCellStateName() + PNG).getImage());
        mapImages.put(CellState.ToPlaceTower, new ImageIcon(BASE_URL + CellState.ToPlaceTower.getCellStateName() + PNG).getImage());
        mapImages.put(CellState.Tower, new ImageIcon(BASE_URL + CellState.Tower.getCellStateName() + PNG).getImage());
        mapImages.put(CellState.Chosen, new ImageIcon(BASE_URL + CellState.Chosen.getCellStateName() + PNG).getImage());
    }


}
