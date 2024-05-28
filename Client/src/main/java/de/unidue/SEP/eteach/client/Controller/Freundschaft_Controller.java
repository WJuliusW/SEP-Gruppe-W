package de.unidue.SEP.eteach.client.Controller;


import de.unidue.SEP.eteach.client.Endpoints.FreundschaftEndpoint;
import de.unidue.SEP.eteach.client.Entities.Freundschaft;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.RetrofitClient;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class Freundschaft_Controller extends ETeach_Controller {
    private Response<Freundschaft[]> response;
    private Response<Freundschaft> responseSingle;

    public static FreundschaftEndpoint getFreundschaftEndpoint() {
        return RetrofitClient.getClient().create(FreundschaftEndpoint.class);
    }

    public ObservableList<Freundschaft> getFreundschaftList() {
        Freundschaft[] eList;
        try {
            response = getFreundschaftEndpoint().getAllFreundschaft(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Freundschaft> freundschaftList = FXCollections.observableArrayList(eList);
        return freundschaftList;
    }

    public Freundschaft editFreundschaft(Freundschaft freundschaftEdited) throws IOException {

        return getFreundschaftEndpoint().editFreundschaft(MainApp.getAuthHeader(),freundschaftEdited,freundschaftEdited.getId()).execute().body();
    }

    public void saveFreundschaft(Freundschaft freundschaft) throws IOException {
        responseSingle = getFreundschaftEndpoint().createFreundschaft(MainApp.getAuthHeader(), freundschaft).execute();
    }

    public void deleteFreundschaft(Freundschaft freundschaft) throws IOException {
        responseSingle = getFreundschaftEndpoint().deleteFreundschaft(MainApp.getAuthHeader(), freundschaft.getId()).execute();
    }
}
