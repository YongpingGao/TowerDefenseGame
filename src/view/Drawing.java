package view;

import model.imagecollection.TowerImageCollection;
import model.map.CellState;
import model.map.GameMap;
import model.imagecollection.MapImageCollection;
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
public class Drawing {

    // cell image size in pixels
    public final static int CELL_SIZE = 30;

    private final static float ALPHA = 0.3f;
    // input: coordinate(x,y)(pixels), cell size of a cell. And cols number
    // output: nth cell in whole map
    public static int coordinateToIndexConverter(int x, int y, int cols) {
        return x / CELL_SIZE + (y / CELL_SIZE) * cols;
    }

    public static int[] indexToCoordinateConverter(int index, int cols){
        int x = index % cols;
        int y = index / cols;
        return new int[]{x * CELL_SIZE, y * CELL_SIZE};
    }

    public static void drawMap(Graphics g, GameMap map, ImageObserver observer){
        Graphics2D g2d = (Graphics2D) g.create();
        ArrayList<CellState> cellList = map.getCells();
        int mapCols = map.getmCols();
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
                        g2d.drawImage(TowerImageCollection.towerImages.get(TowerName.TowerA1), i, j, observer);
                        break;
                    case Chosen:
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Grass), i, j, observer);
                        g2d.drawImage(MapImageCollection.mapImages.get(CellState.Chosen), i, j, observer);
                        break;

                }
            }
        }
        g2d.dispose();
    }

    public static void drawTowers(Graphics g, TowerCollection towerCollection, GameMap gameMap, ImageObserver observer){

//        if(towerCollection.getTowers().size() > 0){
//            Graphics2D g2d = (Graphics2D) g.create();;
//            for (Map.Entry<Integer, Tower> entry : towerCollection.getTowers().entrySet()) {
//                Integer index = entry.getKey();
//                Tower tower = entry.getValue();
//
//                if(tower.getTowerName() != TowerName.TowerNull) { // if TowerID == TowerNull, skip, draw nothing.
//                    int[] arr = indexToCoordinateConverter(index, gameMap.getmCols());
//                    g2d.drawImage(TowerImageCollection.towerImages.get(tower.getTowerName()), arr[0], arr[1], observer);
//                }
//            }
//            g2d.dispose();
//        }


    }
//
//    public static void drawCritters(Graphics g, Critter c, ImageObserver observer){
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.drawImage(c.getImage(), c.getPosX(), c.getPosY(),observer);
//        g2d.dispose();
//
//    }
//
//    public static void drawTowerRange(Graphics g, HashMap<Integer, Tower> towerMap, ImageObserver observer){
//
//        Graphics2D g2d = (Graphics2D) g.create();
//        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
//        g2d.setComposite(alphaComposite);
//        // Some rendering configuration
//        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2d.setRenderingHints(rh);
//
//        for (Map.Entry<Integer, Tower> entry : towerMap.entrySet()) {
//            Tower tower = entry.getValue();
//            if(tower.getTid() != TowerID.TowerNull) { // if TowerID == TowerNull, skip, draw nothing.
//
//                int posX = tower.getPosX();
//                int posY = tower.getPosY();
//                int range = tower.getRange();
//
//                Ellipse2D rangeCircle = new Ellipse2D.Float(posX - range/2,posY - range/2,range,range);
//                g2d.draw(rangeCircle);
//                g2d.fill(rangeCircle);
//
//            }
//        }
//
//        g2d.dispose();
//    }
//
//    public static void drawMissiles(Graphics g, Tower tower, Critter critter) {
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setStroke(MissileCollection.missiles.get(tower.getTid()));
//        g2d.drawLine(tower.getPosX(), tower.getPosY(), critter.getPosX()+ CELL_SIZE/2, critter.getPosY()+ CELL_SIZE/2);
//        g2d.dispose();
//
//    }
//
//    public static  void drawHealthBar(Graphics g, float healthBar, Critter c){
//        Graphics2D g2d = (Graphics2D) g.create();
//        g2d.setColor(Color.GREEN);
//        g2d.fillRect(c.getPosX(), c.getPosY() - 5, (int) (healthBar * CELL_SIZE), 3);
//        g2d.dispose();
//    }



}
