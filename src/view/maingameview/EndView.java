package view.maingameview;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class EndView extends JPanel{

    public TowerSpecificationPanel towerSpecificationPanel;
    public TowerUpgradeSellPanel towerUpgradeSellPanel;

    public EndView(){
        setLayout(new GridLayout(2,1));
        towerUpgradeSellPanel= new TowerUpgradeSellPanel();
        towerSpecificationPanel = new TowerSpecificationPanel();
        add(towerUpgradeSellPanel);
        add(towerSpecificationPanel);
    }
}
