package carpark.carpark;

class Command implements CommandInt {
    /** Command String */
    private String commando;
    /** Param String */
    private String param;

    /**
     * Splits the command and parameters
     * @param commandString Complete String with command and parameter
     */
    public Command(String commandString){
        if (commandString == null)
            return;

        String[] requestParamString = commandString.split("=");
        commando = requestParamString[0];
        param = requestParamString[1].split("&")[0];
    }

    public String getCommand() { return commando; }
    public String getParam() { return param; }

}