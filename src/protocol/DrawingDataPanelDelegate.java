package protocol;

/**
 * Created by yongpinggao on 3/15/16.
 */
public interface DrawingDataPanelDelegate {
    void reloadWaveDataView (int waveNum);
    void reloadBalanceDataView(double balance);
    void reloadCoinDataView(int coin);
    void reloadInfoDataView(String info);
}
