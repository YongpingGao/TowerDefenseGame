package controller;

import model.critter.CritterMovingBehavior;
import model.gamelog.Log;
import model.gamelog.LogType;
import model.gamelog.LoggerCollection;
import protocol.DrawingMapDelegate;
import view.map.Drawing;
import model.tower.TowerFactory;
import protocol.DrawingDataPanelDelegate;
import protocol.DrawingMapInGameDelegate;
import protocol.DrawingPanelDelegate;
import model.critter.Critter;
import model.critter.CritterCollection;
import model.map.CellState;
import model.map.GameMap;
import model.tower.Tower;
import model.tower.TowerCollection;
import view.tower.TowerType;
import view.map.GameMapDrawing;
import model.wave.WaveFactory;
import model.bankaccount.*;
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
    CritterCollection critterCollection = new CritterCollection();
    Tower currentTower;
    int currentCritterIndex = 0;
    int currentIndex = 0;
    int currentWaveNum = 0;
    BankAccount account;

    Timer critterGeneratorTimer;

    DrawingMapInGameDelegate drawingMapInGameDelegate;
    DrawingMapDelegate drawingMapDelegate;
    DrawingPanelDelegate drawingSpecificationPanelDelegate;
    DrawingPanelDelegate drawingSellUpgradePanelDelegate;
    DrawingDataPanelDelegate drawingDataPanelDelegate;

    private final int REFRESH_RATE = 100;
    private final int CRITTER_GENERATE_TIME = 1000;

    private int coins = 10;
    private boolean preWavePhase = true;


    public MainGameController(GameMap gameMap){
        mainGameView = new MainGameView();
        this.gameMap = gameMap;
        drawingMapInGameDelegate = mainGameView.mapView.mapPanel;
        drawingMapDelegate = mainGameView.mapView.mapPanel;
        drawingSpecificationPanelDelegate = mainGameView.endView.towerSpecificationPanel;
        drawingSellUpgradePanelDelegate = mainGameView.endView.towerUpgradeSellPanel;
        drawingDataPanelDelegate = mainGameView.topView.gameDataPanel;
        drawingMapDelegate.refreshMap(gameMap);
        drawingDataPanelDelegate.reloadCoinDataView(coins);

        initBankAccount();
        initPaintingTimers();
        initMapArea();
        initWaveTimers();
        initTowerButtons();

        initSellUpgradeButtons();
        initFunctionalButtonsInTopPanel();


    }

    private void initBankAccount() {
        account = new BankAccount();
        account.setBalance(BankAccount.INITIAL_BALANCE);
        drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
    }


    private void startNextWave() {
        if(!preWavePhase) {
            if(currentWaveNum >= WaveFactory.MAX_WAVE_NUM) {
                gameShouldFinishedWithUserWin(true);
            } else {
                initCrittersForWave(++currentWaveNum);
                drawingDataPanelDelegate.reloadWaveDataView(currentWaveNum);
            }
        }
    }

    private void initFunctionalButtonsInTopPanel() {
        // waveStartButton
        mainGameView.topView.gameDataPanel.waveStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                preWavePhase = false;
                startNextWave();
                mainGameView.topView.gameDataPanel.waveStartButton.setEnabled(false);
            }
        });

        mainGameView.topView.gameDataPanel.showLogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(LoggerCollection.getInstance().showAllLog());
            }
        });

    }

    private void initCrittersForWave(int waveNum) {
        for(Tower t: towerCollection.getTowers().values()) {
            t.getTowerShootingBehavior().getCrittersInRange().clear();
        }
        currentCritterIndex = 0;
        critterCollection = WaveFactory.sharedInstance().getWave(waveNum).getCritterCollection();
    }

    private void initSellUpgradeButtons() {
        mainGameView.endView.towerUpgradeSellPanel.upgradeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentTower != null){
                    int level = currentTower.getLevel();
                    if(level < Tower.MAX_LEVEL){
                        double oldPrice = currentTower.getBuyPrice();
                        currentTower.setLevel(++level);
                        double newPrice = currentTower.getBuyPrice();
                        if(newPrice - oldPrice > account.getBalance()) {
                            drawingDataPanelDelegate.reloadInfoDataView("Need more gold");
                            currentTower.setLevel(--level);
                        } else {
                            account.withDraw(newPrice - oldPrice);
                            drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
                            LoggerCollection.getInstance().addLog(new Log(LogType.Tower, currentIndex, "Player update a tower to: " + currentTower.getTowerType() + " at position " + currentIndex));
                        }
                        refreshGamePanelsView();
                    } else { // warning!
                        drawingDataPanelDelegate.reloadInfoDataView("Max Level of tower is " + Tower.MAX_LEVEL);
                    }
                }
            }
        });
        mainGameView.endView.towerUpgradeSellPanel.sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentTower != null) {
                    account.deposit(currentTower.getSellPrice());
                    drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
                    currentTower = null;
                    towerCollection.removeTowerAtIndex(currentIndex);
                    gameMap.getCells().set(currentIndex, CellState.Grass);
                    currentIndex = -1;
                    refreshGamePanelsView();
                }
            }
        });
    }

    private void refreshGamePanelsView(){
        drawingMapInGameDelegate.refreshMap(gameMap, towerCollection);
        drawingSpecificationPanelDelegate.reloadPanelBasedOnTower(currentTower);
        drawingSellUpgradePanelDelegate.reloadPanelBasedOnTower(currentTower);
        drawingSellUpgradePanelDelegate.reloadLogPanelBasedOnIndexOfTower(currentIndex);
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
                            account.withDraw(currentTower.getBuyPrice());
                            drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
                            cellList.set(index, CellState.Tower);
                            currentTower.setPosition(Drawing.indexToCoordinateConverter(index, gameMap.getmCols()));
                            towerCollection.addTowerAtIndex(index, currentTower);
                            gameMap.setToGrassState();
                            refreshGamePanelsView();
                            currentTower = null;
                        }
                        // 2. if it is "Tower" state:  Tower state -> Chosen state
                        else if (cellList.get(index) == CellState.Tower) {
                            gameMap.setToGrassState();
                            gameMap.toggleChosenState(index);
                            currentTower = towerCollection.getTowers().get(index);
                            currentIndex = index;

                            refreshGamePanelsView();
                        }
                        // 3. if it is "Chosen" state: Chosen state -> Tower State
                        else if (cellList.get(index) == CellState.Chosen){
                            cellList.set(index, CellState.Tower);
                            currentTower = null;
                            currentIndex = -1;
                            refreshGamePanelsView();
                        }
                        // 4. Other Cells: Chosen state -> Tower state.
                        // ToPlaceTower state -> Grass state
                        else {
                            // if the user press the wrong cells, aka path, etc.
                            // set state back to grass
                            gameMap.clearState();
                            currentTower = null;
                            currentIndex = -1;
                            refreshGamePanelsView();
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
                currentTower = TowerFactory.sharedInstance().getTower(TowerType.BurningTower1);
                if(currentTower.getBuyPrice() <= account.getBalance()){
                    gameMap.setToPlaceTowerState();
                    refreshGamePanelsView();
                } else {
                    gameMap.setToGrassState();
                    currentTower = null;
                    drawingDataPanelDelegate.reloadInfoDataView("Need more gold");
                }
            }
        });
        mainGameView.topView.towerSelectionPanel.towerBButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTower = TowerFactory.sharedInstance().getTower(TowerType.IceTower1);
                if(currentTower.getBuyPrice() <= account.getBalance()){
                    gameMap.setToPlaceTowerState();
                    refreshGamePanelsView();
                } else {
                    gameMap.setToGrassState();
                    currentTower = null;
                    drawingDataPanelDelegate.reloadInfoDataView("Need more gold");
                }
            }
        });
        mainGameView.topView.towerSelectionPanel.towerCButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTower = TowerFactory.sharedInstance().getTower(TowerType.SplashTower1);
                if(currentTower.getBuyPrice() <= account.getBalance()){
                    gameMap.setToPlaceTowerState();
                    refreshGamePanelsView();
                } else {
                    gameMap.setToGrassState();
                    currentTower = null;
                    drawingDataPanelDelegate.reloadInfoDataView("Need more gold");
                }
            }
        });
    }

    private void initPaintingTimers(){
        Timer paintingTimer = new Timer(REFRESH_RATE, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingMapInGameDelegate.refreshCrittersInMap(critterCollection);
                detectingCrittersInRange();
                detectingCrittersStoleCoins();
                detectingNextWaveShouldStart();
            }
        });
        paintingTimer.start();
    }

    private void detectingNextWaveShouldStart() {
        int count = 0;
        for(Critter c : critterCollection.getCritters()) {
            if (c.isKilled() || c.isDonated()) {
                count ++;
                continue;
            } else break;
        }
        if(count == critterCollection.getCritters().size()) startNextWave();
    }

    private void detectingCrittersStoleCoins() {
        for(Critter c : critterCollection.getCritters()) {
            if(c.getMovingBehavior() != null) {
                if(c.getMovingBehavior().isArrivedAtExit() && !c.isDonated()){
                    coins --;
                    drawingDataPanelDelegate.reloadCoinDataView(coins);
                    c.setDonated(true);
                    if(coins == 0) gameShouldFinishedWithUserWin(false);
                }
            }
        }
    }

    private void gameShouldFinishedWithUserWin(boolean win) {
        if(win) {
            JOptionPane.showMessageDialog(mainGameView, "Good Job! You win!");
        } else {
            JOptionPane.showMessageDialog(mainGameView, "Sorry, You lose the game!");
        }
        System.exit(0);
    }

    private void initWaveTimers(){

        critterGeneratorTimer = new Timer(CRITTER_GENERATE_TIME, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentCritterIndex < critterCollection.getCritters().size()) {
                    Critter c = critterCollection.getCritters().get(currentCritterIndex);
                    c.setMovingBehavior(new CritterMovingBehavior(gameMap, c.getMovingSpeed()));
                    c.setVisible(true);
                    c.getMovingBehavior().move();
                    currentCritterIndex++;
                }
            }
        });
        critterGeneratorTimer.start();
    }

    private void detectingCrittersInRange(){
        for(Tower t: towerCollection.getTowers().values()){
            for(Critter c : critterCollection.getCritters()) {
                if(c.isVisible() && !c.isKilled()){
                    if(c.getMovingBehavior().getCurrentPosition().distanceTo(t.getPosition()) <= t.getRange() / 2 ) {
                        t.getTowerShootingBehavior().getCrittersInRange().add(c);
                    } else {
                        t.getTowerShootingBehavior().getCrittersInRange().remove(c);
                    }
                    if(c.getCurrentHealth() <= 0) {
                        c.setKilled(true);
                        account.deposit(c.getWorth());
                        drawingDataPanelDelegate.reloadBalanceDataView(account.getBalance());
                        c.setVisible(false);
                    }
                }
            }

            if(!t.getTowerShootingBehavior().getCrittersInRange().isEmpty()) {
                t.getTowerShootingBehavior().setShooting(true);
                if(t.getTowerShootingBehavior().isTimeToShoot())
                t.getTowerShootingBehavior().shoot();
            } else t.getTowerShootingBehavior().setShooting(false);
        }
    }
}
