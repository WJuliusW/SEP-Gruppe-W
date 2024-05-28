package de.unidue.SEP.eteach.client.Controller;


import de.unidue.SEP.eteach.client.Endpoints.AbgabeEndpoint;
import de.unidue.SEP.eteach.client.Entities.Abgabe;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.RetrofitClient;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class Abgabe_Controller extends ETeach_Controller {
    private Response<Abgabe[]> response;
    private Response<Abgabe> responseSingle;

    public static AbgabeEndpoint getAbgabeEndpoint() {
        return RetrofitClient.getClient().create(AbgabeEndpoint.class);
    }

    public ObservableList<Abgabe> getAbgabeList() {
        Abgabe[] eList;
        try {
            response = getAbgabeEndpoint().getAllAbgabe(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Abgabe> abgabeList = FXCollections.observableArrayList(eList);
        return abgabeList;
    }

    public Abgabe editAbgabe(Abgabe abgabeEdited) throws IOException {

        return getAbgabeEndpoint().editAbgabe(MainApp.getAuthHeader(),abgabeEdited,abgabeEdited.getId()).execute().body();
    }

    public void saveAbgabe(Abgabe abgabe) throws IOException {
        responseSingle = getAbgabeEndpoint().createAbgabe(MainApp.getAuthHeader(), abgabe).execute();
    }

    public void deletAbgabe(Abgabe abgabe) throws IOException {
        responseSingle = getAbgabeEndpoint().deleteAbgabe(MainApp.getAuthHeader(), abgabe.getId()).execute();
    }
}
