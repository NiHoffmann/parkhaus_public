package carpark.carpark;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/Parkhaus")
//@WebServlet(name = "Parkhaus", value = "/")
public class Servlet extends HttpServlet {
    static final long serialVersionUID = 1L;
    /**Logger for Errors*/
    private static final Logger LOGGER = Logger.getLogger(Servlet.class.getName());

    /**
     * Constructor method
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
    }

    /**
     * HttpGet
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        CommandHandler commandHandler = new CommandHandler(request, response);
        commandHandler.run(new Command(request.getQueryString()));
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bodyString = getBody(request);

        ConfigBody configBody = new ConfigBody(bodyString);
        if(configBody.isConfig()){
            switch (configBody.getType()) {
                case "change_max":
                    Config.anzahlAutos = configBody.getTo();
                    break;
                case "change_open_from":
                    Config.oeffnungsZeit = configBody.getTo();
                    break;
                case "change_open_to":
                    Config.schliessZeit = configBody.getTo();
                    break;
                default:
                    LOGGER.log(Level.FINEST, String.format("%s: ", "Invalid Command"), bodyString);
                    break;
            }
        }

        Body body = new Body(bodyString);
        System.out.println(body.getBody());

        Car car;
        if (body.getType().equals("enter")) {
            car = body.generateCar();

            Carpark.enter(body);
            System.out.println("CAR ENTERED: " + car.toString());
        }
        else if (body.getType().equals("leave")) {
            car = Carpark.exit(body);

            assert car != null;
            System.out.println("CAR EXITED: " + car.toString());
        }

        ServerData.addEvent(body);
    }

    /**
     * Divide body into a string
     * @param request HttpServletRequest
     * @return stringBuilder finished String
     */
    private static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try (InputStream inputStream = request.getInputStream()) {

            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;

                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, e.toString(), e);
                }
            }
        }

        return stringBuilder.toString();
    }
}