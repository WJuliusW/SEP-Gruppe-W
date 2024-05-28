package de.unidue.SEP.eteach.client.ViewController;


import de.unidue.SEP.eteach.client.Controller.Veranstaltung_Controller;
import de.unidue.SEP.eteach.client.Entities.Semester;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.Entities.Veranstaltungstyp;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import rx.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LehrveranstaltungListeController extends ETeach_Controller implements Initializable
{

    @FXML
    private Button btnSuche;

    @FXML
    private Button btnNeuLV;

    @FXML
    private Button btnBeitreten;

    @FXML
    private TextField txfSuchanfrage;

    @FXML
    private Button btnZurueck;
    @FXML
    private TableView<Veranstaltung> tbvLV;
    @FXML
    private TableColumn<Veranstaltung,String> tbcTitel;
    @FXML
    private TableColumn<Veranstaltung,String> tbcBeschreibung;
    @FXML
    private TableColumn<Veranstaltung, Semester> tbcSemester;
    @FXML
    private TableColumn<Veranstaltung, Veranstaltungstyp> tbcTyp;

    @FXML
    void BeitretenPressed() throws IOException {
        if(currentStudent != null) {
            Veranstaltung v = tbvLV.getSelectionModel().getSelectedItem();
            //Veränderung der eigenen LVs
            currentStudent.getVeranstaltungList().add(v);
            getStudentEndpoint().createStudent(MainApp.getAuthHeader(), getCurrentStudent()).execute();
        }
        else
        {
            Veranstaltung v = tbvLV.getSelectionModel().getSelectedItem();
            //Veränderung der eigenen LVs
            currentLehrender.getVeranstaltungList().add(v);
            getLehrenderEndpoint().createLehrender(MainApp.getAuthHeader(), getCurrentLehrender()).execute();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        Veranstaltung_Controller controller = new Veranstaltung_Controller();
        ObservableList<Veranstaltung> list = controller.getVeranstaltungList();
        tbcTitel.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        tbcTyp.setCellValueFactory(new PropertyValueFactory<>("Typ der Veranstaltung"));
        tbcBeschreibung.setCellValueFactory(new PropertyValueFactory<>("Beschreibung"));
        tbcSemester.setCellValueFactory(new PropertyValueFactory<>("Semester"));
        tbvLV.setItems(list);

    }

    @FXML
    void SuchePressed()
    {
        tbcTitel.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        tbcTyp.setCellValueFactory(new PropertyValueFactory<>("Typ der Veranstaltung"));
        tbcBeschreibung.setCellValueFactory(new PropertyValueFactory<>("Beschreibung"));
        tbcSemester.setCellValueFactory(new PropertyValueFactory<>("Semester"));


        Veranstaltung_Controller controller = new Veranstaltung_Controller();
        ObservableList<Veranstaltung> list = controller.getVeranstaltungList();
        ObservableList<Veranstaltung> list1 = controller.getVeranstaltungList();
        tbvLV.getItems().clear();
        for(Veranstaltung v: list)
//        list1 = (ObservableList<Veranstaltung>) list.stream().filter(t -> t.getTitel().contains(txfSuchanfrage.getText()));
        tbvLV.setItems(list1);
    }

    @FXML
    void zurueckPressed()
    {
        changePane("veranstaltung_collection.fxml");
    }

    public void tbvClicked(MouseEvent event)
    {

    }
}


/*
 columnBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        columnAnmerkungen.setCellValueFactory(new PropertyValueFactory<>("anmerkungen"));
        tblMaterial.setItems(getMaterialList());
 */
