package controller;

import model.map.CellState;
import model.map.GameMap;
import model.map.GameMapCollection;
import model.map.mapvalidation.MapValidationManager;
import protocol.DrawingMapDelegate;
import view.map.GameMapDrawing;
import view.mapeditorview.MapEditorView;
import utility.Helper;
import view.mapeditorview.PopupMenuView;
import view.mapeditorview.TopView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by yongpinggao on 3/12/16.
 */
public class MapEditorController {

    MapEditorView mapEditorView;
    GameMap gameMap;
    DrawingMapDelegate delegate;

    public MapEditorController(GameMap gameMap){

        this.gameMap = gameMap;

        mapEditorView = new MapEditorView();
        delegate = mapEditorView.mapView.mapPanel;
        delegate.refreshMap(gameMap);
        mapEditorView.topView.widthList.setSelectedIndex(Helper.getIndexFrom(TopView.widthStrings, gameMap.getmCols()));
        mapEditorView.topView.widthList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JComboBox){
                    JComboBox cb = (JComboBox)(e.getSource());
                    String string = (String)cb.getSelectedItem();
                    gameMap.setmCols(Integer.parseInt(string));
                    clearGameMap();
                    delegate.refreshMap(gameMap);
                }
            }
        });
        mapEditorView.topView.heightList.setSelectedIndex(Helper.getIndexFrom(TopView.heightStrings, gameMap.getmRows()));
        mapEditorView.topView.heightList.setActionCommand("height");
        mapEditorView.topView.heightList.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JComboBox){
                    JComboBox cb = (JComboBox)(e.getSource());
                    String string = (String)cb.getSelectedItem();
                    gameMap.setmRows(Integer.parseInt(string));
                    clearGameMap();
                    delegate.refreshMap(gameMap);
                }
            }
        });

        mapEditorView.mapView.mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                ArrayList<CellState> cellList = gameMap.getCells();
                int index = GameMapDrawing.coordinateToIndexConverter(x, y, gameMap.getmCols());

                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Left Click to set maps path
                    if(cellList.get(index) == CellState.Grass){
                        cellList.set(index, CellState.Path);
                    } else if(cellList.get(index) == CellState.Path){
                        cellList.set(index, CellState.Grass);
                    } else if(cellList.get(index) == CellState.Entrance || cellList.get(index) == CellState.Exit) {
                        cellList.set(index, CellState.Path);
                    }
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (cellList.get(index) == CellState.Path) {
                        PopupMenuView popup = new PopupMenuView();
                        popup.show(e.getComponent(), x, y);
                        popup.menuItem1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                cellList.set(index, CellState.Entrance);
                                delegate.refreshMap(gameMap);
                            }
                        });
                        popup.menuItem2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                cellList.set(index, CellState.Exit);
                                delegate.refreshMap(gameMap);
                            }
                        });
                    }
                }
                delegate.refreshMap(gameMap);
            }
        });


        mapEditorView.topView.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapValidationManager manager = new MapValidationManager(gameMap);
                if (manager.checkValidate()) {
                     saveDataToFile();
                } else {
                    JOptionPane.showMessageDialog(mapEditorView, manager.getErrorMessage(), "Illegal Map", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mapEditorView.topView.discardButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(
                        mapEditorView,
                        "Are you Sure, all unsaved changes will be discarded!!",
                        "Warning",
                        JOptionPane.YES_NO_OPTION);
                if(n == 0){ // User select "yes"
                    clearGameMap();
                    mapEditorView.setVisible(false);
                    new MainMenuController().mainMenuView.setVisible(true);
                } else {} // User select "no"
            }
        });
    }

    public void clearGameMap(){
        for(int i = 0; i < gameMap.getCells().size(); i++){
            gameMap.getCells().set(i, CellState.Grass);
        }
        delegate.refreshMap(gameMap);
    }

    public void saveDataToFile() {
        GameMapCollection mapCollection = GameMapCollection.loadMapsFromFile();
        boolean isReadyToCreate = true;
        if(!gameMap.getImageName().equals("")){// old map
            JOptionPane.showMessageDialog(mapEditorView, "Saved Successful!");

            for(int i = 0; i < mapCollection.getMaps().size(); i++){
                if(gameMap.getImageName().equals(mapCollection.getMaps().get(i).getImageName())){
                    mapCollection.getMaps().set(i, gameMap);
                }
            }
            GameMapCollection.saveMapsToFile(mapCollection);
            clearGameMap();
            mapEditorView.setVisible(false);
            new MapChooseController().mapChooseView.setVisible(true);

        } else {//brand new map
            String mapName = (String) JOptionPane.showInputDialog(mapEditorView,
                    "Type in the maps name:",
                    "Save map to file",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "map1");
            if (mapName != null) { // if user choose cancel, mapName -> null
                if (!mapName.equals("")) { // if the name is empty then it's invalidate

                    if (mapCollection != null) { // if the file already exits, check the filename and volume
                        int size = mapCollection.getMaps().size();
                        for (int i = 0; i < size; i++) {
                            if (mapCollection.getMaps().get(i).getImageName().equals(mapName)) {
                                String mapRename;// if they have the same name, please rename
                                do {
                                    mapRename = (String) JOptionPane.showInputDialog(mapEditorView,
                                            "Already taken, please rename:",
                                            "Save map to file",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            null,
                                            "map1");
                                } while (mapName.equals(mapRename));
                                if (mapRename != null) mapName = mapRename;
                                else isReadyToCreate = false;
                            }
                        }
                    } else mapCollection = new GameMapCollection();
                }
            }

            if (isReadyToCreate) {
                gameMap.setImageName(mapName);
                mapCollection.addMap(gameMap);
                GameMapCollection.saveMapsToFile(mapCollection);
                clearGameMap();
                mapEditorView.setVisible(false);
                new MainMenuController().mainMenuView.setVisible(true);
            } else JOptionPane.showMessageDialog(mapEditorView, "File name invalidate");

        }



    }




}
