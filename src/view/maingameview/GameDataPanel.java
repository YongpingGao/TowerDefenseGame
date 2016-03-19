package view.maingameview;

import protocol.DrawingDataPanelDelegate;
import sun.jvm.hotspot.types.JBooleanField;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class GameDataPanel extends JPanel implements DrawingDataPanelDelegate{

    public JButton TargetBasedOnWeakestButton;
    public JButton TargetBasedOnStrongestButton;
    public JButton TargetBasedOnNearestButton;

    public JLabel balanceLabel;
    public JLabel coinsLabel;
    public JLabel waveNumLabel;

    public JLabel infoLabel;
    public JButton waveStartButton;
    public JButton exitButton;


    public GameDataPanel() {

        setLayout(new GridLayout(3,3));
        TargetBasedOnWeakestButton = new JButton("TargetBasedOnWeakest");
        TargetBasedOnStrongestButton = new JButton("TargetBasedOnStrongest");
        TargetBasedOnNearestButton = new JButton("TargetBasedOnNearest");

        balanceLabel = new JLabel("balanceLabel");
        coinsLabel = new JLabel("coinsLabel");
        waveNumLabel = new JLabel("Wave: ");

        infoLabel = new JLabel("infoLabel");
        waveStartButton = new JButton("Next Wave");
        exitButton = new JButton("exitButton");


        add(TargetBasedOnWeakestButton);
        add(TargetBasedOnStrongestButton);
        add(TargetBasedOnNearestButton);
        add(balanceLabel);
        add(coinsLabel);
        add(waveNumLabel);
        add(infoLabel);
        add(waveStartButton);
        add(exitButton);


    }

    @Override
    public void reloadWaveDataView(int waveNum) {
        waveNumLabel.setText("Wave: " + waveNum + " now");
    }

    @Override
    public void reloadBalanceDataView(double balance) {
        balanceLabel.setText("Gold: " + balance);
    }


    @Override
    public void reloadCoinDataView(int coin) {
        coinsLabel.setText("Coin left: " + coin);
    }

    @Override
    public void reloadInfoDataView(String info) {
        infoLabel.setText(info);
    }
}
