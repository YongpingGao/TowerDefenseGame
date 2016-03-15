package model.critter;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class CritterA extends Critter {

    public CritterA(){
        initCritter();
    }

    private void initCritter(){
        critterName = CritterName.CritterA;
        moveSpeed = 5;
        maxHealth = 30;
        worth = 20;
        currentHealth = maxHealth;
    }
}
