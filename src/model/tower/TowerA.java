package model.tower;

import model.critter.Critter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class TowerA extends Tower implements ShootingBehavior{

    int level;


    public TowerA(int level){
        if(level <= MAX_LEVEL) {
            highResolutionTowerImageName = TowerName.TowerAH;
            this.level = level;

            shootingEffect = ShootingEffect.getStoke(ShootingEffect.BlackDot);
            initTower();
        }
    }

    private void initTower(){
        specification = "<html>" + "TowerA" + "<br> Level: " + level + "<br> Good at attack normal creature</html>";
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

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
        initTower();
    }

    @Override
    public void shoot() {
        critterUnderAttack = shootingStrategy.targetOnCritters(crittersInRange);
        if(isShooting) { //if critter is get attacked(a line is drawn)
            int health = critterUnderAttack.getCurrentHealth();
            health -= power;
            if(health > 0)
                critterUnderAttack.setCurrentHealth(health);
            else {
                critterUnderAttack.setAlive(false);
                crittersInRange.remove(critterUnderAttack);
            }
        }
    }
}
