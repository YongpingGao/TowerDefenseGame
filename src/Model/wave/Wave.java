package model.wave;

import model.critter.*;


/**
 * Created by yongpinggao on 3/13/16.
 */
public class Wave {
    private int index;
    private final CritterA critterA;
    private Wave(Wave.Builder builder) {
        critterA = builder.ca;
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
        public Wave build() {
            return new Wave(this);
        }
    }
}
