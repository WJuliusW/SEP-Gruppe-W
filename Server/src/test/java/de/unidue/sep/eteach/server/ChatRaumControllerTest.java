package de.unidue.sep.eteach.server;

import de.unidue.sep.eteach.server.Controller.ChatRaumController;
import de.unidue.sep.eteach.server.Entities.ChatNachricht;
import de.unidue.sep.eteach.server.Entities.Nutzer;
import de.unidue.sep.eteach.server.Entities.Veranstaltung;
import de.unidue.sep.eteach.server.Service.ChatRaumService;
import de.unidue.sep.eteach.server.Service.NutzerService;
import de.unidue.sep.eteach.server.Service.VeranstaltungService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ChatRaumControllerTest {

    VeranstaltungService veranstaltungService;

    NutzerService nutzerService;

    ChatRaumService chatRaumService;

    ChatRaumController chatRaumController;

    private final Integer testVeranstaltung = 1;
    private final Integer testNutzer1 = 1;
    private final Integer testNutzer2 = 2;
    private final Integer testNutzer3 = 3;

    /**
     * Im setup werden gemockte Versionen von chatRaum-, veranstaltung- und nutzerService erstellt
     * und dann wird ein neuer ChatController angelegt mit den gemockten Services
     */
    @Before
    public void setup() {

        Veranstaltung veranstaltung = new Veranstaltung();
        veranstaltung.setId(testVeranstaltung);

        Nutzer nutzer1 = new Nutzer();
        nutzer1.setId(1);

        Nutzer nutzer2 = new Nutzer();
        nutzer1.setId(2);

        Nutzer nutzer3 = new Nutzer();
        nutzer1.setId(3);

        veranstaltungService = Mockito.mock(VeranstaltungService.class);
        Mockito.when(veranstaltungService.getVeranstaltungById(testVeranstaltung)).thenReturn(veranstaltung);

        nutzerService = Mockito.mock(NutzerService.class);
        Mockito.when(nutzerService.getNutzerById(testNutzer1)).thenReturn(nutzer1);
        Mockito.when(nutzerService.getNutzerById(testNutzer2)).thenReturn(nutzer2);
        Mockito.when(nutzerService.getNutzerById(testNutzer3)).thenReturn(nutzer3);

        chatRaumService = Mockito.mock(ChatRaumService.class);
        chatRaumController = new ChatRaumController(chatRaumService, veranstaltungService, nutzerService);
    }

    /**
     * Testet ob Nachrichten erfolgreich erstellt werden können
     * Dazu wird der Inhalt, die ID der Veranstaltung und die ID des Nutzer überprüft
     */
    @Test
    public void createChatNachrichtTest() {

        ChatNachricht chatNachricht = new ChatNachricht();
        chatNachricht.setInhalt("Test");
        chatNachricht.setAbsender(nutzerService.getNutzerById(testNutzer1));
        chatNachricht.setVeranstaltung(veranstaltungService.getVeranstaltungById(testVeranstaltung));

        Mockito.when(chatRaumService.getChatNachrichtenByChatRaum(veranstaltungService.getVeranstaltungById(testVeranstaltung))).thenReturn(new ArrayList<>());
        assertTrue(chatRaumController.getChatByVeranstaltung(testVeranstaltung).getBody().isEmpty());

        Mockito.when(chatRaumService.createChatNachricht(chatNachricht)).thenReturn(chatNachricht);
        ChatNachricht testNachricht = chatRaumController.createChatNachricht(testVeranstaltung, testNutzer1, chatNachricht).getBody();

        assertEquals(nutzerService.getNutzerById(testNutzer1), testNachricht.getAbsender());
        assertEquals(veranstaltungService.getVeranstaltungById(testVeranstaltung), testNachricht.getVeranstaltung());
        assertEquals(chatNachricht.getInhalt(), testNachricht.getInhalt());

    }

    /**
     * Testet ob Nachrichten ordnungsgemäß gelöscht werden
     */
    @Test
    public void deleteChatNachrichtTest() {

        ChatNachricht chatNachricht = new ChatNachricht();
        chatNachricht.setId(1);
        chatNachricht.setAbsender(nutzerService.getNutzerById(testNutzer1));
        chatNachricht.setVeranstaltung(veranstaltungService.getVeranstaltungById(testVeranstaltung));
        Mockito.when(chatRaumService.createChatNachricht(chatNachricht)).thenReturn(chatNachricht);
        int testChatNachricht = chatRaumController.createChatNachricht(testVeranstaltung, testNutzer1, chatNachricht).getBody().getId();
        Mockito.when(chatRaumService.deleteChatNachricht(1)).thenReturn(chatNachricht);
        chatRaumController.deleteChatNachricht(testChatNachricht);
        Mockito.when(chatRaumService.getChatNachrichtenByChatRaum(veranstaltungService.getVeranstaltungById(testVeranstaltung))).thenReturn(new ArrayList<>());
        assertTrue(chatRaumController.getChatByVeranstaltung(testVeranstaltung).getBody().isEmpty());


    }

    /**
     * Testet ob mehrere Nachrichten von verschiedenen Nutzern erstellt und danach abgerufen werden können
     */
    @Test
    public void getChatByVeranstaltungTest() {

        ChatNachricht chatNachricht1 = new ChatNachricht();
        ChatNachricht chatNachricht2 = new ChatNachricht();
        ChatNachricht chatNachricht3 = new ChatNachricht();
        chatNachricht1.setVeranstaltung(veranstaltungService.getVeranstaltungById(testVeranstaltung));
        chatNachricht2.setVeranstaltung(veranstaltungService.getVeranstaltungById(testVeranstaltung));
        chatNachricht3.setVeranstaltung(veranstaltungService.getVeranstaltungById(testVeranstaltung));
        chatNachricht1.setAbsender(nutzerService.getNutzerById(testNutzer1));
        chatNachricht2.setAbsender(nutzerService.getNutzerById(testNutzer2));
        chatNachricht3.setAbsender(nutzerService.getNutzerById(testNutzer3));

        Mockito.when(chatRaumService.createChatNachricht(chatNachricht1)).thenReturn(chatNachricht1);
        Mockito.when(chatRaumService.createChatNachricht(chatNachricht2)).thenReturn(chatNachricht2);
        Mockito.when(chatRaumService.createChatNachricht(chatNachricht3)).thenReturn(chatNachricht3);
        chatRaumController.createChatNachricht(testVeranstaltung, testNutzer1, chatNachricht1).getBody();
        chatRaumController.createChatNachricht(testVeranstaltung, testNutzer2, chatNachricht2).getBody();
        chatRaumController.createChatNachricht(testVeranstaltung, testNutzer3, chatNachricht3).getBody();

        List<ChatNachricht> chatNachrichtList = new ArrayList<>();
        chatNachrichtList.add(chatNachricht1);
        chatNachrichtList.add(chatNachricht2);
        chatNachrichtList.add(chatNachricht3);
        Mockito.when(chatRaumService.getChatNachrichtenByChatRaum(veranstaltungService.getVeranstaltungById(testVeranstaltung))).thenReturn(chatNachrichtList);
        assertEquals(nutzerService.getNutzerById(testNutzer1), chatRaumController.getChatByVeranstaltung(testVeranstaltung).getBody().get(0).getAbsender());
        assertEquals(nutzerService.getNutzerById(testNutzer2), chatRaumController.getChatByVeranstaltung(testVeranstaltung).getBody().get(1).getAbsender());
        assertEquals(nutzerService.getNutzerById(testNutzer3), chatRaumController.getChatByVeranstaltung(testVeranstaltung).getBody().get(2).getAbsender());
    }
}
