package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Material_Controller;
import de.unidue.SEP.eteach.client.Entities.Material;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Material_List_Controller extends Material_Controller implements Initializable {

    @FXML
    private Veranstaltung_Collection_Controller veranstaltung_collection_controller;
    private Material currentMaterial;

//    public Material getCurrentMaterial() {
//        return currentMaterial;
//    }
//
//    public void setCurrentMaterial(Material currentMaterial) {
//        this.currentMaterial = currentMaterial;
//    }

    @FXML
    private Label lblHeadline;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnEdit;

    @FXML
    private TableView<Material> tblMaterial;

    @FXML
    private TableColumn<Material, String> columnBezeichnung;

    @FXML
    private TableColumn<Material, String> columnAnmerkungen;

    @FXML
    void handleAddButtonAction(ActionEvent event) throws IOException {
//        if (getCurrentVeranstaltung() != null) {
//            Material currentMaterial = new Material(getCurrentVeranstaltung());
//            getCurrentVeranstaltung().getMaterialList().add(currentMaterial);
//            openModalForm("material_single","Neues Material anlegen...");
//        } else {
//            showInfoWindows("Fehler","Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
//        }
    }

    @FXML
    void handleDeleteButtonAction(ActionEvent event) throws IOException {
        deleteMaterial(tblMaterial.getSelectionModel().getSelectedItem());
        tblMaterial.setItems(getMaterialList());
    }

    @FXML
    void handleEditButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //        tblMaterial.getSelectionModel().setSelectionMode(
//                SelectionMode.MULTIPLE
//        );
        columnBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        columnAnmerkungen.setCellValueFactory(new PropertyValueFactory<>("anmerkungen"));
        tblMaterial.setItems(getMaterialList());
    }
}
