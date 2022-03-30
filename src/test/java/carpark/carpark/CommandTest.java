package carpark.carpark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CommandTest {

    @Test
    void testCommandTrue() {
        String command = "com";
        Command a = new Command("com=12&13");
        Assertions.assertTrue(a.getCommand().equals(command));
    }

    @Test
    void testCommandFalse() {
        String command = "tttcom";
        Command a = new Command("com=cc&12313");
        Assertions.assertFalse(a.getCommand().equals(command));
    }

    @Test
    void testParamTrue() {
        String param = "cc";
        Command a = new Command("enter=cc&12313");
        Assertions.assertTrue(a.getParam().equals(param));
    }

    @Test
    void testParamFalse() {
        String param = "444cc";
        Command a = new Command("enter=cc&12313");
        Assertions.assertFalse(a.getParam().equals(param));
    }
}
