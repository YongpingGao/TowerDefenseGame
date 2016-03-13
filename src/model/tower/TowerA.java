package model.tower;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerA extends Tower{

    int level;


    public TowerA(int level){
        if(level <= MAX_LEVEL) {
            highReslutionTowerImageName = TowerName.TowerAH;
            this.level = level;
            initTower();
            specification = "<html>" + this.towerName.getTowerName() + "<br> Level: " + level + "<br> Good at attack normal creature</html>";
        }
    }

    private void initTower(){
        switch(level){
            case 1:
                buyPrice = 20.0;
                sellPrice = 10.0;
                towerName = TowerName.TowerA1;
                range = 80;
                rateOfFire = 100;
                power = 10;
                break;
            case 2:
                buyPrice = 30.0;
                sellPrice = 15.0;
                towerName = TowerName.TowerA2;
                range = 90;
                rateOfFire = 200;
                power = 20;
                break;
            case 3:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerName = TowerName.TowerA3;
                range = 100;
                rateOfFire = 300;
                power = 30;
                break;
            default:
                towerName = TowerName.TowerNull;
                break;
        }
    }


}
