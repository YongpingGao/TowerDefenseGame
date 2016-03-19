package model.critter;

/**
 * Created by yongpinggao on 3/16/16.
 */
public class CritterB extends Critter {

    public CritterB(){
        initCritter();
    }

    private void initCritter(){
        critterName = CritterName.CritterB;
        initialMoveSpeed = 15;
        maxHealth = 60;
        worth = 30;
        currentHealth = maxHealth;
        currentMoveSpeed = initialMoveSpeed;
    }
}
