package utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;


/**
 * Created by yongpinggao on 1/26/16.
 */
public class FileProcessing {

    // Singleton
    private static FileProcessing instance = new FileProcessing();
    public static FileProcessing sharedInstance() {
        return instance;
    }

    private static Gson gson;
    private FileProcessing() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Input: Map information: 1. map size(rows and cols the map has); 2. a list of map cell state
    // Output: Write the info to a file in JSON Format
    // Dependency: gson-2.5 library
    public <T> boolean writeToJsonFile(String fileName, T t){
        try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
                writer.write(gson.toJson(t));
                writer.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
    }

    public <T> T readFromJsonFile(String fileName, Class<T> _class) {
        File file = new File(fileName);
        if (file.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileName));
                T t = gson.fromJson(br, _class);
                return t;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
    }
}
