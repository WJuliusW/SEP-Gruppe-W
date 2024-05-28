package de.unidue.SEP.eteach.client.Controller;

import de.unidue.SEP.eteach.client.Endpoints.VeranstaltungEndpoint;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.RetrofitClient;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class Veranstaltung_Controller extends ETeach_Controller {
    private Response<Veranstaltung[]> response;
    private Response<Veranstaltung> responseSingle;

    public static VeranstaltungEndpoint getVeranstaltungEndpoint() {
        return RetrofitClient.getClient().create(VeranstaltungEndpoint.class);
    }

    public ObservableList<Veranstaltung> getVeranstaltungList() {
        Veranstaltung[] eList;
        try {
            response = getVeranstaltungEndpoint().getAllVeranstaltung(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = response.body();
        ObservableList<Veranstaltung> veranstaltungList = FXCollections.observableArrayList(eList);
        return veranstaltungList;
    }

    public void veranstaltungsorganisatorFestlegen(Integer veranstaltungId, Integer lehrenderId) throws IOException {
        responseSingle = getVeranstaltungEndpoint().veranstaltungsorganisatorFestlegen(MainApp.getAuthHeader(), veranstaltungId, lehrenderId).execute();
    }

    public Veranstaltung getVeranstaltungByTitle(String title) throws IOException {
        responseSingle = getVeranstaltungEndpoint().getVeranstaltungByTitle(MainApp.getAuthHeader(), title).execute();
        return responseSingle.body();
    }

    public Veranstaltung editVeranstaltung(Veranstaltung veranstaltungEdited) throws IOException {
        responseSingle = getVeranstaltungEndpoint().editVeranstaltung(MainApp.getAuthHeader(), veranstaltungEdited.getId()).execute();
        return responseSingle.body();
    }

    public Veranstaltung saveVeranstaltung(Veranstaltung veranstaltung) throws IOException {
        responseSingle = getVeranstaltungEndpoint().createVeranstaltung(MainApp.getAuthHeader(), veranstaltung).execute();
        return responseSingle.body();
    }

    public void deleteVeranstaltung(Veranstaltung veranstaltung) throws IOException {
        responseSingle = getVeranstaltungEndpoint().deleteVeranstaltung(MainApp.getAuthHeader(), veranstaltung.getId()).execute();
    }
}
