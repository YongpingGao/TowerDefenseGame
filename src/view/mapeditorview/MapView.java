package view.mapeditorview;

import model.map.GameMap;
import Protocol.DrawingMapDelegate;
import view.BaseWindowView;
import view.Drawing;

import javax.swing.*;
import java.awt.*;

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

        @Override
        public void refreshMap(GameMap map) {
            this.map = map;
            mapPanel.revalidate();
            repaint();
        }
    }






}
