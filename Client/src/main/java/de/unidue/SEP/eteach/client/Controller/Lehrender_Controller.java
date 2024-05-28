package de.unidue.SEP.eteach.client.Controller;

import de.unidue.SEP.eteach.client.Entities.Lehrender;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class Lehrender_Controller extends ETeach_Controller {
    private Response<Lehrender[]> responseMulti;
    private Response<Lehrender> responseSingle;

    public Lehrender getLehrenderById(Integer id) throws IOException {
        responseSingle = getLehrenderEndpoint().getLehrenderById(MainApp.getAuthHeader(), id).execute();
        return responseSingle.body();
    }

    public ObservableList<Lehrender> getLehrenderList() {
        Lehrender[] eList;
        try {
            responseMulti = getLehrenderEndpoint().getAllLehrender(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        eList = responseMulti.body();
        ObservableList<Lehrender> lehrenderList = FXCollections.observableArrayList(eList);
        return lehrenderList;
    }

    public void editLehrender(Lehrender lehrenderEdited) throws IOException {
        responseSingle = getLehrenderEndpoint().editLehrender(MainApp.getAuthHeader(), lehrenderEdited.getId(), lehrenderEdited).execute();
    }

    public void saveLehrender(Lehrender lehrender) throws IOException {
        responseSingle = getLehrenderEndpoint().createLehrender(MainApp.getAuthHeader(), lehrender).execute();
    }

    public void deleteLehrender(Lehrender lehrender) throws IOException {
        responseSingle = getLehrenderEndpoint().deleteLehrender(MainApp.getAuthHeader(), lehrender.getId()).execute();
    }

}
