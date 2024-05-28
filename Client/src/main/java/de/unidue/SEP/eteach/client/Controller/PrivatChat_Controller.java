package de.unidue.SEP.eteach.client.Controller;

import de.unidue.SEP.eteach.client.Entities.Abgabe;
import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.PrivatNachricht;
import de.unidue.SEP.eteach.client.Entities.Quiz;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class PrivatChat_Controller extends ETeach_Controller
{

    public ObservableList<PrivatNachricht> getNutzerList(Nutzer nutzer) {
        PrivatNachricht[] privatNachrichten = {};
        try {
            privatNachrichten = getPrivatNachrichtenEndpoint().getPrivatNachrichtenByEmpfaenger(MainApp.getAuthHeader(), nutzer.getId()).execute().body();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(privatNachrichten);
    }


    public ObservableList<PrivatNachricht> getPrivatNachrichtenByAbsenderOrEmpfaenger(Nutzer absender, Nutzer empfaenger) {
        PrivatNachricht[] privatNachrichten = {};
        try {
            privatNachrichten = getPrivatNachrichtenEndpoint().getPrivatNachrichtenByAbsenderOrEmpfaenger(MainApp.getAuthHeader(), absender.getId(), empfaenger.getId()).execute().body();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList(privatNachrichten);
    }

    public void createPrivatNachricht(Nutzer absender, Nutzer empfaenger, PrivatNachricht privatNachricht) throws IOException {

        getPrivatNachrichtenEndpoint().createPrivatNachricht(MainApp.getAuthHeader(), absender.getId(), empfaenger.getId(), privatNachricht).execute();
    }

    public void deletePrivatNachricht(PrivatNachricht privatNachricht) throws IOException {

        getPrivatNachrichtenEndpoint().deletePrivatNachricht(MainApp.getAuthHeader(), privatNachricht.getId()).execute();

    }


    //JUlius

    private Response<PrivatNachricht[]> response;
    private Response<PrivatNachricht> responseSingle;

    public ObservableList<PrivatNachricht> getPrivatNachrichtList() {
       PrivatNachricht[] eList;
        try {
            response = getPrivatNachrichtenEndpoint().getAllPrivatNachricht(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<PrivatNachricht> privatNachrichtList = FXCollections.observableArrayList(eList);
        return privatNachrichtList;
    }

}
