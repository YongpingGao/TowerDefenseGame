package model.tower;

import java.awt.*;

/**
 * Created by yongpinggao on 3/14/16.
 */
public enum ShootingEffect {
    RedStrong,
    BlueWeek,
    BlackDot;

    public static BasicStroke getStoke(ShootingEffect e){
        switch (e){
            case RedStrong:
                return new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            case BlackDot:
                return new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{10f, 10f}, 2f);
            case BlueWeek:
                return new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{10.0f, 3.0f, 1.0f, 10.0f}, 2f);
            default:
                return null;
        }
    }




}
