package view.maingameview;

import model.imagecollection.TowerImageCollection;
import model.tower.TowerName;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerSelectionPanel extends JPanel{

    public JButton towerAButton;
    public JButton towerBButton;
    public JButton towerCButton;

    public TowerSelectionPanel(){
        towerAButton = new JButton(new ImageIcon(TowerImageCollection.towerImages.get(TowerName.TowerAH)));
        towerBButton = new JButton(new ImageIcon(TowerImageCollection.towerImages.get(TowerName.TowerBH)));
        towerCButton = new JButton(new ImageIcon(TowerImageCollection.towerImages.get(TowerName.TowerCH)));
        setLayout(new GridLayout(1,3));
        setBackground(Color.blue);
        add(towerAButton);
        add(towerBButton);
        add(towerCButton);
    }
}
