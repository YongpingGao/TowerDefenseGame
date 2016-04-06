package model.tower;

import view.map.Position;
import view.tower.TowerShootingRangeView;
import view.tower.TowerShootingView;
import view.tower.TowerType;
import view.tower.TowerView;

import java.awt.*;

/**
 * Created by yongpinggao on 3/24/16.
 */
public class SplashTower extends Tower {

    public SplashTower(int level){
        if(level <= MAX_LEVEL) {
            this.level = level;
            initTower();
        }
    }

    private void initTower(){
        this.specification = "<html>" + "Splash Tower" + "<br> Level: " + level + "<br> Good at attack all the critters in the shooting range</html>";
        switch(level){
            case 1:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerType = TowerType.SplashTower1;
                range = 120;
                towerShootingBehavior = new SplashTowerShootingBehavior(10, 100);
                towerView = new TowerView(towerType);
                break;
            case 2:
                buyPrice = 50.0;
                sellPrice = 25.0;
                towerType = TowerType.SplashTower2;
                range = 150;
                towerShootingBehavior = new SplashTowerShootingBehavior(20, 200);
                towerView = new TowerView(towerType);
                break;
            case 3:
                buyPrice = 60.0;
                sellPrice = 30.0;
                towerType = TowerType.SplashTower3;
                range = 180;
                towerShootingBehavior = new SplashTowerShootingBehavior(30, 300);
                towerView = new TowerView(towerType);
                break;
        }
    }

    @Override
    public String getHdImageName() {
        return "res/towerC_high.png";
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
                towerShootingView = new TowerShootingView(position, range, new ShootingEffect(Color.BLUE, 3));
                break;
            case 2:
                towerShootingView = new TowerShootingView(position, range, new ShootingEffect(java.awt.Color.BLUE, 4));
                break;
            case 3:
                towerShootingView = new TowerShootingView(position, range, new ShootingEffect(java.awt.Color.BLUE, 5));
                break;
        }
        towerShootingBehavior.setTowerDidShotDelegate(towerShootingView);
    }
}
