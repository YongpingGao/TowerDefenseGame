package model.tower;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerFactory {

    private static TowerFactory instance = new TowerFactory();

    public static TowerFactory sharedInstance() {
        return instance;
    }

    private TowerFactory() {}

    public Tower getTower(TowerName name){
        switch (name) {
            case TowerA1:
                return new NormalTower(1);
            case TowerA2:
                return new NormalTower(2);
            case TowerA3:
                return new NormalTower(3);
            case TowerB1:
                return new IceTower(1);
            case TowerB2:
                return new IceTower(2);
            case TowerB3:
                return new IceTower(3);
            case TowerC1:
                return new PoisonTower(1);
            case TowerC2:
                return new PoisonTower(2);
            case TowerC3:
                return new PoisonTower(3);
            default:
                return null;
        }
    }


}
