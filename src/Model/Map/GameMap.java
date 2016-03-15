package model.map;

import java.util.ArrayList;

/**
 * Created by yongpinggao on 1/26/16.
 */
public class GameMap {
    // cell image size in pixels


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


    public void setToPlaceTowerState(){
        for(int i = 0; i < cells.size(); i++){
            if(cells.get(i) == CellState.Grass){
                cells.set(i, CellState.ToPlaceTower);
            } else if(cells.get(i) == CellState.Chosen){
                cells.set(i, CellState.Tower);
            }
        }
    }

    public void setToGrassState(){
        for(int i = 0; i < cells.size(); i++){
            if(cells.get(i) == CellState.ToPlaceTower){
                cells.set(i, CellState.Grass);
            }
        }
    }

    public void toggleChosenState(int index){

        for(int i = 0; i < cells.size(); i++){
            if(cells.get(i) == CellState.Chosen){
                cells.set(i, CellState.Tower);
            }
        }
        cells.set(index, CellState.Chosen);
    }

    public void clearState(){
        // if the user press the wrong cells, aka path, etc.
        // set state back to grass
        for(int i = 0; i < cells.size(); i++){
            if (cells.get(i) == CellState.Chosen){
                cells.set(i, CellState.Tower);
            } else if (cells.get(i) == CellState.ToPlaceTower){
                cells.set(i, CellState.Grass);
            }

        }
    }

    public int findExitIndex(){
        for(int i = 0; i < cells.size() ; i++){
            if(cells.get(i) == CellState.Exit){ // Entrance -> indexEntrance
                return i;
            }
        }
        return -1;
    }

    public int findEntranceIndex(){
        for(int i = 0; i < cells.size() ; i++){
            if(cells.get(i) == CellState.Entrance){ // Entrance -> indexEntrance
                return i;
            }
        }
        return -1;
    }

    public ArrayList<Integer> findPathList(){
        ArrayList<Integer> pathList = new ArrayList<>();
        for(int i = 0; i < cells.size() ; i++){
            if(cells.get(i) == CellState.Path){ // PATH -> pathList
                pathList.add(i);
            }
        }
        return pathList;

    }




}





