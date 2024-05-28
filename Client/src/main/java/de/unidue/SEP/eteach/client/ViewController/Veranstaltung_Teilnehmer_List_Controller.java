package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Student_Controller;
import de.unidue.SEP.eteach.client.Entities.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import retrofit2.Response;

import java.net.URL;
import java.util.ResourceBundle;

public class Veranstaltung_Teilnehmer_List_Controller extends Student_Controller implements Initializable {
    Response<Student[]> response;
    @FXML
    private Label lblHeadline;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnEdit;
    @FXML
    private TableView<Student> tblStudenten;
    @FXML
    private TableColumn<Student, String> columnVorname;
    @FXML
    private TableColumn<Student, String> columnNachname;

    @FXML
    void handleAddButtonAction(ActionEvent event) {
        openModalForm("student_list_large", "Auswahl Studenten");
    }

    @FXML
    void handleDeleteButtonAction(ActionEvent event) {

    }

    @FXML
    void handleEditButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        tblStudenten.getSelectionModel().setSelectionMode(
//                SelectionMode.MULTIPLE
//        );
//        columnVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
//        columnNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
//
//        ObservableList<Student> observableList= FXCollections.observableList(getCurrentVeranstaltung().getVeranstaltungsteilnehmerList());
//        tblStudenten.setItems(observableList);
    }

    public void setTable(){
//        ObservableList<Student> studentObservableList = FXCollections.observableArrayList(getCurrentVeranstaltung().getVeranstaltungsteilnehmerList());
//        tblStudenten.setItems(studentObservableList);
    }

}

