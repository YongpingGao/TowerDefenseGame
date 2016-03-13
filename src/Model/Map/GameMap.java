package Model.Map;

import java.util.ArrayList;

/**
 * Created by yongpinggao on 1/26/16.
 */
public class GameMap {

    private ArrayList<CellState> cells;
    private int mCols;
    private int mRows;
    private String imageName;


    public GameMap(int mapRows, int mapCols, ArrayList<CellState> cells, String imageName){
        this.cells = cells;
        this.mCols = mapCols;
        this.mRows = mapRows;
        this.imageName = imageName;

    }

    public GameMap(){
        int mCols = 30;
        int mRows = 15;
        ArrayList<CellState> cells = new ArrayList<>();
        for(int i = 0; i < mCols * mRows; i++){
            cells.add(CellState.Grass);
        }
        String imageName = "";

        this.cells = cells;
        this.mCols = mCols;
        this.mRows = mRows;
        this.imageName = imageName;
    }


    public ArrayList<CellState> getCells() {
        return cells;
    }

    public void setCells(ArrayList<CellState> cells) {
        this.cells = cells;
    }

    public int getmCols() {
        return mCols;
    }

    public void setmCols(int mCols) {
        this.mCols = mCols;
    }

    public int getmRows() {
        return mRows;
    }

    public void setmRows(int mRows) {
        this.mRows = mRows;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}





