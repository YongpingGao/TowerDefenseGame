package controller;

import model.tower.shootingstrategy.*;
import protocol.DrawingDataPanelDelegate;
import protocol.DrawingMapInGameDelegate;
import protocol.DrawingPanelDelegate;
import model.critter.Critter;
import model.critter.CritterCollection;
import model.map.CellState;
import model.map.GameMap;
import model.tower.Tower;
import model.tower.TowerCollection;
import model.tower.TowerFactory;
import model.tower.TowerName;
import model.drawing.GameMapDrawing;
import model.wave.WaveFactory;
import view.maingameview.MainGameView;
import javax.swing.*;
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
    int currentIndex = -1;
    int currentWaveNum = 0;


    Timer critterGeneratorTimer;

    DrawingMapInGameDelegate drawingMapInGameDelegate;
    DrawingPanelDelegate drawingSpecificationPanelDelegate;
    DrawingPanelDelegate drawingSellUpgradePanelDelegate;
    DrawingDataPanelDelegate drawingDataPanelDelegate;

    private final int REFRESH_RATE = 100;
    private final int CRITTER_GENERATE_TIME = 1000;

    private int coins = 10;


    public MainGameController(GameMap gameMap){
        mainGameView = new MainGameView();
        this.gameMap = gameMap;
        drawingMapInGameDelegate = mainGameView.mapView.mapPanel;
        drawingSpecificationPanelDelegate = mainGameView.endView.towerSpecificationPanel;
        drawingSellUpgradePanelDelegate = mainGameView.endView.towerUpgradeSellPanel;
        drawingDataPanelDelegate = mainGameView.topView.gameDataPanel;
        drawingMapInGameDelegate.refreshMap(gameMap);
        drawingDataPanelDelegate.reloadCoinDataView(coins);

        initPaintingTimers();
        initWaveTimers();
        initTowerButtons();
        initMapArea();
        initSellUpgradeButtons();
        initFunctionalButtonsInTopPanel();
    }

    private void initFunctionalButtonsInTopPanel() {
        // waveStartButton
        mainGameView.topView.gameDataPanel.waveStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentWaveNum == WaveFactory.MAX_WAVE_NUM) {
                    currentWaveNum = 0;
                }
                initCrittersForWave(++currentWaveNum);
                drawingDataPanelDelegate.reloadWaveDataView(currentWaveNum);
                System.out.println(currentWaveNum);
            }
        });

        // strategy button 1, 2, 3:
        mainGameView.topView.gameDataPanel.TargetBasedOnWeakestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Tower t : towerCollection.getTowers().values()){
                    t.setShootingStrategy(new TargetBasedOnWeakest());
                }
            }
        });

        mainGameView.topView.gameDataPanel.TargetBasedOnStrongestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Tower t : towerCollection.getTowers().values()){
                    t.setShootingStrategy(new TargetBasedOnStrongest());
                }
            }
        });

        mainGameView.topView.gameDataPanel.TargetBasedOnNearestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(Tower t : towerCollection.getTowers().values()){
                    t.setShootingStrategy(new TargetBasedOnNearest());
                }
            }
        });

        mainGameView.topView.gameDataPanel.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearGame();
                mainGameView.setVisible(false);
                new MainMenuController().mainMenuView.setVisible(true);
            }
        });

    }

    private void initCrittersForWave(int waveNum) {
        CritterCollection.clearAllCritters();
        WaveFactory.sharedInstance().getWave(waveNum);
        CritterCollection.setGameMapForCritters(gameMap);
    }

    private void initSellUpgradeButtons() {
        mainGameView.endView.towerUpgradeSellPanel.upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentTower != null){
                    int level = currentTower.getLevel();
                    if(level < Tower.MAX_LEVEL){
                        currentTower.setLevel(++level);
                        refreshAllPanelsView();
                    } else { // warning!

                    }
                }
            }
        });
        mainGameView.endView.towerUpgradeSellPanel.sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentTower != null){
                    currentTower = null;
                    towerCollection.removeTowerAtIndex(currentIndex);
                    gameMap.getCells().set(currentIndex, CellState.Grass);
                    currentIndex = -1;
                    refreshAllPanelsView();
                }
            }
        });
    }

    private void refreshAllPanelsView(){
        drawingMapInGameDelegate.refreshMap(gameMap, towerCollection);
        drawingSpecificationPanelDelegate.reloadPanelBasedOnTower(currentTower);
        drawingSellUpgradePanelDelegate.reloadPanelBasedOnTower(currentTower);
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

                    int index = GameMapDrawing.coordinateToIndexConverter(x, y, gameMap.getmCols());
                    ArrayList<CellState> cellList = gameMap.getCells();

                    if (e.getButton() == MouseEvent.BUTTON1) { // User left click map cells

                        // 1. if it is "toPlaceTower" state:  toPlaceTower -> Tower state
                        if(cellList.get(index) == CellState.ToPlaceTower){
                            cellList.set(index, CellState.Tower);
                            Tower tower = TowerFactory.sharedInstance().getTower(currentTower.getTowerName());
                            tower.setPosition(GameMapDrawing.indexToCoordinateConverter(index, gameMap.getmCols()));
                            towerCollection.addTowerAtIndex(index, tower);
                            gameMap.setToGrassState();
                            refreshAllPanelsView();
                            System.out.println("1");
                        }
                        // 2. if it is "Tower" state:  Tower state -> Chosen state
                        else if (cellList.get(index) == CellState.Tower) {
                            gameMap.setToGrassState();
                            gameMap.toggleChosenState(index);
                            currentTower = towerCollection.getTowers().get(index);
                            currentIndex = index;
                            refreshAllPanelsView();
                            System.out.println("2");
                        }
                        // 3. if it is "Chosen" state: Chosen state -> Tower State
                        else if (cellList.get(index) == CellState.Chosen){
                            cellList.set(index, CellState.Tower);
                            currentTower = null;
                            currentIndex = -1;
                            refreshAllPanelsView();
                            System.out.println("3");
                        }
                        // 4. Other Cells: Chosen state -> Tower state.
                        // ToPlaceTower state -> Grass state
                        else {
                            // if the user press the wrong cells, aka path, etc.
                            // set state back to grass
                            gameMap.clearState();
                            currentTower = null;
                            currentIndex = -1;
                            refreshAllPanelsView();
                            System.out.println("4");
                        }
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

                refreshAllPanelsView();
            }
        });
        mainGameView.topView.towerSelectionPanel.towerBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gameMap.setToPlaceTowerState();
                currentTower = TowerFactory.sharedInstance().getTower(TowerName.TowerB1);

                refreshAllPanelsView();
            }
        });
        mainGameView.topView.towerSelectionPanel.towerCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gameMap.setToPlaceTowerState();
                currentTower = TowerFactory.sharedInstance().getTower(TowerName.TowerC1);

                refreshAllPanelsView();
            }
        });
    }

    private void initPaintingTimers(){
        Timer paintingTimer = new Timer(REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CritterCollection.crittersMoving();
                drawingMapInGameDelegate.refreshCrittersInMap();
                detectingCrittersInRange();
                drawingMapInGameDelegate.refreshShootingEffectInMap(towerCollection);
                detectingCrittersStoleCoins();

            }
        });
        paintingTimer.start();
    }

    private void detectingCrittersStoleCoins() {
        for(Critter c : CritterCollection.critters) {
            if(c.isSucceed()){
                coins --;
                drawingDataPanelDelegate.reloadCoinDataView(coins);
                if(coins == 0) gameOverHandler();
                c.setSucceed(false);
            }
        }
    }

    private void initWaveTimers(){
        critterGeneratorTimer = new Timer(CRITTER_GENERATE_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(CritterCollection.currentIndex < CritterCollection.critters.size())
                CritterCollection.critters.get(CritterCollection.currentIndex++).setAlive(true);
            }
        });
        critterGeneratorTimer.start();
    }

    private void detectingCrittersInRange(){
        for(Tower t: towerCollection.getTowers().values()){
            for(Critter c : CritterCollection.critters) {
                if(c.isAlive()){
                    if(c.getCurrentHealth() <= 0){
                        c.setAlive(false);
                    }
                    if(c.getBound().intersects(t.getBound())){
                        t.getCrittersInRange().add(c);
                    } else {
                        t.getCrittersInRange().remove(c);
                    }

                }
            }

        }
    }

    private void gameOverHandler(){
        clearGame();
        Object[] options = {"Back to main menu",  "Play again!"};
        int n = JOptionPane.showOptionDialog(mainGameView,
                "Sorry, Game over...",
                "Oops!",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options, //the titles of buttons
                options[0]); //default button title
        if(n == 0){
            mainGameView.setVisible(false);
            new MainMenuController().mainMenuView.setVisible(true);
        } else {
            mainGameView.setVisible(false);
            new MapChooseController().mapChooseView.setVisible(true);
        }
    }

    private void clearGame(){
        CritterCollection.clearAllCritters();
        critterGeneratorTimer.stop();
    }
    
    

}
