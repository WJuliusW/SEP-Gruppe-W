package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Nutzer_Controller;
import de.unidue.SEP.eteach.client.Entities.*;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Lehrender_Single_Controller extends ETeach_Controller {
    File selectedFile;

    @FXML
    private TextField txtVorname;
    @FXML
    private Button btnZuruecksetzen;
    @FXML
    private Button btnZuruecksetzen1;
    @FXML
    private Button btnSpeichern;

    @FXML
    private TextField txtNachname;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPasswort;

    @FXML
    private TextField txtPasswortWiederholung;

    @FXML
    private ComboBox<Studienfach> comboForschungsgebiet;

    @FXML
    private TextField txtOrt;

    @FXML
    private TextField txtStrasse;

    @FXML
    private TextField txtPostleitzahl;

    @FXML
    private TextField txtHausnummer;

    @FXML
    private ComboBox<Lehrstuhl> comboLehrstuhl;

    @FXML
    private ImageView imgProfilbild;

    @FXML
    private Button btnProfilbildAendern;

    @FXML
    private TableColumn<Veranstaltung, String> columnVeranstaltungstyp;

    @FXML
    private TableColumn<Veranstaltung, String> columnTitel;

    @FXML
    private TableColumn<Veranstaltung, String> columnSemester;

    @FXML
    private TableView<Veranstaltung> tblMeineVeranstaltungen;
    @FXML
    private Label lblPasswort;
    @FXML
    private Label lblPasswort1;
    @FXML
    private Label lblPasswort2;
    @FXML
    private Label lblAdresse1;
    @FXML
    private Label lblAdresse2;
    @FXML
    private Label lblAdresse3;
    @FXML
    private Label lblAdresse4;
    @FXML
    private Label lblAdresse5;

    @FXML
    public Button btnPrivatChat;

    Nutzer_Controller nutzer_controller = new Nutzer_Controller();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        txtNachname.setDisable(true);
        txtVorname.setDisable(true);
        txtEmail.setDisable(true);
        if(selectedLehrender != null)
        {
            fill(selectedLehrender);
            Context.privaterChatEmpfaenger =  selectedLehrender;
        }
        else
        {
            fill(currentLehrender);
        }




        //Lehrveranstaltung
//        tbcFach.setCellValueFactory(new PropertyValueFactory<>("Kurs"));
//        tbcLVLehrender.setCellValueFactory(new PropertyValueFactory<>("Lehrender"));
//            tbvLVFach.setItems(getVeranstaltungList());

        //IMAGE
//        Image image = new Image(getCurrentStudent().getPhotoString());
//        imgProfilbild.setImage(image);
    }

    @FXML
    void handleSpeicherButtonAction(ActionEvent event) throws IOException {
        if (txtPasswort.getText().equals(txtPasswortWiederholung.getText())
                && nurBuchstaben(txtNachname.getText())
                && nurBuchstaben(txtVorname.getText())
                && nurBuchstaben(txtStrasse.getText())
                && nurBuchstaben(txtOrt.getText())
                && nurZahlen(txtHausnummer.getText())
                && nurZahlen(txtPostleitzahl.getText())
                && legitLehrenderMailadresse(txtEmail.getText())) {
//        Neuen Lehrenden anlegen
        getCurrentLehrender().setForschungsgebiet(comboForschungsgebiet.getSelectionModel().getSelectedItem());
        getCurrentLehrender().getAdresse().setOrt(txtOrt.getText());
        getCurrentLehrender().getAdresse().setPostleitzahl(Integer.parseInt(txtPostleitzahl.getText()));
        getCurrentLehrender().getAdresse().setHausnummer(Integer.parseInt(txtHausnummer.getText()));
        getCurrentLehrender().getAdresse().setStrasse(txtStrasse.getText());
        getCurrentLehrender().setNachname(txtNachname.getText());
        getCurrentLehrender().setVorname(txtVorname.getText());
        getCurrentLehrender().setEmail(txtEmail.getText());
        getCurrentLehrender().setLehrstuhl(comboLehrstuhl.getSelectionModel().getSelectedItem());
            if(selectedFile != null)
            {
                getCurrentLehrender().setPhotoString(Base64.encodeBase64String(FileUtils.readFileToByteArray(selectedFile)));
            }
        getLehrenderEndpoint().editLehrender(MainApp.getAuthHeader(), getCurrentLehrender().getId(), getCurrentLehrender()).execute();
        showInfoWindows("Gespeichert...", "Aenderungen erfolgreich gespeichert!");

//         hier muss evtl try catch für szenen wechsel hin
         changeStage("LOGINPage.fxml");
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

    @FXML
    void handleProfilbildAendernAction(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image imageView = new Image(selectedFile.toURI().toString());
            imgProfilbild.setImage(imageView);
            getCurrentLehrender().setPhotoString(Base64.encodeBase64String(FileUtils.readFileToByteArray(selectedFile)));
        }
    }


    public void dragPBDropped(DragEvent event) {
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



    public void fill(Lehrender l)
    {
        tblMeineVeranstaltungen.setItems(nutzer_controller.meineVeranstaltungen(l));

        comboForschungsgebiet.setItems(FXCollections.observableArrayList(Studienfach.values()));
        comboForschungsgebiet.getSelectionModel().select(l.getForschungsgebiet());
        comboLehrstuhl.setItems(FXCollections.observableArrayList(Lehrstuhl.values()));
        comboLehrstuhl.getSelectionModel().select(l.getLehrstuhl());

            if(l.getId() == currentNutzer.getId() || currentLehrender != null)
            {
                // Adresse festsetzen
                txtStrasse.setText(l.getAdresse().getStrasse());
                txtPostleitzahl.setText(String.valueOf(l.adresse.getPostleitzahl()));
                txtHausnummer.setText(String.valueOf(l.adresse.getHausnummer()));
                txtOrt.setText(l.adresse.getOrt());
                //Password
                txtPasswort.setText(l.getPasswort());

                comboLehrstuhl.setPromptText(l.getLehrstuhl().getStringValue());
                comboForschungsgebiet.setPromptText(l.getForschungsgebiet().getStringValue());

                btnPrivatChat.setVisible(false);
                btnPrivatChat.setDisable(true);

                if(currentLehrender.getId() != l.getId())
                {
                    btnZuruecksetzen.setDisable(true);
                    btnZuruecksetzen.setVisible(false);
                    btnZuruecksetzen1.setDisable(true);
                    btnZuruecksetzen1.setVisible(false);
                    btnSpeichern.setDisable(true);
                    btnSpeichern.setVisible(false);
                    btnProfilbildAendern.setDisable(true);
                    btnProfilbildAendern.setVisible(false);


                    lblPasswort.setDisable(true);
                    lblPasswort.setVisible(false);
                    lblPasswort1.setDisable(true);
                    lblPasswort1.setVisible(false);
                    lblPasswort2.setDisable(true);
                    lblPasswort2.setVisible(false);

                    txtPasswort.setDisable(true);
                    txtPasswort.setVisible(false);
                    txtPasswortWiederholung.setDisable(true);
                    txtPasswortWiederholung.setVisible(false);


                    comboLehrstuhl.setDisable(true);

                    comboForschungsgebiet.setDisable(true);

                    btnPrivatChat.setVisible(true);
                    btnPrivatChat.setDisable(false);

                }
            }
            else
            {
                txtStrasse.setDisable(true);
                txtStrasse.setVisible(false);
                txtPostleitzahl.setDisable(true);
                txtPostleitzahl.setVisible(false);
                txtHausnummer.setDisable(true);
                txtHausnummer.setVisible(false);
                txtOrt.setDisable(true);
                txtOrt.setVisible(false);

                lblAdresse1.setDisable(true);
                lblAdresse1.setVisible(false);
                lblAdresse2.setDisable(true);
                lblAdresse2.setVisible(false);
                lblAdresse3.setDisable(true);
                lblAdresse3.setVisible(false);
                lblAdresse4.setDisable(true);
                lblAdresse4.setVisible(false);
                lblAdresse5.setDisable(true);
                lblAdresse5.setVisible(false);

                lblPasswort.setDisable(true);
                lblPasswort.setVisible(false);
                lblPasswort1.setDisable(true);
                lblPasswort1.setVisible(false);
                lblPasswort2.setDisable(true);
                lblPasswort2.setVisible(false);

                txtPasswort.setDisable(true);
                txtPasswort.setVisible(false);
                txtPasswortWiederholung.setDisable(true);
                txtPasswortWiederholung.setVisible(false);

                btnZuruecksetzen.setDisable(true);
                btnZuruecksetzen.setVisible(false);
                btnZuruecksetzen1.setDisable(true);
                btnZuruecksetzen1.setVisible(false);
                btnSpeichern.setDisable(true);
                btnSpeichern.setVisible(false);
                btnProfilbildAendern.setDisable(true);
                btnProfilbildAendern.setVisible(false);

                comboLehrstuhl.setDisable(true);

                comboForschungsgebiet.setDisable(true);

                btnPrivatChat.setVisible(true);
                btnPrivatChat.setDisable(false);
            }


        columnSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        columnTitel.setCellValueFactory(new PropertyValueFactory<>("titel"));
        columnVeranstaltungstyp.setCellValueFactory(new PropertyValueFactory<>("veranstaltungstyp"));
//        if (FXCollections.observableArrayList(getCurrentStudent().getVeranstaltungList()) != null) {
//            tblMeineVeranstaltungen.setItems(FXCollections.observableArrayList(getCurrentLehrender().getVeranstaltungList()));
//        }
            //NAME + EMAIL
            txtVorname.setText(l.vorname);
            txtNachname.setText(l.nachname);
            txtEmail.setText(l.email);
            //IMAGE
            //Image image = new Image(s.photoString);
            //imgProfilBild.setImage(image);
        try {
            byte[] in = Base64.decodeBase64(l.photoString);
            File file = new File("Client/src/main/resources/Profilbild");
            FileUtils.writeByteArrayToFile(file, in);
            Image image = new Image(file.toURI().toString());
            imgProfilbild.setImage(image);
        }
        catch(Exception e){}



        }

    public void dragPBOver(DragEvent event) {
        System.out.println("onDragOver");

        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }

        event.consume();
    }

    public void btnPrivChatPressed(ActionEvent event)
    {
        openModalForm("PrivatChat","Chat "  + Context.privaterChatEmpfaenger.getVorname() + "  " + Context.privaterChatEmpfaenger.getNachname());
    }
}
