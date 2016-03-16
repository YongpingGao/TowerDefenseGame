package model.wave;

import model.critter.*;


/**
 * Created by yongpinggao on 3/13/16.
 */
public class Wave {
    private int index;
    private Wave(Wave.Builder builder) {
        index = 0;
    }
    public static class Builder {
        private CritterA ca = new CritterA();
        public Builder() {}

        public Wave.Builder critterA(int num) {
            for(int i = 0; i < num; ++i) {
                CritterA critterA = new CritterA();
                CritterCollection.addCritter(critterA);
            }
            return this;
        }

        public Wave.Builder critterB(int num) {
            for(int i = 0; i < num; ++i) {
                CritterB critterB = new CritterB();
                CritterCollection.addCritter(critterB);
            }
            return this;
        }

        public Wave.Builder critterC(int num) {
            for(int i = 0; i < num; ++i) {
                CritterC critterC = new CritterC();
                CritterCollection.addCritter(critterC);
            }
            return this;
        }

        public Wave.Builder critterD(int num) {
            for(int i = 0; i < num; ++i) {
                CritterD critterD = new CritterD();
                CritterCollection.addCritter(critterD);
            }
            return this;
        }
        public Wave build() {
            return new Wave(this);
        }
    }
}
