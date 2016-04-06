package view.maingameview;

import model.tower.SplashTower;
import protocol.DrawingPanelDelegate;
import model.tower.Tower;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerUpgradeSellPanel extends JPanel implements DrawingPanelDelegate {

    private final static String[] towerStrategies = {"Target On Weakest", "Target On Strongest", "Target On Nearest to End"};

    public JButton sellButton;
    public JButton upgradeButton;
    public JLabel towerImageLabel;
    public JComboBox strategyComboBox;

    public TowerUpgradeSellPanel(){
        setBackground(Color.black);
        sellButton = new JButton("Sell");
        upgradeButton = new JButton("Upgrade");
        towerImageLabel = new JLabel(new ImageIcon());
        strategyComboBox = new JComboBox(towerStrategies);
        strategyComboBox.setEnabled(false);


        setLayout(null);
        add(sellButton);
        add(towerImageLabel);
        add(upgradeButton);
        add(strategyComboBox);
        sellButton.setBounds(0, 0, 118, 58);
        upgradeButton.setBounds(120, 0, 120, 58);
        towerImageLabel.setBounds(0, 58 , 240, 160);
        strategyComboBox.setBounds(0, 218, 240, 70);

    }

    @Override
    public void reloadPanelBasedOnTower(Tower tower) {
        if (tower != null) {
            if (tower.getPosition() != null) {
                sellButton.setEnabled(true);
                upgradeButton.setEnabled(true);
            } else {
                sellButton.setEnabled(false);
                upgradeButton.setEnabled(false);
            }
            towerImageLabel.setIcon(new ImageIcon(tower.getHdImageName()));
            strategyComboBox.setSelectedItem(tower.getTowerShootingBehavior().getShootingStrategy().toString());
            if (tower instanceof SplashTower) {
                strategyComboBox.setEnabled(false);
            } else {
                strategyComboBox.setEnabled(true);
            }
        } else {
            towerImageLabel.setIcon(null);
            strategyComboBox.setEnabled(false);
            sellButton.setEnabled(false);
            upgradeButton.setEnabled(false);
        }
    }

    @Override
    public void reloadLogPanelBasedOnIndexOfTower(int index) {
    }


}
