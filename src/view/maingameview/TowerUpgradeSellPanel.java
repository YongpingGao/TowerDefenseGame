package view.maingameview;

import protocol.DrawingPanelDelegate;
import model.imagecollection.TowerImageCollection;
import model.tower.Tower;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerUpgradeSellPanel extends JPanel implements DrawingPanelDelegate {

    private Tower tower;
    public JButton sellButton;
    public JButton upgradeButton;
    public JLabel towerImageLabel;

    public TowerUpgradeSellPanel(){
        setBackground(Color.black);
        tower = new Tower();
        sellButton = new JButton("Sell");
        upgradeButton = new JButton("Upgrade");
        towerImageLabel = new JLabel(new ImageIcon());


        setLayout(null);
        add(sellButton);
        add(towerImageLabel);
        add(upgradeButton);
        sellButton.setBounds(0, 0, 240, 58);
        towerImageLabel.setBounds(0, 58 , 240, 174);
        upgradeButton.setBounds(0, 231, 240, 58);
    }

    @Override
    public void reloadPanelBasedOnTower(Tower tower) {
        if(tower != null){
            this.tower = tower;
            towerImageLabel.setIcon(new ImageIcon(TowerImageCollection.towerImages.get(tower.getHighResolutionTowerImageName())));
        } else {
            towerImageLabel.setIcon(null);
        }

    }


}
