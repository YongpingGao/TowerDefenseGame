package view.maingameview;

import model.tower.Tower;
import protocol.DrawingMapInGameDelegate;
import model.drawing.CritterDrawing;
import model.map.GameMap;
import model.tower.TowerCollection;
import model.drawing.GameMapDrawing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class MapView extends JPanel {

    public MapPanel mapPanel;

    public MapView(){
        mapPanel = new MapPanel();
        setBackground(Color.black);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        add(mapPanel, c);
    }


    public class MapPanel extends JPanel implements DrawingMapInGameDelegate {

        private GameMap gameMap = new GameMap();
        private TowerCollection towerCollection = new TowerCollection();



        @Override
        public Dimension getPreferredSize() {
            return new Dimension(GameMapDrawing.CELL_SIZE * gameMap.getmCols(), GameMapDrawing.CELL_SIZE * gameMap.getmRows());
        }

        @Override
        public void refreshMap(GameMap map) {
            this.gameMap = map;
            repaint();
        }

        @Override
        public void refreshMap(GameMap map, TowerCollection towerCollection) {
            this.gameMap = map;
            this.towerCollection = towerCollection;
            repaint();
        }

        @Override
        public void refreshCrittersInMap() {
            repaint();
        }

        @Override
        public void refreshShootingEffectInMap(TowerCollection towerCollection) {
            this.towerCollection = towerCollection;
            repaint();
        }



        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            GameMapDrawing.drawMapAndTower(g, gameMap, towerCollection, this);
            CritterDrawing.drawCritters(g, this);
            CritterDrawing.drawHealthBar(g);
            GameMapDrawing.drawTowerRange(g, towerCollection, gameMap, this);
            towerCollection.drawShootingEffect(g);

        }


    }

}
