package model.tower;
import model.tower.shootingstrategy.TargetBasedOnWeakest;
import view.map.Position;
import view.tower.TowerShootingRangeView;
import view.tower.TowerShootingView;
import view.tower.TowerType;
import view.tower.TowerView;


/**
 * Created by yongpinggao on 3/15/16.
 */
// Freezing the critter for a time (have a higher priority to poison tower)
public class IceTower extends Tower {

    public IceTower(int level){
        if(level <= MAX_LEVEL) {
            this.level = level;
            initTower();
        }
    }

    private void initTower(){
        this.specification = "<html>" + "Ice Tower" + "<br> Level: " + level + "<br> Good at attack fast creatures with its freezing effect</html>";
        switch(level){
            case 1:
                buyPrice = 30.0;
                sellPrice = 15.0;
                towerType = TowerType.IceTower1;
                range = 80;
                towerShootingBehavior = new IceTowerShootingBehavior(1000, 10, new TargetBasedOnWeakest());
                towerView = new TowerView(towerType);
                break;
            case 2:
                buyPrice = 40.0;
                sellPrice = 20.0;
                towerType = TowerType.IceTower2;
                range = 90;
                towerShootingBehavior = new IceTowerShootingBehavior(1500, 20, new TargetBasedOnWeakest());
                towerView = new TowerView(towerType);
                break;
            case 3:
                buyPrice = 50.0;
                sellPrice = 25.0;
                towerType = TowerType.IceTower3;
                range = 100;
                towerShootingBehavior = new IceTowerShootingBehavior(2000, 30, new TargetBasedOnWeakest());
                towerView = new TowerView(towerType);
                break;
        }
    }

    @Override
    public String getHdImageName() {
        return "res/towerB_high.png";
    }

    @Override
    public void setLevel(int level) {
        super.setLevel(level);
        this.level = level;
        initTower();
        setPosition(position);
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
        towerShootingRangeView = new TowerShootingRangeView(position, range);
        switch(level){
            case 1:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.WHITE, 3));
                break;
            case 2:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.WHITE, 4));
                break;
            case 3:
                towerShootingView = new TowerShootingView(position, new ShootingEffect(java.awt.Color.WHITE, 5));
                break;
        }
        towerShootingBehavior.setTowerDidShotDelegate(towerShootingView);
    }

}
