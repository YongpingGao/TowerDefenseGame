package model.drawing;

import model.imagecollection.TowerImageCollection;
import model.imagecollection.MapImageCollection;
import model.map.CellState;
import model.map.GameMap;
import model.tower.Tower;
import model.tower.TowerCollection;
import model.tower.TowerName;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Map;


/**
 * Created by yongpinggao on 1/29/16.
 */
public class GameMapDrawing extends Drawing {

    public static void drawMapAndTower(Graphics g, GameMap map, TowerCollection towerCollection, ImageObserver observer){
        Graphics2D g2d = (Graphics2D) g.create();
        ArrayList<CellState> cellList = map.getCells();
        int mapCols = map.getmCols();
        int index;
        for(int i = 0; i < CELL_SIZE * map.getmCols(); i = i + CELL_SIZE){
            for(int j = 0; j < CELL_SIZE * map.getmRows(); j = j + CELL_SIZE){

                switch (cellList.get(coordinateToIndexConverter(i, j, mapCols))){
                    case Grass:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Grass), i, j, observer);
                        break;
                    case Path:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Path), i, j, observer);
                        break;
                    case Entrance:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Entrance), i, j, observer);
                        break;
                    case Exit:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Exit), i, j, observer);
                        break;
                    case ToPlaceTower:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.ToPlaceTower), i, j, observer);
                        break;
                    case Tower:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Grass), i, j, observer);
                        index = coordinateToIndexConverter(i ,j , mapCols);
                        g2d.drawImage(TowerImageCollection.towerImages.get(towerCollection.getTowers().get(index).getTowerName()), i, j, observer);
                        break;
                    case Chosen:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Grass), i, j, observer);
                        index = coordinateToIndexConverter(i ,j , mapCols);
                        g2d.drawImage(TowerImageCollection.towerImages.get(towerCollection.getTowers().get(index).getTowerName()), i, j, observer);
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Chosen), i, j, observer);
                        break;

                }
            }
        }
        g2d.dispose();
    }

    public static void drawTowerRange(Graphics g, TowerCollection towerCollection, GameMap map, ImageObserver observer){

        Graphics2D g2d = (Graphics2D) g.create();
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
        g2d.setComposite(alphaComposite);
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        for (Map.Entry<Integer, Tower> entry : towerCollection.getTowers().entrySet()) {
            Tower tower = entry.getValue();
            if(tower.getTowerName() != TowerName.TowerNull) { // if TowerID == TOWERNULL, skip, draw nothing.
                g2d.draw(tower.getTowerRangeCircle());
                g2d.fill(tower.getTowerRangeCircle());
            }
        }
        g2d.dispose();
    }




}
