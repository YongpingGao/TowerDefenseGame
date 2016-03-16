package model.critter;

import model.imagecollection.CritterImageCollection;
import model.map.GameMap;
import model.drawing.GameMapDrawing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class Critter implements ActionListener{
    protected CritterName critterName;
    protected int currentHealth;
    protected int maxHealth;
    protected double worth;
    protected int currentMoveSpeed;
    protected int initialMoveSpeed;
    protected boolean isAlive;
    protected boolean isSucceed;

    protected Timer movingTimer;
    protected Timer innerTimer;
    protected int continuesDamage;

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

    public ArrayList<Integer> getPathList() {
        return pathList;
    }

    public void setPathList(ArrayList<Integer> pathList) {
        this.pathList = pathList;
    }

    public boolean isSucceed() {
        return isSucceed;
    }

    public void setSucceed(boolean succeed) {
        isSucceed = succeed;
    }

    public Timer getMovingTimer() {
        return movingTimer;
    }

    public void setMovingTimer(Timer movingTimer) {
        this.movingTimer = movingTimer;
    }

    public Timer getInnerTimer() {
        return innerTimer;
    }

    public int getContinuesDamage() {
        return continuesDamage;
    }

    public void setContinuesDamage(int continuesDamage) {
        this.continuesDamage = continuesDamage;
    }

    public void setInnerTimer(Timer innerTimer) {
        this.innerTimer = innerTimer;
    }

    public int getInitialMoveSpeed() {
        return initialMoveSpeed;
    }

    public void setInitialMoveSpeed(int initialMoveSpeed) {
        this.initialMoveSpeed = initialMoveSpeed;
    }

    public Rectangle getBound(){
        Dimension dimension = CritterImageCollection.getCritterImageSizeOf(critterName);
        return new Rectangle(currentPosX, currentPosY, dimension.width, dimension.height);
    }

    public float getHealthBarLength() {
        return (float)(currentHealth) / maxHealth;
    }

    public int getCurrentMoveSpeed() {
        return currentMoveSpeed;
    }

    public void setCurrentMoveSpeed(int currentMoveSpeed) {
//        moveToIndex(getDestination(GameMapDrawing.coordinateToIndexConverter(currentPosX, currentPosY ,cols)));
        this.currentMoveSpeed = currentMoveSpeed;
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

    // x, y -> current position
    private void moveRight(){
        currentPosX += currentMoveSpeed;
    }

    private void moveDown(){
        currentPosY += currentMoveSpeed;

    }

    private void moverLeft(){
        currentPosX -= currentMoveSpeed;
    }

    private void moveUp(){
        currentPosY -= currentMoveSpeed;
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

        if (currentPosX == x && currentPosY == y){
            nextIndex = getDestination(GameMapDrawing.coordinateToIndexConverter(x, y ,cols));
            if(nextIndex != -1) {
                moveToIndex(nextIndex);
            } else {
                isAlive = false;
                isSucceed = true;
            }
        } else {
            if(currentPosY - y >= currentMoveSpeed){
                moveUp();
            }
            else if (y - currentPosY >= currentMoveSpeed){
                moveDown();
            }
            else if (currentPosX - x >= currentMoveSpeed){
                moverLeft();
            }
            else if (x - currentPosX >= currentMoveSpeed){
                moveRight();
            } else {
                currentPosX = x;
                currentPosY = y;
            }
        }
    }

    public void moveThroughPathInMap() {
        if(isAlive) moveToIndex(nextIndex);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() != null){ // critter get poisoned
            if(e.getActionCommand().equals("POISON")){
                currentHealth -= continuesDamage;
            }
        } else { // set back to initial speed of frozen state or low speed state
            if(innerTimer != null) innerTimer.stop();
            setCurrentMoveSpeed(initialMoveSpeed);
        }
    }
}
