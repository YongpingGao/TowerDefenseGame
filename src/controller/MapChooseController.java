package controller;

import model.map.GameMapCollection;
import view.mapchooseview.MapChooseView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by yongpinggao on 3/12/16.
 */
public class MapChooseController {

    MapChooseView mapChooseView;
    GameMapCollection mapCollection;
    DefaultListModel listModel;


    public MapChooseController(){
        listModel = new DefaultListModel();
        mapCollection = GameMapCollection.loadMapsFromFile();
        for(int i = 0; i < mapCollection.getMaps().size(); i++){
            listModel.addElement(mapCollection.getMaps().get(i).getImageName());
        }


        mapChooseView = new MapChooseView(listModel);
        mapChooseView.startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapChooseView.setVisible(false);
                new MainGameController(mapCollection.getMaps().get(mapChooseView.list.getSelectedIndex())).mainGameView.setVisible(true);
            }
        });

        mapChooseView.editMapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mapChooseView.setVisible(false);
                new MapEditorController(mapCollection.getMaps().get(mapChooseView.list.getSelectedIndex())).mapEditorView.setVisible(true);
            }
        });


    }
}
