package model.critter;

/**
 * Created by yongpinggao on 3/16/16.
 */
public class CritterD extends Critter {

    public CritterD(){
        initCritter();
    }

    private void initCritter(){
        critterName = CritterName.CritterD;
        initialMoveSpeed = 5;
        maxHealth = 400;
        worth = 50;
        currentHealth = maxHealth;
        currentMoveSpeed = initialMoveSpeed;
    }
}
