package model.wave;

import model.critter.*;
import view.critter.CritterType;


/**
 * Created by yongpinggao on 3/13/16.
 */
public class WaveBuilder {

    private CritterCollection critterCollection = new CritterCollection();

    public WaveBuilder() {
        if(critterCollection != null) critterCollection.clearAllCritters();
    }
    public WaveBuilder critterA(int num) {

        for (int i = 0; i < num; ++i) {
            Critter critter = new Critter(CritterType.CritterA);
            critterCollection.addCritter(critter);
        }
        return this;
    }

    public WaveBuilder critterB(int num) {
        for (int i = 0; i < num; ++i) {
            Critter critter = new Critter(CritterType.CritterB);
            critterCollection.addCritter(critter);
        }
        return this;
    }

    public WaveBuilder critterC(int num) {
        for (int i = 0; i < num; ++i) {
            Critter critter = new Critter(CritterType.CritterC);
            critterCollection.addCritter(critter);
        }
        return this;
    }

    public WaveBuilder critterD(int num) {
        for (int i = 0; i < num; ++i) {
            Critter critter = new Critter(CritterType.CritterD);
            critterCollection.addCritter(critter);
        }
        return this;
    }

    public CritterCollection getCritterCollection() {
        return critterCollection;
    }
}


