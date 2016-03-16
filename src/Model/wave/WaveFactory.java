package model.wave;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class WaveFactory {

    private static WaveFactory ourInstance = new WaveFactory();

    public static WaveFactory sharedInstance() {
        return ourInstance;
    }

    private WaveFactory() {}

    public Wave getWave(int waveNum){
        switch (waveNum){
            case 1:
                return new Wave.Builder().critterA(1).build();
            default:
                return null;
        }
    }
}
