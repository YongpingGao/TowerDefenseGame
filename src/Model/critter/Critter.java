package model.critter;

import model.imagecollection.CritterImageCollection;
import model.map.GameMap;
import model.drawing.GameMapDrawing;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class Critter {
    protected CritterName critterName;
    protected int currentHealth;
    protected int maxHealth;
    protected double worth;
    protected boolean isAlive;

    // current position
    protected int currentPosX;
    protected int currentPosY;

    private int nextIndex;
    private int cols;
    private ArrayList<Integer> pathList;
    private int entranceIndex;
    private int exitIndex;

    public void setGameMap(GameMap gameMap) {
        pathList = gameMap.findPathList();
        cols = gameMap.getmCols();
        entranceIndex = gameMap.findEntranceIndex();
        exitIndex = gameMap.findExitIndex();
        int[] currentPosition = GameMapDrawing.indexToCoordinateConverter(entranceIndex, cols);
        currentPosX = currentPosition[0];
        currentPosY = currentPosition[1];
        nextIndex = entranceIndex;
    }

    public Rectangle getBound(){
        Dimension dimension = CritterImageCollection.getCritterImageSizeOf(critterName);
        return new Rectangle(currentPosX, currentPosY, dimension.width, dimension.height);
    }

    public float getHealthBarLength() {
        return (float)(currentHealth) / maxHealth;
    }

    public CritterName getCritterName() {
        return critterName;
    }

    public void setCritterName(CritterName critterName) {
        this.critterName = critterName;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    protected int moveSpeed;

    // x, y -> current position
    private void moveRight(){
        currentPosX += moveSpeed;
    }

    private void moveDown(){
        currentPosY += moveSpeed;
    }

    private void moverLeft(){
        currentPosX -= moveSpeed;
    }

    private void moveUp(){
        currentPosY -= moveSpeed;
    }

    public int getCurrentPosX() {
        return currentPosX;
    }

    public void setCurrentPosX(int currentPosX) {
        this.currentPosX = currentPosX;
    }

    public int getCurrentPosY() {
        return currentPosY;
    }

    public void setCurrentPosY(int currentPosY) {
        this.currentPosY = currentPosY;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    private int getDestination(int index) {

        int iLeft = index - 1;
        int iRight = index + 1;
        int iDown = index + cols;
        int iUp = index - cols;
        // TODO when critter gone, it should stop
        int nextIndex;
        if (pathList.contains(iLeft)) {
            nextIndex = iLeft;
        } else if (pathList.contains(iRight)) {
            nextIndex = iRight;
        } else if (pathList.contains(iDown)) {
            nextIndex = iDown;
        } else if (pathList.contains(iUp)) {
            nextIndex = iUp;
        } else {
            return -1;
        }
        pathList.remove(new Integer(index));
        return nextIndex;
    }


    // recursion make it consecutive
    private void moveToIndex(int index){
        int[] nextPosition = GameMapDrawing.indexToCoordinateConverter(index, cols);
        int x = nextPosition[0];
        int y = nextPosition[1];
        if(currentPosY > y){
            moveUp();
        } else if (currentPosY < y){
            moveDown();
        } else if (currentPosX > x){
            moverLeft();
        } else if (currentPosX < x){
            moveRight();
        } else if (currentPosX == x && currentPosY == y){
            nextIndex = getDestination(GameMapDrawing.coordinateToIndexConverter(x, y ,cols));
            if(nextIndex != -1) {
                moveToIndex(nextIndex);
            } else isAlive = false;
        }

    }

    public void moveThroughPathInMap() {
        if(isAlive) moveToIndex(nextIndex);
    }




}
