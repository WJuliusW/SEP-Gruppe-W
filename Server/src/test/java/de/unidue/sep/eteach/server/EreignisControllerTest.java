package de.unidue.sep.eteach.server;

import de.unidue.sep.eteach.server.Controller.EreignisController;
import de.unidue.sep.eteach.server.Entities.Ereignis;
import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Service.EreignisService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EreignisControllerTest {


    EreignisService ereignisService; // Diese Klasse mocke ich, wegen der Abhängigkeiit
    EreignisController ereignisController; // Das ist die Klasse die wir Testen

    @Before

    public void setup() {

        ereignisService = Mockito.mock(EreignisService.class);
        ereignisController = new EreignisController(ereignisService);
    }

    @Test
    public void createNewEreignisTest() {

        Ereignis ereignis = new Ereignis();
        ereignis.setTitel("test");

        Mockito.when(ereignisService.createEreignis(ereignis)).thenReturn(ereignis);
        Ereignis testEreignis = ereignisController.createNewEreignis(ereignis).getBody();

        assertEquals(ereignis.getTitel(), testEreignis.getTitel());

    }

    @Test
    public void getByIdTest() {

        Ereignis ereignis = new Ereignis();
        ereignis.setId(1);

        Mockito.when(ereignisService.createEreignis(ereignis)).thenReturn(ereignis);
        ereignisController.createNewEreignis(ereignis);

        Mockito.when(ereignisService.getEreignisById(1)).thenReturn(ereignis);

        assertEquals(ereignis, ereignisController.getById(1).getBody());


    }


    @Test
    public void getAllTest() {

        Ereignis ereignis = new Ereignis();
        Ereignis ereignis1 = new Ereignis();
        Ereignis ereignis2 = new Ereignis();

        Mockito.when(ereignisService.createEreignis(ereignis)).thenReturn(ereignis);
        ereignisController.createNewEreignis(ereignis);

        Mockito.when(ereignisService.createEreignis(ereignis1)).thenReturn(ereignis1);
        ereignisController.createNewEreignis(ereignis1);

        Mockito.when(ereignisService.createEreignis(ereignis2)).thenReturn(ereignis2);
        ereignisController.createNewEreignis(ereignis2);

        List<Ereignis> ereignisList = new ArrayList<>();
        ereignisList.add(ereignis);
        ereignisList.add(ereignis1);
        ereignisList.add(ereignis2);

        Mockito.when(ereignisService.getAll()).thenReturn(ereignisList);
        assertEquals(ereignisList, ereignisController.getAll().getBody());


    }

    @Test
    public void editEreignisTest() {

        Ereignis ereignis = new Ereignis();
        ereignis.setTitel("Test");

        Mockito.when(ereignisService.createEreignis(ereignis)).thenReturn(ereignis);
        ereignisController.createNewEreignis(ereignis);

        ereignis.setTitel("Test2");
        Mockito.when(ereignisService.editEreignis(ereignis)).thenReturn(ereignis);

        assertEquals(ereignis, ereignisController.editEreignis(ereignis, "").getBody());

    }

    @Test

    public void deleteEreignisTest() {

        Ereignis ereignis = new Ereignis();
        ereignis.setId(1);

        Mockito.when(ereignisService.createEreignis(ereignis)).thenReturn(ereignis);
        ereignisController.createNewEreignis(ereignis);

        Mockito.when(ereignisService.deleteEreignis(1)).thenReturn(ereignis);

        assertEquals(ereignis, ereignisController.deleteEreignis(1).getBody());


    }
}

