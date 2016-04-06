package model.gamelog;

import java.util.ArrayList;

/**
 * Created by yongpinggao on 3/25/16.
 */
public class LoggerCollection {


    private static LoggerCollection sharedInstance = new LoggerCollection();

    public static LoggerCollection getInstance() {
        return sharedInstance;
    }

    private LoggerCollection() {}

    private ArrayList<Log> logList = new ArrayList<>();

    public void addLog(Log log) {
        logList.add(log);
    }

    public String showAllLog() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Log log: logList) {
            stringBuilder.append(log.toString());
        }
        return stringBuilder.toString();
    }

    public String showAllTowerLog() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Log log: logList) {
            if(log.getWho() == LogType.Tower) {
                stringBuilder.append(log.toString());
            }
        }
        return stringBuilder.toString();
    }

    public String showTowerLogAtIndex(int index) {
        StringBuilder stringBuilder = new StringBuilder();
        for(Log log: logList) {
            if(log.getWho() == LogType.Tower) {
                if(index == log.getId()) {
                    stringBuilder.append(log.toString());
                }
            }
        }
        return stringBuilder.toString();
    }

}
