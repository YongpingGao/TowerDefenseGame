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
        initialMoveSpeed = 5;
        maxHealth = 100;
        worth = 20;
        currentHealth = maxHealth;
        currentMoveSpeed = initialMoveSpeed;
    }
}
