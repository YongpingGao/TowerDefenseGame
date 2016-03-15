package protocol;

import model.tower.Tower;

/**
 * Created by yongpinggao on 3/13/16.
 */
public interface DrawingPanelDelegate {
    void reloadPanelBasedOnTower(Tower tower);
}
