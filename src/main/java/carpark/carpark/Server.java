package carpark.carpark;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * Class for the Tomcat events
 */
@WebListener
public class Server implements ServletContextListener {
    /** scheduler event from the server */
    private ScheduledExecutorService scheduler;

    /**
     * Performs the backup
     */
    private static final Runnable backup = () -> {
        ServerData.backup();
        Config.backup();
    };

    /**
     * Executes the config at server start, and reads the history file
     * @param event Server status
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        Config.load();
        
        ArrayList<Body> history = CSV.read("History").stream().filter(body-> Body.checkBody(body.split(",")).equals(Body.BodyField.CORRECT)).map(Body::new).collect(Collectors.toCollection(ArrayList::new));
        ServerData.setHistory(history);

        List<Body> carsInside = ServerData.getUnexitedBodies();
        for (Body body: carsInside) {
            Carpark.enter(body);
        }

        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(Server.backup, 60, 60, TimeUnit.SECONDS);
    }

    /**
     * Executes the backup, and shutdown the Server
     * @param event Server status
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        backup.run();
        scheduler.shutdownNow();
    }
}