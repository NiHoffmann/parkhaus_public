package carpark.carpark;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandHandler implements CommandHandlerInt<Command> {
    HttpServletRequest request;
    HttpServletResponse response;

    /**Logger for Errors*/
    private static final Logger LOGGER = Logger.getLogger(CommandHandler.class.getName());

    /**
     * Regulates the request and response
     * @param request Request to the server
     * @param response Response from the server
     */
    public CommandHandler(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    /**
     * Executes the incoming command
     * @param command Today date
     */
    public void run(Command command){
        if (command.getCommand().equals("cmd")) {
            StatisticsHandler statisticsHandler;
            UiWriter ui;
            try {
                ui = new UiWriter(request, response);
                statisticsHandler = new StatisticsHandler(request,response);
            }
            catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
                return;
            }

            try {
                switch (command.getParam()) {
                    case "sum":
                        ui.sum();
                        break;
                    case "avg":
                        ui.avg();
                        break;
                    case "Besucher":
                        ui.besucher();
                        break;
                    case "Preis":
                        ui.preis();
                        break;
                    case "Anzahl":
                        ui.graphAnzahl();
                        break;
                    case "Parkverhalten":
                        ui.graphParkverhalten();
                        break;
                    case "Tageseinnahmen":
                        statisticsHandler.dailyIncome(Calendar.getInstance());
                        break;
                    case "Wocheneinnahmen":
                        statisticsHandler.weeklyIncome(Calendar.getInstance());
                        break;
                    case "Kundensicht":
                        statisticsHandler.customerView(System.currentTimeMillis());
                        break;
                    case "cars":
                        loadCars();
                        break;
                    case "config":
                        response.getWriter().print(Config.toCSV());
                        break;
                    default:
                        LOGGER.log(Level.FINEST, "Invalid Command: " + request.getQueryString());
                        break;
                }
            }
            catch (IOException e) {
                LOGGER.log(Level.SEVERE, e.toString(), e);
            }
        }
    }

    /**loadCars loads the car images on to the UI upon site refresh*/
    public void loadCars() throws IOException {
        StringBuilder returnString = new StringBuilder();

        for (Car car: Carpark.spots.values()) {
            for (int i = ServerData.history.size() -1; i >= 0; i--) {
                Body body = ServerData.history.get(i);

                if (body.getTicketId().equals(car.ticket().getId())) {
                    if(!returnString.toString().equals("")){
                        returnString.append(",");
                    }
                    returnString.append(body.toApiFormat());
                }
            }
        }

        response.getWriter().println(returnString.toString());
    }

}


