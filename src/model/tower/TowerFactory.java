package model.tower;

import view.tower.TowerType;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerFactory {

    private static TowerFactory instance = new TowerFactory();

    public static TowerFactory sharedInstance() {
        return instance;
    }

    private TowerFactory() {}

    public Tower getTower(TowerType name){
        switch (name) {
            case BurningTower1:
                return new BurningTower(1);
            case BurningTower2:
                return new BurningTower(2);
            case BurningTower3:
                return new BurningTower(3);
            case IceTower1:
                return new IceTower(1);
            case IceTower2:
                return new IceTower(2);
            case IceTower3:
                return new IceTower(3);
            case SplashTower1:
                return new SplashTower(1);
            case SplashTower2:
                return new SplashTower(2);
            case SplashTower3:
                return new SplashTower(3);
            default:
                return null;
        }
    }


}
