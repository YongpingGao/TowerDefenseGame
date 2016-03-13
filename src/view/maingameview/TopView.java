package view.maingameview;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TopView extends JPanel{

    public TowerSelectionPanel towerSelectionPanel;
    public GameDataPanel gameDataPanel;

    public TopView(){
        setLayout(new GridLayout(1,2));
        towerSelectionPanel = new TowerSelectionPanel();
        gameDataPanel = new GameDataPanel();
        gameDataPanel.setBackground(Color.WHITE);

        this.add(towerSelectionPanel);
        this.add(gameDataPanel);
    }

}
