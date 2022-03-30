package carpark.carpark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ServerDataTest {

    Body b1 = new Body("enter,87,1626952350145,_,_,1,#464d5d,3,_,_,SU-R 40");
    Body b2 = new Body("leave,87,1626952350146,21000,80,1,#464d5d,3,_,_,SU-R 40");
    Body b3 = new Body("enter,23,1626952350145,_,_,2,#a708b7,13,_,_,SU-C 60");
    Body b4 = new Body("leave,23,1626952350146,1,20,2,#a708b7,13,_,_,SU-C 60");
    Body b5 = new Body("enter,26,1627557434113,_,_,3,#a708b7,13,_,_,SU-D 60");
    Body b6 = new Body("leave,26,1627557434115,1,40,3,#a708b7,13,_,_,SU-D 60");
    Body b7 = new Body("enter,29,1627557434230,_,_,4,#a708b9,17,_,_,SU-D 70");

    @BeforeEach
    void setup(){
        ArrayList<Body> cars = new ArrayList<>();
        cars.add(b1);
        cars.add(b2);
        cars.add(b3);
        cars.add(b4);
        cars.add(b5);
        cars.add(b6);
        cars.add(b7);

        ServerData.setHistory(cars);
    }

    @Test
    void testgetCarsEntered(){
        Assertions.assertEquals(4, ServerData.getCarsEntered());
    }

    @Test
    void testgetCarsLeft(){Assertions.assertEquals(3, ServerData.getCarsLeft());}

    @Test
    void testgetTotalIncome(){Assertions.assertEquals(140, ServerData.getTotalIncome());}

    @Test
    void testgetUnexitedBodies(){Assertions.assertEquals(1, ServerData.getUnexitedBodies().size());}

    @Test
    void testhasExited(){
        Assertions.assertFalse(ServerData.hasExited(b7));}

    @Test
    void testgestLongTermParking(){Assertions.assertEquals(1, ServerData.getLongTermParking());}

    @Test
    void testgetShortTermParking(){Assertions.assertEquals(2, ServerData.getShortTermParking());}

    @Test
    void testgetLastEntered(){Assertions.assertEquals(b7.getTicketId(), ServerData.getLastEntered().getTicketId());}

    @Test
    void testgetLastExited(){Assertions.assertEquals(b6, ServerData.getLastExited());}

}
