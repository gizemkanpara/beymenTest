package utilities;

import org.apache.log4j.Logger;

public class Log {

    //Initialize Log4j instance
    private static Logger Log = Logger.getLogger(Log.class.getName());


    public static void startLog (){
        Log.info("Test Başladı...");
    }

    public static void endLog (){
        Log.info("Test Bitti...");
    }

    public static void info (String message) {
        Log.info(message);
    }

    public static void error (String message) {
        Log.error(message);
    }

}
