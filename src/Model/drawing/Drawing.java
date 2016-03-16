package model.drawing;

/**
 * Created by yongpinggao on 3/15/16.
 */
// Base class of Drawing
public class Drawing {
    // input: coordinate(x,y)(pixels), cell size of a cell. And cols number
    // output: nth cell in whole map
    public static int coordinateToIndexConverter(int x, int y, int cols) {
        return x / CELL_SIZE + (y / CELL_SIZE) * cols;
    }

    public static int[] indexToCoordinateConverter(int index, int cols){
        int x = index % cols;
        int y = index / cols;
        return new int[]{x * CELL_SIZE, y * CELL_SIZE};
    }

    public final static int CELL_SIZE = 30;
}
