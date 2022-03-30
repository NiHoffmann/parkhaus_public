package carpark.carpark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class ViewTest {
    HttpServletRequest request;
    HttpServletResponse response;

    //Tue Jun 22 2021 16:08:51
    public static final String LICENSE1 = "enter,87,1626952350145,_,_,b8cf6df3f885872df1e44bdc32224854,#464d5d,3,_,_,SU-R 40";
    public static final String LICENSE2 = "leave,87,1626952350146,1,5000,b8cf6df3f885872df1e44bdc32224854,#464d5d,3,_,_,SU-R 40";
    public static final String LICENSE3 = "enter,23,1626952350145,_,_,c4bbb149b05e68e9a12d553educ701bd,#a708b7,13,_,_,SU-C 60";
    public static final String LICENSE4 = "leave,23,1626952350146,1,1000,c4bbb149b05e68e9a12d553educ701bd,#a708b7,13,_,_,SU-C 60";

    //Tue Jun 29 2021 14:08:51
    public static final String LICENSE5 = "enter,23,1627557434113,_,_,c4bbb149b05e68e9a12d553educ701bd,#a708b7,13,_,_,SU-C 60";
    public static final String LICENSE6 = "leave,23,1627557434115,1,80000000,f2a096e894541ec6eda887c74e0a9a43,#a708b7,13,_,_,SU-C 60";

    @Test
    void testViewDailyIncome() throws IOException {
        ArrayList<Body> cars = new ArrayList<>();
        Body a = new Body(LICENSE1);
        Body b = new Body(LICENSE2);
        Body c = new Body(LICENSE3);
        Body d = new Body(LICENSE4);
        cars.add(a);
        cars.add(b);
        cars.add(c);
        cars.add(d);
        ServerData.setHistory(cars);

        StatisticsHandler s = new StatisticsHandler(request, response);
        Calendar cal = Calendar.getInstance();
        cal.set(2021,Calendar.JULY,22);
        Assertions.assertEquals(60.0, s.dailyIncome(cal));
    }
    @Test
    void testViewWeeklyIncome() throws IOException{
        ArrayList<Body> cars = new ArrayList<>();
        Body a = new Body(LICENSE1);
        Body b = new Body(LICENSE2);
        Body c = new Body(LICENSE3);
        Body d = new Body(LICENSE4);
        Body e = new Body(LICENSE5);
        Body f = new Body(LICENSE6);

        cars.add(a);
        cars.add(b);
        cars.add(c);
        cars.add(d);
        cars.add(e);
        cars.add(f);
        ServerData.setHistory(cars);

        StatisticsHandler s = new StatisticsHandler(request, response);
        Calendar cal = Calendar.getInstance();
        cal.set(2021, Calendar.JULY,29);

        Assertions.assertEquals(800000.0, s.weeklyIncome(cal));
    }
    @Test
    void testViewCustomer() throws IOException{
        ArrayList<Body> cars = new ArrayList<>();
        Body a = new Body(LICENSE4);
        cars.add(a);
        ServerData.setHistory(cars);
        ServerData.setLastEntered(a);

        StatisticsHandler s = new StatisticsHandler(request, response);

        Assertions.assertEquals(0.007f, s.customerView(1626952350146L + 7));
    }
}