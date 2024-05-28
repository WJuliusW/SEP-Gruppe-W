package de.unidue.SEP.eteach.client.Controller;

import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class Nutzer_Controller extends ETeach_Controller {

    private Response<Nutzer[]> nutzers;
    private Response<Nutzer> response;
    private ObservableList<Nutzer> nutzerList;
    private Response<Veranstaltung[]> veranstaltungen;
    private ObservableList<Veranstaltung> meineVeranstaltungen;

    public ObservableList<Nutzer> getNutzerList() {
        Nutzer[] nutzerArray;
        try {
            nutzers = getNutzerEndpoint().getAllNutzer(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        nutzerArray = nutzers.body();
        nutzerList = FXCollections.observableArrayList(nutzerArray);
        return nutzerList;
    }

    public ObservableList<Veranstaltung> meineVeranstaltungen(Nutzer nutzer) {
        Veranstaltung[] veranstaltungenArray;
        try {
            veranstaltungen = getVeranstaltungEndpoint().getVeranstaltungenByTeilnehmer(MainApp.getAuthHeader(), nutzer.getId()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        veranstaltungenArray = veranstaltungen.body();
        meineVeranstaltungen = FXCollections.observableArrayList(veranstaltungenArray);
        return meineVeranstaltungen;
    }

    public Nutzer getNutzerById(Integer id) throws IOException {
        response = getNutzerEndpoint().getNutzerById(MainApp.getAuthHeader(), id).execute();
        return response.body();
    }

    public Nutzer getNutzerByKeyEmailPass(String key) throws IOException{
        response = getNutzerEndpoint().getNutzerByKeyEmailPass(MainApp.getAuthHeader(), key).execute();
        return response.body();
    }

    public Nutzer getNutzerByEmail(String email) throws IOException{
        response = getNutzerEndpoint().getNutzerByEmail(MainApp.getAuthHeader(), email).execute();
        return response.body();
    }

    /*
    public Nutzer getNutzerByKeyMatPass(String key) throws IOException{
        response = getNutzerEndpoint().getNutzerByKeyMatPass(MainApp.getAuthHeader(), key).execute();
        return response.body();
    }

     */
    public void editNutzer(Nutzer nutzerEdited) throws IOException {
        response = getNutzerEndpoint().editNutzer(MainApp.getAuthHeader(), nutzerEdited.getId()).execute();
    }

    public void nutzerInVeranstaltungEinschreiben(Integer nutzerId, Integer veranstaltungId) throws IOException {
        response=getNutzerEndpoint().nutzerInVeranstaltungEinschreiben(MainApp.getAuthHeader(),nutzerId, veranstaltungId).execute();
    }

    public void nutzerAusVeranstaltungEntfernen(Integer nutzerId, Integer veranstaltungId) throws IOException {
        response=getNutzerEndpoint().nutzerAusVeranstaltungEntfernen(MainApp.getAuthHeader(),nutzerId, veranstaltungId).execute();
    }

    public void saveNutzer(Nutzer nutzer) throws IOException {
        response = getNutzerEndpoint().createNutzer(MainApp.getAuthHeader(), nutzer).execute();
    }

    public void deleteNutzer(Nutzer nutzer) throws IOException {
        response = getNutzerEndpoint().deleteNutzer(MainApp.getAuthHeader(), nutzer.getId()).execute();
    }

}
