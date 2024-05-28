package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Lehrender_Controller;
import de.unidue.SEP.eteach.client.Controller.Nutzer_Controller;
import de.unidue.SEP.eteach.client.Entities.*;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Register_Lehrender_New_Controller extends Lehrender_Controller implements Initializable {

    @FXML
    private AnchorPane paneRootPane;

    @FXML
    private ImageView imgProfilbild;

    @FXML
    private Button btnProfilbildAendern;

    @FXML
    private TextField txtImagePfad;

    @FXML
    private TextField txtVorname;

    @FXML
    private TextField txtNachname;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPasswort;

    @FXML
    private Button btnZuruecksetzen;

    @FXML
    private Button btnZuruecksetzen1;

    @FXML
    private Button btnAlsStudiRegistrieren;

    @FXML
    private Button btnSpeichern;

    @FXML
    private Label lblAdresse1;

    @FXML
    private ComboBox<Studienfach> comboForschungsgebiet;

    @FXML
    private TextField txtStrasse;

    @FXML
    private TextField txtPostleitzahl;

    @FXML
    private TextField txtHausnummer;

    @FXML
    private Label lblNachname;

    @FXML
    private Label lblVorname;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPasswort1;

    @FXML
    private Label lblStudienfach;

    @FXML
    private Label lblAdresse2;

    @FXML
    private Label lblAdresse3;

    @FXML
    private Label lblAdresse4;

    @FXML
    private ComboBox<Lehrstuhl> comboLehrstuhl;

    @FXML
    private Label lblStudienfach1;

    @FXML
    private PasswordField txtPasswortWiederholung;

    @FXML
    private Label lblPasswort2;

    @FXML
    private Label lblPasswort;

    @FXML
    private Label lblVorname111;

    @FXML
    private Label lblAdresse5;

    @FXML
    private TextField txtOrt;

    private File selectedFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboForschungsgebiet.getItems().addAll(Studienfach.values());
        comboLehrstuhl.getItems().addAll(Lehrstuhl.values());

    }


    @FXML
    void dragPBDropped(DragEvent event) {
        event.acceptTransferModes(TransferMode.COPY);
        System.out.println("onDragDropped");
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            for (File file : db.getFiles()) {
                String absolutePath = file.getAbsolutePath();
                System.out.println(absolutePath);
                selectedFile = file; //muss zur momentanen Umgebung hinzugeügt werden
                Image image = new Image(file.toURI().toString());
            /*  ImageView dbImageView = new ImageView();
                dbImageView.setFitWidth(200);
                dbImageView.setFitHeight(100);
                dbImageView.setImage(image);
                boxPB.getChildren().add(dbImageView);
             */
                imgProfilbild.setImage(image);
            }
        }
        event.consume();
    }

    @FXML
    void dragPBOver(DragEvent event) {
        System.out.println("onDragOver");

        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }

        event.consume();
    }

    @FXML
    void handleProfilbildAendernAction(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image imageView = new Image(selectedFile.toURI().toString());
            imgProfilbild.setImage(imageView);
            System.out.println(selectedFile.getPath());
        }

    }

    @FXML
    void handleSpeicherButtonAction(ActionEvent event) throws IOException {
        Nutzer_Controller controllerNutzer = new Nutzer_Controller();
        FilteredList<Nutzer> list = new FilteredList<>(controllerNutzer.getNutzerList());
        list.setPredicate(t -> t.getEmail().equals(txtEmail.getText()));
        if(list.isEmpty())
        {


            if (txtPasswort.getText().equals(txtPasswortWiederholung.getText())
                    && nurBuchstaben(txtNachname.getText())
                    && nurBuchstaben(txtVorname.getText())
                    && nurBuchstaben(txtStrasse.getText())
                    && nurBuchstaben(txtOrt.getText())
                    && selectedFile != null
                    && nurZahlen(txtHausnummer.getText())
                    && nurZahlen(txtPostleitzahl.getText())
                    && legitMailadresse(txtEmail.getText())) {
                //Neuen Lehrenden anlegen
                String photoString = Base64.encodeBase64String(FileUtils.readFileToByteArray(selectedFile));
                Lehrender lehrender = new Lehrender();
                lehrender.setNachname(txtNachname.getText());
                lehrender.setVorname(txtVorname.getText());
                lehrender.setEmail(txtEmail.getText());
                lehrender.setPasswort(txtPasswort.getText());
                lehrender.setPhotoString(photoString);
                lehrender.setLehrstuhl(comboLehrstuhl.getValue());
                lehrender.setForschungsgebiet(comboForschungsgebiet.getValue());
                lehrender.setAdresse(new Adresse(txtStrasse.getText(), Integer.parseInt(txtHausnummer.getText()), txtOrt.getText(), Integer.parseInt(txtPostleitzahl.getText())));


                saveLehrender(lehrender);


                // hier muss evtl try catch für szenen wechsel hin
                // changeStage("LOGINPage.fxml");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("LOGINPage.fxml"));
                    Parent root = loader.load();
                    Scene login = new Scene(root, 600, 600);
                    //Auffüllen der Werte

                    LogInController controller = loader.getController();

                    controller.txfMailField.setText(txtEmail.getText());
                    controller.pwfPasswordField.setText(txtPasswort.getText());
                    MainApp.window.setScene(login);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // hier muss error display bei Falscher eingabe gehandelt werden. Oder Textfelder Rot einfärben
                if (!legitLehrenderMailadresse(txtEmail.getText())) {
                    showInfoWindows("Mailadresse Falsch", "Mailadresse nicht korrekt");
                }
                if(selectedFile == null){
                    showInfoWindows("Profilbild fehlt", "Bitte ein Profilbild hochladen");
                }
                if (!txtPasswort.getText().equals(txtPasswortWiederholung.getText())) {
                    showInfoWindows("Passwort wiederholung Falsch", "Das eingegebene PW und das wiederholte PW stimmen nicht überein");
                }
                if (!nurBuchstaben(txtNachname.getText())) {
                    showInfoWindows("Nachname Falsch", "Nachname nicht korrekt");
                }
                if (!nurBuchstaben(txtVorname.getText())) {
                    showInfoWindows("Vorname Falsch", "Vorname nicht korrekt");
                }
                if (!nurBuchstaben(txtStrasse.getText())) {
                    showInfoWindows("Strasse Falsch", "Strasse nicht korrekt");
                }
                if (!nurBuchstaben(txtOrt.getText())) {
                    showInfoWindows("Ort Falsch", "Ort nicht korrekt");
                }
                if (!nurZahlen(txtHausnummer.getText())) {
                    showInfoWindows("Hausnummer Falsch", "Hausnummer nicht korrekt");
                }
                if (!nurZahlen(txtPostleitzahl.getText())) {
                    showInfoWindows("PLZ Falsch", "PLZ nicht korrekt");
                }
                System.out.println("Hier ist etwas falsch eingegeben worden");
            }
        }
        else
        {
            showInfoWindows("E-Mail Adresse vergeben","Unter dieser EMail gibt es bereits einen Nutzer");
        }

    }

    @FXML
    public void studierenderRegistrierenPressed(){
        changeStage("Register_Student_New.fxml");
    }

    @FXML
    public void AbbrechenRegPressed() {
        changeStage("LOGINPage.fxml");
    }

    @FXML
    public void emptyFieldsButtonAction(ActionEvent event){
        txtVorname.clear();
        txtNachname.clear();
        txtEmail.clear();
        comboLehrstuhl.setValue(null);
        comboForschungsgebiet.setValue(null);
        txtPasswort.clear();
        txtPasswortWiederholung.clear();
        txtStrasse.clear();
        txtHausnummer.clear();
        txtOrt.clear();
        txtPostleitzahl.clear();
        imgProfilbild.setImage(null);
        selectedFile=null;
    }

}
