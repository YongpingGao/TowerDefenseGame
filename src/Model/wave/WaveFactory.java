package model.wave;

/**
 * Created by yongpinggao on 3/13/16.
 */
public class WaveFactory {

    private static WaveFactory ourInstance = new WaveFactory();

    public static final int MAX_WAVE_NUM = 6;

    public static WaveFactory sharedInstance() {
        return ourInstance;
    }

    private WaveFactory() {}

    public Wave getWave(int waveNum){
        switch (waveNum){
            case 1:
                return new Wave.Builder().critterA(4).critterB(5).build();
            case 2:
                return new Wave.Builder().critterA(4).critterB(5).critterC(6).build();
            case 3:
                return new Wave.Builder().critterA(3).critterB(10).critterC(8).critterD(8).build();
            case 4:
                return new Wave.Builder().critterA(11).critterB(10).critterC(8).critterD(8).build();
            case 5:
                return new Wave.Builder().critterA(13).critterB(10).critterC(8).critterD(8).build();
            case 6:
                return new Wave.Builder().critterA(15).critterB(20).critterC(12).critterD(13).build();
            default:
                return null;
        }
    }
}
