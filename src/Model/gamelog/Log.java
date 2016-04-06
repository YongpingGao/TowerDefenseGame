package model.gamelog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yongpinggao on 3/25/16.
 */
public class Log {


    private String currentTime;
    private String content;
    private LogType who;
    private int id;
    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public Log(LogType who, String content) {
        this.currentTime = dateFormat.format(new Date());
        this.content = content;
        this.who = who;
    }

    public Log(LogType who, int id, String content) {
        this.currentTime = dateFormat.format(new Date());
        this.content = content;
        this.who = who;
        this.id = id;
    }

    public LogType getWho() {
        return who;
    }

    public int getId() {
        return id;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "[" + currentTime + "]" + " : " + content + "\n";
    }

}
