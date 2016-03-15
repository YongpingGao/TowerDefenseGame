package protocol;

import model.map.GameMap;
import model.tower.TowerCollection;


/**
 * Created by yongpinggao on 3/14/16.
 */
// for map in the game(towers, critters, etc.)
public interface DrawingMapInGameDelegate extends DrawingMapDelegate{


    void refreshMap(GameMap map, TowerCollection towerCollection);
    void refreshCrittersInMap();
    void refreshShootingEffectInMap(TowerCollection towerCollection);
}
