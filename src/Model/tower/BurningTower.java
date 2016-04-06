package model.tower;

import view.map.Position;
import model.tower.shootingstrategy.TargetBasedOnWeakest;
import view.tower.TowerShootingRangeView;
import view.tower.TowerShootingView;
import view.tower.TowerType;
import view.tower.TowerView;

/**
 * Created by yongpinggao on 3/13/16.
 */

public class BurningTower extends Tower {

    public BurningTower(int level) {
        if(level <= MAX_LEVEL) {
            this.level = level;
            initTower();
        }
    }

    private void initTower(){
        this.specification = "<html>" + "Burning Tower" + "<br> Level: " + level + "<br> Good at attack normal creature</html>";
        switch(level){
            case 1:
                buyPrice = 20.0;
                sellPrice = 10.0;
                towerType = TowerType.BurningTower1;
                range = 120;
                towerShootingBehavior = new BurningTowerShootingBehavior(5, 100, 5, new TargetBasedOnWeakest());
                towerView = new TowerView(towerType);
                break;
            case 2:
                buyPrice = 30.0;
                sellPrice = 15.0;
                towerType = TowerType.BurningTower2;
                range = 150;
                towerShootingBehavior = new BurningTowerShootingBehavior(10, 200, 10, new TargetBasedOnWeakest());
                towerView = new TowerView(towerType);
                break;
            case 3:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerType = TowerType.BurningTower3;
                range = 180;
                towerShootingBehavior = new BurningTowerShootingBehavior(15, 300, 20, new TargetBasedOnWeakest());
                towerView = new TowerView(towerType);
                break;
        }
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
        this.level = level;
        initTower();
        setPosition(position);
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        towerShootingRangeView = new TowerShootingRangeView(position, range);
        switch(level){
            case 1:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.RED, 3));
                break;
            case 2:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.RED, 4));
                break;
            case 3:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.RED, 5));
                break;
        }
        towerShootingBehavior.setTowerDidShotDelegate(towerShootingView);
    }

    @Override
    public String getHdImageName() {
        return "res/towerA_high.png";
    }
}
