package carpark.carpark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BodyTest {
    public static final String LICENSE = "leave,87,1624378131068,1,5000,b8cf6df3f885872df1e44bdc32224854,#464d5d,3,_,_,SU-R 40";
    Body b = new Body(LICENSE);

    @Test
    void testgetBody(){Assertions.assertEquals(LICENSE, b.getBody());}
    @Test
    void testgetType(){Assertions.assertEquals("leave", b.getType());}
    @Test
    void testgetCarNr(){Assertions.assertEquals(87, b.getCarNr());}
    @Test
    void testgetDuration(){Assertions.assertEquals(1, b.getDuration());}
    @Test
    void testgetPrice(){Assertions.assertEquals(5000, b.getPrice());}
    @Test
    void testgetTicketId(){Assertions.assertEquals("b8cf6df3f885872df1e44bdc32224854", b.getTicketId());}
    @Test
    void testgetColor(){Assertions.assertEquals("#464d5d", b.getColor());}
    @Test
    void testgetParkingSpotNumber(){Assertions.assertEquals(3, b.getParkingSpotNumber());}
    @Test
    void testgetClientCategory(){Assertions.assertEquals("_", b.getClientCategory());}
    @Test
    void testgetVehicleType(){Assertions.assertEquals("_", b.getVehicleType());}
    @Test
    void testgetLicensePlate(){Assertions.assertEquals("SU-R 40", b.getLicensePlate());}

    @Test
    void testgenerateCar(){
        Body truck = new Body("leave,87,1624378131068,1,5000,b8cf6df3f885872df1e44bdc3222485,#464d5d,3,_,Truck,SU-R 40");
        Body pickup = new Body("leave,87,1624378131068,1,5000,b8cf6df3f885872df1e44bdc32224854,#464d5d,3,_,Pickup,SU-R 40");
        Body suv = new Body("leave,87,1624378131068,1,5000,b8cf6df3f885872df1e44bdc32224854,#464d5d,3,_,SUV,SU-R 40");
        Body zweirad = new Body("leave,87,1624378131068,1,5000,b8cf6df3f885872df1e44bdc32224854,#464d5d,3,_,Zweirad,SU-R 40");
        Body trike = new Body("leave,87,1624378131068,1,5000,b8cf6df3f885872df1e44bdc32224854,#464d5d,3,_,Trike,SU-R 40");
        Body quad = new Body("leave,87,1624378131068,1,5000,b8cf6df3f885872df1e44bdc32224855,#464d5d,3,_,Quad,SU-R 40");
        Body empty = new Body("leave,87,1624378131068,1,5000,b8cf6df3f885872df1e44bdc32224855,#464d5d,3,_,_,SU-R 40");

        Assertions.assertEquals("Truck", truck.generateCar().vehicleType);
        Assertions.assertEquals("Pickup", pickup.generateCar().vehicleType);
        Assertions.assertEquals("SUV", suv.generateCar().vehicleType);
        Assertions.assertEquals("Zweirad", zweirad.generateCar().vehicleType);
        Assertions.assertEquals("Trike", trike.generateCar().vehicleType);
        Assertions.assertEquals("Quad", quad.generateCar().vehicleType);
        Assertions.assertNull(empty.generateCar());
    }

    @Test
    void testgenerateTicket(){
        Assertions.assertEquals(1624378131068l, (b.generateTicket().getEntered().getTimeInMillis()/1000));
    }

    @Test
    void testTypeTrue1() {
        Assertions.assertTrue(Body.checkType("enter"));
    }
    @Test
    void testTypeTrue2() {
        Assertions.assertTrue(Body.checkType("leave"));
    }
    @Test
    void testTypeFalse1() {
        Assertions.assertFalse(Body.checkType("9+56"));
    }
    @Test
    void testTypeFalse2() {
        Assertions.assertFalse(Body.checkType(""));
    }

    @Test
    void testCarNumberTrue() {
        Assertions.assertTrue(Body.checkCarNumber("96"));
    }
    @Test
    void testCarNumberFalse1() {
        Assertions.assertFalse(Body.checkCarNumber("101"));
    }
    @Test
    void testCarNumberFalse2() {
        Assertions.assertFalse(Body.checkCarNumber("-5"));
    }

    @Test
    void testEnteredTrue() {
        Assertions.assertTrue(Body.checkEntered("1623942748727"));
    }
    @Test
    void testEnteredFalse1() {
        Assertions.assertFalse(Body.checkEntered("1623942jk364899"));
    }
    @Test
    void testEnteredFalse2() {
        Assertions.assertFalse(Body.checkEntered(""));
    }

    @Test
    void testDurationTrue() {
        Assertions.assertTrue(Body.checkDuration("_"));
    }
    @Test
    void testDurationFalse1() {
        Assertions.assertFalse(Body.checkDuration("*"));
    }

    @Test
    void testDurationFalse2() {
        Assertions.assertFalse(Body.checkDuration("-201"));
    }

    @Test
    void testPriceTrue() {
        Assertions.assertTrue(Body.checkPrice("326"));
    }
    @Test
    void testPriceFalse1() {
        Assertions.assertFalse(Body.checkPrice("-"));
    }
    @Test
    void testPriceFalse2() {
        Assertions.assertFalse(Body.checkPrice(""));
    }

    @Test
    void testTicketIDTrue() {
        Assertions.assertTrue(Body.checkTicketID("4b0025a9b04a0982236ef52e3a0b7c21"));
    }
    @Test
    void testTicketIDFalse1() {
        Assertions.assertFalse(Body.checkTicketID("9a97193e6f811cfe215+/*-48009e6ab"));
    }
    @Test
    void testTicketIDFalse2() {
        Assertions.assertFalse(Body.checkTicketID("9a97193e6f811cfe215998548009e6ab999"));
    }

    @Test
    void testColorTrue() {
        Assertions.assertTrue(Body.checkColor("#4afcd0"));
    }
    @Test
    void testColorFalse1() {
        Assertions.assertFalse(Body.checkColor("564397c"));
    }
    @Test
    void testColorFalse2() {
        Assertions.assertFalse(Body.checkColor("#*64397c"));
    }

    @Test
    void testParkingSpotNumberTrue() {
        Assertions.assertTrue(Body.checkParkingSpotNumber("7"));
    }
    @Test
    void testParkingSpotNumberFalse1() {
        Assertions.assertFalse(Body.checkParkingSpotNumber("1500"));
    }
    @Test
    void testParkingSpotNumberFalse2() {
        Assertions.assertFalse(Body.checkParkingSpotNumber("5*"));
    }

    @Test
    void testLicensePlateTrue() {
        Assertions.assertTrue(Body.checkLicensePlate("SU-P 16"));
    }
    @Test
    void testLicensePlateFalse1() {
        Assertions.assertFalse(Body.checkLicensePlate("SU-DDD 87"));
    }
    @Test
    void testLicensePlateFalse2() {
        Assertions.assertFalse(Body.checkLicensePlate("SU-B 33--"));
    }
    @Test
    void testLicensePlateFalse3() {
        Assertions.assertFalse(Body.checkLicensePlate("S8-Q 12"));
    }

    @Test
    void testVehicleTypeTrue1() { Assertions.assertTrue(Body.checkVehicleType("Truck")); }
    @Test
    void testVehicleTypeTrue2() { Assertions.assertTrue(Body.checkVehicleType("Trike")); }
    @Test
    void testVehicleTypeFalse1() {
        Assertions.assertFalse(Body.checkVehicleType("PKW33"));
    }
    @Test
    void testVehicleTypeFalse2() {
        Assertions.assertFalse(Body.checkVehicleType(""));
    }
    @Test
    void testVehicleTypeFalse3() {
        Assertions.assertFalse(Body.checkVehicleType("*/-"));
    }
}