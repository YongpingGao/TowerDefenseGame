package controller;

import model.map.GameMap;
import model.map.GameMapCollection;
import view.mainmenuview.MainMenuView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by yongpinggao on 1/24/16.
 */
public class MainMenuController {

    MainMenuView mainMenuView;

    public MainMenuController() {
        mainMenuView = new MainMenuView();
        mainMenuView.editorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuView.setVisible(false);
                new MapEditorController(new GameMap()).mapEditorView.setVisible(true);
            }
        });
        mainMenuView.startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainMenuView.setVisible(false);
                if(GameMapCollection.loadMapsFromFile() == null) {
                    JOptionPane.showMessageDialog(mainMenuView, "No Saved Maps, please go to the Map editor", "Error", JOptionPane.YES_OPTION);
                    mainMenuView.setVisible(false);
                    new MapEditorController(new GameMap()).mapEditorView.setVisible(true);
                } else {
                    new MapChooseController().mapChooseView.setVisible(true);
                }

            }
        });

    }

}
