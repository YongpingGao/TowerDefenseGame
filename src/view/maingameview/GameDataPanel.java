package view.maingameview;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class GameDataPanel extends JPanel {

    public JLabel balanceLabel;
    public JLabel coinsLabel;
    public JLabel warningLabel;
    public JLabel waveStartLabel;
    public JLabel waveNumLabel;
    public JButton exitButton;


    public GameDataPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        balanceLabel = new JLabel("balanceLabel");
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10,10,0,0);
        add(balanceLabel, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        coinsLabel = new JLabel("coinsLabel");
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(10,10,0,0);
        add(coinsLabel, c);


        waveStartLabel = new JLabel("waveStartLabel");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,10,0,0);
        add(waveStartLabel, c);

        waveNumLabel = new JLabel("waveNumLabel");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 40;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10,10,0,0);
        add(waveNumLabel, c);

        warningLabel = new JLabel("warningLabel");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(10,0,0,0);  //top padding
        c.gridx = 0;
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        c.insets = new Insets(10,10,0,0);
        add(warningLabel, c);

        exitButton = new JButton("Quit");
        c.anchor = GridBagConstraints.PAGE_END;
        c.insets = new Insets(0,0,0,10);  //top padding
        c.gridx = 1;
        c.gridwidth = 1;   //2 columns wide
        c.gridy = 2;       //third row
        add(exitButton, c);
    }

}
