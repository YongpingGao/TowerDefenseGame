package view.maingameview;

import model.gamelog.LoggerCollection;
import protocol.DrawingPanelDelegate;
import model.tower.Tower;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerUpgradeSellPanel extends JPanel implements DrawingPanelDelegate {

    private final static String[] towerStrategies = {"Target On Weakest", "Target On Strongest", "Target On Nearest to End", "Target On Closest to Tower"};

    public JButton sellButton;
    public JButton upgradeButton;
    public JLabel towerImageLabel;
    public JComboBox strategyComboBox;
    public JTextArea towerLogArea;
    public JScrollPane towerLogScrollPane;

    public TowerUpgradeSellPanel(){
        setBackground(Color.black);
        sellButton = new JButton("Sell");
        upgradeButton = new JButton("Upgrade");
        towerImageLabel = new JLabel(new ImageIcon());
        strategyComboBox = new JComboBox(towerStrategies);
        towerLogArea = new JTextArea(3, 5);
        towerLogArea.setEditable(false);
        towerLogScrollPane = new JScrollPane(towerLogArea);

        setLayout(null);
        add(sellButton);
        add(towerImageLabel);
        add(upgradeButton);
        add(towerLogScrollPane);
        add(strategyComboBox);
        sellButton.setBounds(0, 0, 118, 58);
        upgradeButton.setBounds(120, 0, 120, 58);
        towerImageLabel.setBounds(0, 58 , 240, 132);
        strategyComboBox.setBounds(0, 188, 240, 30);
        towerLogScrollPane.setBounds(0, 218, 240, 70);
        towerLogArea.setCaretPosition(towerLogArea.getDocument().getLength());

    }

    @Override
    public void reloadPanelBasedOnTower(Tower tower) {
        if(tower != null){
            towerImageLabel.setIcon(new ImageIcon(tower.getHdImageName()));
        } else {
            towerImageLabel.setIcon(null);

        }

    }

    @Override
    public void reloadLogPanelBasedOnIndexOfTower(int index) {
        towerLogArea.setText("");
        towerLogArea.append(LoggerCollection.getInstance().showTowerLogAtIndex(index));
    }


}
