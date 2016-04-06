package view.maingameview;

import model.tower.TowerFactory;
import view.tower.TowerType;

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
        towerAButton = new JButton(new ImageIcon(TowerFactory.sharedInstance().getTower(TowerType.BurningTower1).getHdImageName()));
        towerBButton = new JButton(new ImageIcon(TowerFactory.sharedInstance().getTower(TowerType.IceTower1).getHdImageName()));
        towerCButton = new JButton(new ImageIcon(TowerFactory.sharedInstance().getTower(TowerType.SplashTower1).getHdImageName()));
        setLayout(new GridLayout(1,3));
        setBackground(Color.blue);
        add(towerAButton);
        add(towerBButton);
        add(towerCButton);
    }
}
