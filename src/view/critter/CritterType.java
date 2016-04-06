package view.critter;

/**
 * Created by yongpinggao on 3/19/16.
 */
public enum CritterType {
    CritterA("critterA"),
    CritterB("critterB"),
    CritterC("critterC"),
    CritterD("critterD");

    private String critterImageName;

    CritterType(String critterName) {
        this.critterImageName = critterName;
    }

    public String getCritterImageName() {
        return critterImageName;
    }
}
