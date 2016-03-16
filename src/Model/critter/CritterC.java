package model.critter;

/**
 * Created by yongpinggao on 3/16/16.
 */
public class CritterC extends  Critter {

    public CritterC(){
        initCritter();
    }

    private void initCritter(){
        critterName = CritterName.CritterC;
        initialMoveSpeed = 10;
        maxHealth = 20;
        worth = 40;
        currentHealth = maxHealth;
        currentMoveSpeed = initialMoveSpeed;
    }
}
