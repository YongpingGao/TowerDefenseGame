package model.critter;

import view.map.Position;
import view.map.Drawing;
import model.map.GameMap;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by yongpinggao on 3/18/16.
 */
public class CritterMovingBehavior implements ActionListener {

    private final int DELAY = 50;
    private Timer movingTimer;
    private int initialMoveSpeed;

    private Position currentPosition;
    private int nextIndex;
    private ArrayList<Integer> pathList;
    private int cols;
    private int entranceIndex;

    private boolean arrivedAtExit;

    public CritterMovingBehavior(GameMap gameMap, int movingSpeed){
        pathList = gameMap.findPathList();
        cols = gameMap.getmCols();
        entranceIndex = gameMap.findEntranceIndex();
        currentPosition = Drawing.indexToCoordinateConverter(entranceIndex, cols);
        nextIndex = entranceIndex;

        initialMoveSpeed = movingSpeed;
    }

    public boolean isArrivedAtExit() {
        return arrivedAtExit;
    }



    public ArrayList<Integer> getPathList() {
        return pathList;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    // x, y -> current position
    private void moveRight(){
        currentPosition.setX(currentPosition.getX() + 1);
    }

    private void moveDown(){
        currentPosition.setY(currentPosition.getY() + 1);
    }

    private void moveLeft(){
        currentPosition.setX(currentPosition.getX() - 1);
    }

    private void moveUp(){
        currentPosition.setY(currentPosition.getY() - 1);
    }

    private int getDestination(int index) {
        int iLeft = index - 1;
        int iRight = index + 1;
        int iDown = index + cols;
        int iUp = index - cols;
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

    public Timer getMovingTimer() {
        return movingTimer;
    }

    // recursion make it consecutive
    private void moveToIndex(int index){
        Position position = Drawing.indexToCoordinateConverter(index, cols);
        int x = position.getX();
        int y = position.getY();

        if (currentPosition.getX() == x && currentPosition.getY() == y){
            nextIndex = getDestination(Drawing.coordinateToIndexConverter(x, y ,cols));
            if(nextIndex != -1) {
                moveToIndex(nextIndex);
            } else {
                movingTimer.stop();
                arrivedAtExit = true;
            }
        } else {
            if(currentPosition.getY() > y){
                moveUp();
            }
            else if (y > currentPosition.getY()){
                moveDown();
            }
            else if (currentPosition.getX() > x){
                moveLeft();
            }
            else if (x > currentPosition.getX()){
                moveRight();
            }
        }
    }

    public void move() {
        movingTimer = new Timer(DELAY - initialMoveSpeed, this);
        movingTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveToIndex(nextIndex);
    }
}
