package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Material_Controller;
import de.unidue.SEP.eteach.client.Controller.Veranstaltung_Controller;
import de.unidue.SEP.eteach.client.Entities.Material;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import retrofit2.Response;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Material_Single_Controller extends Material_Controller implements Initializable {
    private Veranstaltung_Controller veranstaltung_controller = new Veranstaltung_Controller();
    private Material currentMaterial;
    Response<Material> response;


    @FXML
    private Material_List_Controller material_list_controller;

    @FXML
    private Label lblHeadline;

    @FXML
    private TextField txtBezeichnung;

    @FXML
    private TextArea txaAnmerkungen;

    @FXML
    private Button btnMaterialHinzufuegen;

    @FXML
    private Button btnAbbruch;

    @FXML
    private Button btnSpeichern;

    @FXML
    private Label lblDateiname;

    private File selectedFile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(currentMode.equals("Edit")) {
            this.txtBezeichnung.setText(getCurrentMaterial().getBezeichnung());
            this.txaAnmerkungen.setText(getCurrentMaterial().getAnmerkungen());
        }
    }

    @FXML
    void handleAbbruchButtonAction(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void handleMaterialHinzufuegenButtonAction(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);
        if (checkPdf(selectedFile.getName())){
            lblDateiname.setText(selectedFile.getName());
        }
        else{
            showInfoWindows("Falsches Format", "Es werden nur PDF dateien akzeptiert");
        }
    }

    @FXML
    void handleSpeichernButtonAction(ActionEvent event) throws IOException {
        if (selectedFile != null) {
            Material materialPersistent;
            Material material = new Material();
            material.setAnmerkungen(txaAnmerkungen.getText());
            material.setBezeichnung(selectedFile.getName());
            material.setMaterialString(Base64.encodeBase64String(FileUtils.readFileToByteArray(selectedFile)));
            materialPersistent = saveMaterial(material);
            setCurrentVeranstaltung(
                    getMaterialEndpoint().veranstaltungsmaterialHinzufuegen(
                            MainApp.getAuthHeader(), materialPersistent.getId(), getCurrentVeranstaltung().getId()).execute().body());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            showInfoWindows("Material fehlt", "Bitte eine PDF anhaengen");
        }
    }
}
