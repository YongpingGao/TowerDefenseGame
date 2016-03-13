package controller;

import javax.swing.*;

/**
 * Created by yongpinggao on 1/24/16.
 */
public class TowerDefenseGameDriver {

    public static void main(String[] args){


        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                new MainMenuController().mainMenuView.setVisible(true);
            }
        });
    }
}
