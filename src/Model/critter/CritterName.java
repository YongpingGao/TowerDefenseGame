package model.critter;

/**
 * Created by yongpinggao on 3/13/16.
 */
public enum CritterName {

    CritterA("critterA"),
    CritterB("critterB"),
    CritterC("critterC"),
    CritterD("critterD");

    private String critterName;

    CritterName(String critterName) {
        this.critterName = critterName;
    }

    public String getCritterName() {
        return critterName;
    }

}
