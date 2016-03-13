package view.mapeditorview;

import Model.Map.GameMap;
import Protocol.DrawingMapDelegate;
import view.BaseWindowView;
import view.Drawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Created by yongpinggao on 3/12/16.
 */
public class MapView extends JPanel {

    public MapPanel mapPanel;


    public MapView(){
        setPreferredSize(new Dimension(BaseWindowView.WINDOW_WIDTH, BaseWindowView.WINDOW_HEIGHT / 10 * 9));
        mapPanel = new MapPanel();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.CENTER;
        add(mapPanel, c);
    }

    public class MapPanel extends JPanel implements DrawingMapDelegate {

        private GameMap map = new GameMap();

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(Drawing.CELL_SIZE * map.getmCols(), Drawing.CELL_SIZE * map.getmRows());
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Drawing.drawMap(g, map, this);
        }


//        public void clearMap(){
//            // it will let layout manager run again!
//            mapPanel.revalidate();
//            cellList.clear();
//            for(int i = 0; i < mapCols * mapRows; i++)
//                cellList.add(CellState.GRASS);
//            repaint();
//        }
//
//        private BufferedImage mapCaptureShot() {
//            BufferedImage image = new BufferedImage(DrawMap.CELL_SIZE * mapCols, DrawMap.CELL_SIZE * mapRows, BufferedImage.TYPE_INT_RGB);
//            Graphics g = image.createGraphics();
//            print(g);
//            g.dispose();
//            return image;
//        }

        @Override
        public void refreshMap(GameMap map) {
            this.map = map;
            mapPanel.revalidate();
            repaint();
        }
    }






}
