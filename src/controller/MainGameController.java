package controller;

import Protocol.DrawingPanelDelegate;
import Protocol.DrawingTowersDelegate;
import model.map.CellState;
import model.map.GameMap;
import Protocol.DrawingMapDelegate;
import model.tower.Tower;
import model.tower.TowerCollection;
import model.tower.TowerFactory;
import model.tower.TowerName;
import view.Drawing;
import view.maingameview.MainGameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class MainGameController {

    MainGameView mainGameView;

    GameMap gameMap = new GameMap();
    TowerCollection towerCollection = new TowerCollection();
    Tower currentTower = new Tower();




    DrawingMapDelegate drawingMapDelegate;
    DrawingTowersDelegate drawingTowersDelegate;
    DrawingPanelDelegate drawingSpecificationPanelDelegate;
    DrawingPanelDelegate drawingSellUpgradePanelDelegate;


    public MainGameController(GameMap gameMap){
        mainGameView = new MainGameView();
        this.gameMap = gameMap;

        drawingMapDelegate = mainGameView.mapView.mapPanel;
        drawingTowersDelegate = mainGameView.mapView.mapPanel;
        drawingSpecificationPanelDelegate = mainGameView.endView.towerSpecificationPanel;
        drawingSellUpgradePanelDelegate = mainGameView.endView.towerUpgradeSellPanel;

        drawingMapDelegate.refreshMap(gameMap);


        initTowerButtons();

        initMapArea();
    }

    private void initMapArea() {
        mainGameView.mapView.mapPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                {
                    super.mousePressed(e);

                    int x = e.getX();
                    int y = e.getY();

                    int index = Drawing.coordinateToIndexConverter(x, y, gameMap.getmCols());
                    ArrayList<CellState> cellList = gameMap.getCells();

                    if (e.getButton() == MouseEvent.BUTTON1) { // User left click map cells

                        // 1. if it is "toPlaceTower" state:  toPlaceTower -> Tower state
                        if(cellList.get(index) == CellState.ToPlaceTower){
                            cellList.set(index, CellState.Tower);
                            Tower tower = TowerFactory.sharedInstance().getTower(currentTower.getTowerName());
                            towerCollection.addTowerAtIndex(index, tower);
                            gameMap.setToGrassState();

                            drawingMapDelegate.refreshMap(gameMap);
                            drawingTowersDelegate.refreshAllTowers(towerCollection);
                            System.out.println("1");
                        }
                        // 2. if it is "Tower" state:  Tower state -> Chosen state
//                        else if (cellList.get(index) == CellState.TOWER) {
//                            for(int i = 0; i < cellList.size(); i++) {
//                                if (cellList.get(i) == CellState.TOPLACETOWER) {
//                                    cellList.set(i, CellState.GRASS);
//                                }
//                            }
//                            if(isChosen){
//                                for(int i = 0; i < cellList.size(); i++){
//                                    if (cellList.get(i) == CellState.CHOSEN){
//                                        cellList.set(i, CellState.TOWER);
//                                    }
//                                }
//                                isChosen = false;
//                            }
//                            isChosen = true;
//                            cellList.set(index, CellState.CHOSEN);
//
//                            currentChosenID = towerMap.get(index).getTid();
//                            listener.updateInfo(currentChosenID);
//                            repaint();
//                            System.out.println("2");
//                        }
//                        // 3. if it is "Chosen" state: Chosen state -> Tower State
//                        else if (cellList.get(index) == CellState.CHOSEN){
//                            cellList.set(index, CellState.TOWER);
//                            currentChosenID = TowerID.TOWERNULL;
//                            System.out.println("3");
//                        }
//
//                        // 4. Other Cells: Chosen state -> Tower state.
//                        // ToPlaceTower state -> Grass state
//                        else {
//                            // if the user press the wrong cells, aka path, etc.
//                            // set state back to grass
//                            for(int i = 0; i < cellList.size(); i++){
//                                if (cellList.get(i) == CellState.CHOSEN){
//                                    cellList.set(i, CellState.TOWER);
//
//                                } else if (cellList.get(i) == CellState.TOPLACETOWER){
//                                    cellList.set(i, CellState.GRASS);
//                                }
//                            }
//                            currentChosenID = TowerID.TOWERNULL;
//                            listener.updateInfo(currentChosenID);
//                            System.out.println("4");
//                            repaint();
//                        }
//                        currentTowerID = TowerID.TOWERNULL;
                    }

                }
            }
        });

    }

    private void initTowerButtons() {
        mainGameView.topView.towerSelectionPanel.towerAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameMap.setToPlaceTowerState();
                currentTower = TowerFactory.sharedInstance().getTower(TowerName.TowerA1);

                drawingMapDelegate.refreshMap(gameMap);
                drawingSpecificationPanelDelegate.reloadPanelBasedOnTower(currentTower);
                drawingSellUpgradePanelDelegate.reloadPanelBasedOnTower(currentTower);
            }
        });
        mainGameView.topView.towerSelectionPanel.towerBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gameMap.setToPlaceTowerState();
                currentTower = TowerFactory.sharedInstance().getTower(TowerName.TowerB1);

                drawingMapDelegate.refreshMap(gameMap);
                drawingSpecificationPanelDelegate.reloadPanelBasedOnTower(currentTower);
                drawingSellUpgradePanelDelegate.reloadPanelBasedOnTower(currentTower);
            }
        });
        mainGameView.topView.towerSelectionPanel.towerCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gameMap.setToPlaceTowerState();
                currentTower = TowerFactory.sharedInstance().getTower(TowerName.TowerC1);

                drawingMapDelegate.refreshMap(gameMap);
                drawingSpecificationPanelDelegate.reloadPanelBasedOnTower(currentTower);
                drawingSellUpgradePanelDelegate.reloadPanelBasedOnTower(currentTower);
            }
        });
    }






}
