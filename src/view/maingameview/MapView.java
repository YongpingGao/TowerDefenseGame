package view.maingameview;

import Protocol.DrawingTowersDelegate;
import model.map.GameMap;
import Protocol.DrawingMapDelegate;
import model.tower.TowerCollection;
import view.Drawing;

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


    public class MapPanel extends JPanel implements DrawingMapDelegate, DrawingTowersDelegate{

        private GameMap gameMap = new GameMap();
        private TowerCollection towerCollection = new TowerCollection();

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(Drawing.CELL_SIZE * gameMap.getmCols(), Drawing.CELL_SIZE * gameMap.getmRows());
        }

        @Override
        public void refreshMap(GameMap map) {
            this.gameMap = map;
            repaint();
        }

        @Override
        public void refreshAllTowers(TowerCollection towerCollection) {
            this.towerCollection = towerCollection;
            repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Drawing.drawMap(g, gameMap, this);
            Drawing.drawTowers(g, towerCollection, gameMap, this);
        }


    }

}
