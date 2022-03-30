package carpark.carpark;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ConfigTest {
    @Test
    public void testDefaultValues(){
        assertEquals(Config.anzahlAutos, Config.DEF_ANZAHL_AUTOS);
        assertEquals(Config.oeffnungsZeit, Config.DEF_OEFFNUNGS_ZEIT);
        assertEquals(Config.schliessZeit, Config.DEF_SCHLIESS_ZEIT);
        assertEquals(Config.verzoegerung, Config.DEF_VERZOEGERUNG);
        assertEquals(Config.preisfaktor, Config.DEF_PREISFAKTOR);
        assertEquals(Config.toCSV(), Config.DEF_ANZAHL_AUTOS+","+Config.DEF_OEFFNUNGS_ZEIT+","+Config.DEF_SCHLIESS_ZEIT+","+Config.DEF_VERZOEGERUNG+","+Config.DEF_PREISFAKTOR);
    }
    @Test
    public void testSetValues(){
        int anzahlAutos = 10;
        int oeffnungsZeit = 5;
        int schliessZeit = 4;
        int verzoegerung = 90;
        int preisfaktor = 5;
        String configString = anzahlAutos+","+oeffnungsZeit+","+schliessZeit+","+verzoegerung+","+preisfaktor;
        Config.setValues(configString);
        assertEquals(Config.anzahlAutos, anzahlAutos);
        assertEquals(Config.oeffnungsZeit, oeffnungsZeit);
        assertEquals(Config.schliessZeit, schliessZeit);
        assertEquals(Config.verzoegerung, verzoegerung);
        assertEquals(Config.preisfaktor, preisfaktor);
        assertEquals(Config.toCSV(), configString);
    }
}
