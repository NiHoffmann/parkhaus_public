package carpark.carpark;
import java.util.List;
/** Class for setting the carpark config */
public class Config {
    /**Default values for anzahlAutos*/
    static final int DEF_ANZAHL_AUTOS = 20;
    /**Default values for oeffnungsZeit*/
    static final int DEF_OEFFNUNGS_ZEIT = 6;
    /**Default values for schliessZeit*/
    static final int DEF_SCHLIESS_ZEIT = 24;
    /**Default values for verzoegerung*/
    static final int DEF_VERZOEGERUNG = 100;
    /**Default values for preisfaktor*/
    static final int DEF_PREISFAKTOR = 10;
    /** Determine config parameters for the carpark */
    /** Max amount of available parking spots */
    static int anzahlAutos = DEF_ANZAHL_AUTOS;
    /** Opening time */
    static int oeffnungsZeit = DEF_OEFFNUNGS_ZEIT;
    /** Closing time */
    static int schliessZeit = DEF_SCHLIESS_ZEIT;
    /** Delay */
    static int verzoegerung = DEF_VERZOEGERUNG;
    /** Price factor */
    static int preisfaktor = DEF_PREISFAKTOR;
    /**Private constructor of Config class*/
    private Config(){

    }
    /** @return Config Parameters in CSV string format */
    public static String toCSV() {
        return anzahlAutos+","+oeffnungsZeit+","+schliessZeit+","+verzoegerung+","+preisfaktor;
    }
    /** Load content of CSV into an array */
    public static void load() {
        List<String> configCSV = CSV.read("Config");

        if(!configCSV.isEmpty()) {
            String configString = configCSV.get(0);
            setValues(configString);
        }
    }
    /** set config values passed by String each value is seperated by a ","*/
    public static void setValues(String configString){
        String[] configArray = configString.split(",");
        if(ConfigBody.checkNumberFormat(configArray[0]))
            anzahlAutos = Integer.parseInt(configArray[0]);
        if(ConfigBody.checkNumberFormat(configArray[1]))
            oeffnungsZeit = Integer.parseInt(configArray[1]);
        if(ConfigBody.checkNumberFormat(configArray[2]))
            schliessZeit = Integer.parseInt(configArray[2]);
        if(ConfigBody.checkNumberFormat(configArray[3]))
            verzoegerung = Integer.parseInt(configArray[3]);
        if(ConfigBody.checkNumberFormat(configArray[4]))
            preisfaktor = Integer.parseInt(configArray[4]);
    }
    /** Create CSV as backup */
    public static void backup() {
        CSV.save("Config", toCSV());
    }
}
