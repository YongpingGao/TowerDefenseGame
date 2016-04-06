package protocol;

import model.map.GameMap;

/**
 * Created by yongpinggao on 3/12/16.
 */
//Base interface: for map editor map
public interface DrawingMapDelegate {
    void refreshMap(GameMap map);
}
