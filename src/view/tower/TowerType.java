package view.tower;

/**
 * Created by yongpinggao on 3/13/16.
 */
public enum TowerType {
    TowerNull(""),

    // Burning Tower
    BurningTower1("towerA_1"),
    BurningTower2("towerA_2"),
    BurningTower3("towerA_3"),

    // Ice Tower
    IceTower1("towerB_1"),
    IceTower2("towerB_2"),
    IceTower3("towerB_3"),

    // Splash Tower
    SplashTower1("towerC_1"),
    SplashTower2("towerC_2"),
    SplashTower3("towerC_3");


    private String towerImageName;

    TowerType(String towerImageName) {
        this.towerImageName = towerImageName;
    }

    public String getTowerImageName() {
        return towerImageName;
    }
}
