package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Nutzer_Controller;
import de.unidue.SEP.eteach.client.Controller.Veranstaltung_Controller;
import de.unidue.SEP.eteach.client.Entities.Semester;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.Entities.Veranstaltungstyp;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Veranstaltung_Single_Controller extends Veranstaltung_Controller implements Initializable {
    private Veranstaltung veranstaltung;

    @FXML
    private Label lblHeadline;

    @FXML
    private TextField txtTitel;

    @FXML
    private ComboBox<Semester> comboSemester;

    @FXML
    private ComboBox<Veranstaltungstyp> comboVeranstaltungstyp;

    @FXML
    private TextArea txsAnmerkungen;

    @FXML
    private Label lblDateianhang;

    @FXML
    private Button btnAbbruch;

    @FXML
    private Button btnSpeichern;

    private File selectedFile;
    public String veranstaltungstyp;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboVeranstaltungstyp.setItems(FXCollections.observableArrayList(Veranstaltungstyp.values()));
        comboVeranstaltungstyp.setValue(getCurrentVeranstaltung().getVeranstaltungstyp());
        comboSemester.setItems(FXCollections.observableArrayList(Semester.values()));
        comboSemester.setValue(getCurrentVeranstaltung().getSemester());
        txtTitel.setText(getCurrentVeranstaltung().getTitel());
        txsAnmerkungen.setText(getCurrentVeranstaltung().getBeschreibung());
        if(currentStudent != null)
        {
            comboVeranstaltungstyp.setItems(null);
            comboVeranstaltungstyp.setItems(FXCollections.observableArrayList(Veranstaltungstyp.PROJEKTGRUPPE));
        }
    }

    @FXML
    void handleAbbruchButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleSpeichernButtonAction(ActionEvent event) throws IOException {
        FilteredList<Veranstaltung> list = new FilteredList<> (getVeranstaltungList());
        list.setPredicate(t -> t.getTitel().equalsIgnoreCase(txtTitel.getText()));
        if (list.isEmpty()) {
//            andere möglichkeit die einzigartigkeit des titels zu prüfen (waruzm auch immer gehts aktuell nicht mehr)
//        System.out.println(getVeranstaltungEndpoint().getVeranstaltungByTitle(MainApp.getAuthHeader(), txtTitel.getText()));
//        System.out.println(txtTitel.getText());
//        String veranstaltungsTitel = txtTitel.getText();
//        if(getVeranstaltungByTitle(txtTitel.getText()) == null){
//          if(getVeranstaltungByTitle(veranstaltungsTitel) == null){
//          if(getVeranstaltungEndpoint().getVeranstaltungByTitle(MainApp.getAuthHeader(), veranstaltungsTitel) == null){
            Nutzer_Controller nutzerController = new Nutzer_Controller();
            veranstaltung = new Veranstaltung();
            Veranstaltung persistentVeranstaltung;
            veranstaltung.setTitel(txtTitel.getText());
            veranstaltung.setBeschreibung(txsAnmerkungen.getText());
            veranstaltung.setVeranstaltungstyp(comboVeranstaltungstyp.getSelectionModel().getSelectedItem());
            veranstaltung.setSemester(comboSemester.getSelectionModel().getSelectedItem());
            //Chatraum hinzufügen


            // durch den nächsten schritt wird die veranstaltung auf dem Server gespeichert und das gespeicherte objekt mit persistent veranstaltung festgehalten.
            persistentVeranstaltung = saveVeranstaltung(veranstaltung);
            // Da die Veranstaltung ohne Organisator abgespeichert wurde wird im nächsten schritt der Organisator hinzugefügt.
            veranstaltungsorganisatorFestlegen(persistentVeranstaltung.getId(), getCurrentNutzer().getId());
            nutzerController.nutzerInVeranstaltungEinschreiben(getCurrentNutzer().getId(), persistentVeranstaltung.getId());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

            //Veranstaltung Collection Update Combobox
            //Veranstaltung_Collection_Controller controller = new Veranstaltung_Collection_Controller();
            //controller.comboVeranstaltungen.getItems().clear();
            //controller.comboVeranstaltungen.setItems(getVeranstaltungList());
        }
        else {
            showInfoWindows("Veranstaltung existiert bereits", "Der Veranstaltungstitel muss eindeutig sein");
        }

    }

    public void csvDragDropped(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
        System.out.println("onDragDropped");
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            for (File file : db.getFiles()) {
                String absolutePath = file.getAbsolutePath();
                System.out.println(absolutePath);
                // selectedFile = file; //muss zur momentanen Umgebung hinzugeügt werden
                //die csv datei ist als file wird im code bereit gestellt, ihr müsst sie nur noch versenden
            }
        }
        event.consume();
    }

    public void csvDragOver(DragEvent dragEvent) {
        System.out.println("onDragOver");

        Dragboard db = dragEvent.getDragboard();
        if (db.hasFiles()) {
            dragEvent.acceptTransferModes(TransferMode.COPY);
        }

        dragEvent.consume();
    }

    public void btnCSVPressed(ActionEvent actionEvent) {
     /*Evtl in einen zusätzlichen button das man nicht nur drag and drop zur auswahl hat.
        FileChooser CsvChooser = new FileChooser();
        selectedFile = CsvChooser.showOpenDialog(null);

      */
        if (selectedFile != null) {
            //Senden der selected File an den Server
        }
    }
}

