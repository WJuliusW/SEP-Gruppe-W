package de.unidue.SEP.eteach.client.Controller;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Endpoints.ChatRaumEndpoint;
import de.unidue.SEP.eteach.client.Entities.ChatNachricht;
import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.RetrofitClient;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.util.Duration;
import retrofit2.Response;

import java.io.IOException;

public class ChatRaum_Controller extends ETeach_Controller {
    private Response<ChatNachricht[]> response;
    private Response<ChatNachricht> responseSingle;
    private ObservableList<ChatNachricht> chatNachrichten;
    private Veranstaltung veranstaltung;

    public static ChatRaumEndpoint getChatRaumEndpoint() {
        return RetrofitClient.getClient().create(ChatRaumEndpoint.class);
    }

    ScheduledService<Void> scheduledService = new ScheduledService<>() {

        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() throws Exception {
                    ChatNachricht[] chat;
                    try {
                        response = getChatRaumEndpoint().getChatByVeranstaltung(MainApp.getAuthHeader(), veranstaltung.getId()).execute();
                    } catch (
                            IOException e) {
                        e.printStackTrace();
                    }
                    chat = response.body();
                    chatNachrichten = FXCollections.observableArrayList(chat);
                    try
                    {
                        Context.chat.setItems(chatNachrichten);
                    }
                    catch(Exception ignored)
                    {}
                    return null;
                }
            };
        }
    };

    // 1. Schritt watchChatByVeranstaltung mit Veranstaltung aufrufen
    // 2. Schritt ObservableList<ChatNachricht> chatNachrichten benutzen für Chat Nachrichten
    public ObservableList<ChatNachricht> watchChatByVeranstaltung(Veranstaltung veranstaltung) {
        this.veranstaltung = veranstaltung;
        scheduledService.setPeriod(Duration.seconds(2.0));
        scheduledService.start();
        return chatNachrichten;
    }
    // 3. Schritt Beim verlassen des Chats unwatchChatByVeranstaltung aufrufen

    public void unwatchChatByVeranstaltung() {
        if (scheduledService.getState() == Worker.State.RUNNING) {
            scheduledService.cancel();
        }
        chatNachrichten = null;
        this.veranstaltung = null;
    }

    public ChatNachricht sendMessage(ChatNachricht chatNachricht, Veranstaltung veranstaltung, Nutzer nutzer) throws IOException {
        responseSingle = getChatRaumEndpoint().createChatNachricht(MainApp.getAuthHeader(), veranstaltung.getId(), nutzer.getId(), chatNachricht).execute();
        return responseSingle.body();
    }

    public void clearChatRaum(Veranstaltung veranstaltung) throws IOException {
        response = getChatRaumEndpoint().clearChatByVeranstaltung(MainApp.getAuthHeader(), veranstaltung.getId()).execute();
    }

    public void deleteChatNachricht(ChatNachricht chatNachricht) throws IOException {
        responseSingle = getChatRaumEndpoint().deleteChatNachricht(MainApp.getAuthHeader(), chatNachricht.getId()).execute();
    }
}