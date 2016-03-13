package model.map;


/**
 * Created by yongpinggao on 1/28/16.
 */
public enum CellState {
    Grass("grass"),
    Path("path"),
    Entrance("entrance"),
    Exit("exit"),
    ToPlaceTower("toplacetower"),
    Tower("tower"),
    Chosen("chosen");


    private String cellStateName;
    CellState(String name) {
        cellStateName = name;
    }

    public String getCellStateName() {
        return cellStateName;
    }



}