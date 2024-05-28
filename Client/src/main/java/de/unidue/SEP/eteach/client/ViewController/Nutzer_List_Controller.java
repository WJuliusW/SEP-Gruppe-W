package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Nutzer_Controller;
import de.unidue.SEP.eteach.client.Controller.Student_Controller;
import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.Student;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Nutzer_List_Controller extends Nutzer_Controller implements Initializable {
    Response<Nutzer[]> response;
    private static Nutzer[] nutzers;
    private ObservableList<Nutzer> data;

    @FXML
    private Label lblHeadline;
    @FXML
    private Button btnAdd;
    @FXML
    private TableView<Student> tblNutzer;
    @FXML
    private TableColumn<Student, String> columnVorname;
    @FXML
    private TableColumn<Student, String> columnNachname;
    @FXML
    private TableColumn<Student, String> columnMatrikelnr;
    @FXML
    private TextField txfSuche;
    @FXML
    private Button btnSuche;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblNutzer.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        columnVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        columnNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        columnMatrikelnr.setCellValueFactory(new PropertyValueFactory<>("matrikelnummer"));
        Student_Controller controller = new Student_Controller();
        tblNutzer.setItems(controller.getStudentList());
    }

    @FXML
    void handleAddButtonAction(ActionEvent event) throws IOException {
        ObservableList<Student> selectedNutzer = tblNutzer.getSelectionModel().getSelectedItems();
        int anzahlHinzugefuegt = 0;
        for (Nutzer nutzer : selectedNutzer) {
            nutzerInVeranstaltungEinschreiben(nutzer.getId(), getCurrentVeranstaltung().getId());
            anzahlHinzugefuegt++;
        }
        String titel="Erfolgreich hinzugefuegt...";
        String nachricht="Der Veranstaltung " + getCurrentVeranstaltung().getTitel() + " wurden" + anzahlHinzugefuegt + " Teilnehmer hinzugefuegt.";
        showInfoWindows(titel, nachricht);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        //Veranstaltung_Collection_Controller controller = new Veranstaltung_Collection_Controller();
        //controller.tblTeilnehmer.refresh();
    }


    public void handleSuche(ActionEvent event)
    {
        Student_Controller controller = new Student_Controller();
        FilteredList<Student> list = new FilteredList<> (controller.getStudentList());
        if(nurZahlen(txfSuche.getText())) {
            list.setPredicate(t -> t.getMatrikelnummer() == (Integer.parseInt(txfSuche.getText())));
            tblNutzer.setItems(list);
        }
        else if (nurBuchstaben(txfSuche.getText()))
        {
            list.setPredicate(t -> t.getNachname().contains(txfSuche.getText()));
            tblNutzer.setItems(list);
        }
    }
}

