package carpark.carpark;

import java.util.regex.Pattern;

/** Class that checks a string of config parameters for acceptable format*/
class ConfigBody{
    /** Determines if config is in acceptable format */
    private boolean isConfig = true;
    /** Max amount of available parking spots */
    private String type;
    /** Opening TIme */
    private int from;
    /** Closing Time */
    private int to;

    /** @param body String of config parameters */
    public ConfigBody(String body){
        String[] parts = body.split(",");

        if(parts[0].equals("change_max") || parts[0].equals("change_open_from") || parts[0].equals("change_open_to")){
            type = parts[0];
        } else{
            isConfig = false;
        }


        if(checkNumberFormat(parts[1])){
            from = Integer.parseInt(parts[1]);
        }else{
            isConfig = false;
        }

        if(checkNumberFormat(parts[2])){
            to = Integer.parseInt(parts[2]);
        }else{
            isConfig = false;
        }

    }

    public String getType(){
        return type;
    }

    public int getFrom(){
        return from;
    }

    public int getTo(){
        return to;
    }

    public boolean isConfig(){
        return isConfig;
    }


    /**
     * @param string Config parameters in string format
     * @return Determines if config is in acceptable format
     */
    public static boolean checkNumberFormat(String string) {
        return Pattern.compile("^([0-9]|[1-9][0-9])$").matcher(string).matches();
    }

}