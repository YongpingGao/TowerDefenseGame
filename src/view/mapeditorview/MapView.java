package view.mapeditorview;

import model.map.GameMap;
import protocol.DrawingMapDelegate;
import view.BaseWindowView;
import view.map.GameMapDrawing;

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
            return new Dimension(GameMapDrawing.CELL_SIZE * map.getmCols(), GameMapDrawing.CELL_SIZE * map.getmRows());
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            GameMapDrawing.drawGameMap(g, map);
        }


        @Override
        public void refreshMap(GameMap map) {
            this.map = map;
            mapPanel.revalidate();
            repaint();
        }
    }






}
