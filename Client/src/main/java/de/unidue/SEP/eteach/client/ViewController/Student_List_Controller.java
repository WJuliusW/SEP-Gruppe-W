package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Student_Controller;
import de.unidue.SEP.eteach.client.Entities.Student;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import retrofit2.Response;

import java.net.URL;
import java.util.ResourceBundle;

public class Student_List_Controller extends Student_Controller implements Initializable {
    Response<Student[]> response;
    private static Student[] students;
    private ObservableList<Student> data;

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
        tblStudenten.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
        columnVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        columnNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        tblStudenten.setItems(getStudentList());
    }

    public void loadData() {
        tblStudenten.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );
//        data = FXCollections.observableArrayList(getCurrentVeranstaltung().getVeranstaltungsteilnehmerList());
        tblStudenten.setItems(data);
        columnVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        columnNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
    }

}

