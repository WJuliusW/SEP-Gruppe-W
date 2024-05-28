package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Nutzer_Controller;
import de.unidue.SEP.eteach.client.Controller.Veranstaltung_Controller;
import de.unidue.SEP.eteach.client.Entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Veranstaltung_List_Controller extends Veranstaltung_Controller implements Initializable {

    @FXML
    private TextField txfSuchanfrage;

    @FXML
    private Button btnSuche;

    @FXML
    private Button btnVeranstaltungBeitreten;

    @FXML
    private TableView<Veranstaltung> tbvLV;

    @FXML
    private TableColumn<Veranstaltung, String> tbcTitel;

    @FXML
    private TableColumn<Veranstaltung, Veranstaltungstyp> tbcTyp;

    @FXML
    private TableColumn<Veranstaltung, String> tbcBeschreibung;

    @FXML
    private TableColumn<Veranstaltung, Semester> tbcSemester;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tbcTitel.setCellValueFactory(new PropertyValueFactory<>("titel"));
        tbcTyp.setCellValueFactory(new PropertyValueFactory<>("veranstaltungstyp"));
        tbcBeschreibung.setCellValueFactory(new PropertyValueFactory<>("beschreibung"));
        tbcSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        tbvLV.setItems(FXCollections.observableArrayList(getVeranstaltungList()));
    }

    public String titel;

    @FXML
    void SuchePressed()
    {

        FilteredList<Veranstaltung> list =new FilteredList<> (getVeranstaltungList());
        list.setPredicate(t -> t.getTitel().contains(txfSuchanfrage.getText()));
        tbvLV.setItems(list);
        /*
        ObservableList<Veranstaltung> list1 = null;

        for(ObservableList<Veranstaltung> v : titel)
        {
            if(Veranstaltung t ->t.getTitel().contains(txfSuchanfrage.getText()))
            {
                list1.add(t);
            }
        }
        tbvLV.getItems().clear();
        list1 = (ObservableList<Veranstaltung>) list.stream().filter(t -> t.getTitel().contains(txfSuchanfrage.getText()));
        tbvLV.setItems(list1);

         */
    }

    @FXML
    void handleVeranstaltungBeitretenButtonAction(ActionEvent event) throws IOException {
        Nutzer_Controller nutzerController = new Nutzer_Controller();

        ObservableList<Veranstaltung> selectedVeranstaltung = tbvLV.getSelectionModel().getSelectedItems();
        for (Veranstaltung veranstaltung : selectedVeranstaltung) {
            nutzerController.nutzerInVeranstaltungEinschreiben(currentNutzer.getId(), veranstaltung.getId());
        }
        String titel="Erfolgreich hinzugefuegt...";
        String nachricht="Sie sind der Veranstaltung " + selectedVeranstaltung.get(0).getTitel() + " erfolgreich beigetreten.";
        showInfoWindows(titel, nachricht);

    }
}


