package model.tower;

import java.awt.*;

/**
 * Created by yongpinggao on 3/14/16.
 */
public enum ShootingEffect {

    IceEffect,
    PoisonEffect,
    NormalEffect;

    public static BasicStroke getStoke(ShootingEffect e){
        switch (e){
            case IceEffect:
                return new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            case NormalEffect:
                return new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{10f, 10f}, 2f);
            case PoisonEffect:
                return new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f, new float[]{10.0f, 3.0f, 1.0f, 10.0f}, 2f);
            default:
                return null;
        }
    }




}
