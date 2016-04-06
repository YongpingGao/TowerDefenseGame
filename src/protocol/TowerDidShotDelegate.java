package protocol;

import view.map.Position;

/**
 * Created by yongpinggao on 3/20/16.
 */
public interface TowerDidShotDelegate {
    void towerDidShotAt(Position critterPosition);
}
