package view.map;

/**
 * Created by yongpinggao on 3/19/16.
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int distanceTo(Position p){
        int dx = p.x - this.x;
        int dy = p.y - this.y;
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    public Position getCenterPosition(){
        return new Position(x + Drawing.CELL_SIZE / 2, y + Drawing.CELL_SIZE / 2);
    }

}
