package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Nutzer_Controller;
import de.unidue.SEP.eteach.client.Controller.Student_Controller;
import de.unidue.SEP.eteach.client.Entities.Student;
import de.unidue.SEP.eteach.client.Entities.Studienfach;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;


public class Student_Single_Controller extends Student_Controller implements Initializable {
    private Nutzer_Controller nutzer_controller=new Nutzer_Controller();
    File selectedFile;

    @FXML
    private AnchorPane paneRootPane;
    @FXML
    private Label lblMatrikelnummer;
    @FXML
    private TextField txtVorname;
    @FXML
    private TextField txtNachname;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPasswort;
    @FXML
    private TextField txtPasswortWiederholung;
    @FXML
    private TextField txtMatrikelnummer;
    @FXML
    private Button btnZuruecksetzen;
    @FXML
    private Button btnZuruecksetzen1;
    @FXML
    private Button btnSpeichern;
    @FXML
    private TextField txtOrt;
    @FXML
    private TextField txtStrasse;
    @FXML
    private TextField txtPostleitzahl;
    @FXML
    private TextField txtHausnummer;
    @FXML
    private ImageView imgProfilbild;
    @FXML
    private Button btnProfilbildAendern;
    @FXML
    private ComboBox<Studienfach> comboStudienfach;
    @FXML
    private ComboBox<Student> comboStudentsList;
    @FXML
    private VBox paneVBox;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnKalender;
    @FXML
    private Button btnChat;
    @FXML
    private AnchorPane paneScenePane;
    @FXML
    private Label lblBildpfad;
    @FXML
    private Label lblValues;
    @FXML
    private Button btnLadeStudent;
    @FXML
    private Button btnLoescheStudent;
    @FXML
    private TextField txtStudentId;


    @FXML
    private Label lblStudienfach;

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

    @FXML
    private TextField txtPhotoString;

    @FXML
    private TableColumn<Veranstaltung, String> columnVeranstaltungstyp;

    @FXML
    private TableColumn<Veranstaltung, String> columnTitel;

    @FXML
    private TableColumn<Veranstaltung, String> columnSemester;

    @FXML
    private TableView<Veranstaltung> tblMeineVeranstaltungen;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        columnTitel.setCellValueFactory(new PropertyValueFactory<>("titel"));
        columnVeranstaltungstyp.setCellValueFactory(new PropertyValueFactory<>("veranstaltungstyp"));
        columnSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        txtNachname.setDisable(true);
        txtVorname.setDisable(true);
        txtEmail.setDisable(true);

        if(selectedStudent != null)
        {
            fillStudent(selectedStudent);
            Context.privaterChatEmpfaenger = selectedStudent;
        }
        else
        {
            fillStudent(currentStudent);
        }
    }

    @FXML
    void handleSpeicherButtonAction(ActionEvent event) throws IOException
    {
        getCurrentStudent().setStudienfach(comboStudienfach.getSelectionModel().getSelectedItem());
        getCurrentStudent().getAdresse().setOrt(txtOrt.getText());
        getCurrentStudent().getAdresse().setPostleitzahl(Integer.parseInt(txtPostleitzahl.getText()));
        getCurrentStudent().getAdresse().setHausnummer(Integer.parseInt(txtHausnummer.getText()));
        getCurrentStudent().getAdresse().setStrasse(txtStrasse.getText());
        getCurrentStudent().setNachname(txtNachname.getText());
        getCurrentStudent().setVorname(txtVorname.getText());
        getCurrentStudent().setEmail(txtEmail.getText());
        if(selectedFile != null)
        {
        getCurrentStudent().setPhotoString(Base64.encodeBase64String(FileUtils.readFileToByteArray(selectedFile)));
        }
        getStudentEndpoint().editStudent(MainApp.getAuthHeader(),getCurrentStudent().getId()).execute();
        showInfoWindows("Gespeichert...", "Aenderungen erfolgreich gespeichert!");
    }

    @FXML
    void handleProfilbildAendernAction(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);
        if (selectedFile != null) {
            Image imageView = new Image(selectedFile.toURI().toString());
            imgProfilbild.setImage(imageView);
            getCurrentStudent().setPhotoString(Base64.encodeBase64String(FileUtils.readFileToByteArray(selectedFile)));
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

    public void dragPBOver(DragEvent event) {
        System.out.println("onDragOver");

        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }

        event.consume();
    }

    public void fillStudent(Student s)
    {

        tblMeineVeranstaltungen.setItems(nutzer_controller.meineVeranstaltungen(s));
        if (s.getId() == currentNutzer.getId()) {
            // Adresse festsetzen
            txtStrasse.setText(s.getAdresse().getStrasse());
            txtPostleitzahl.setText(String.valueOf(s.adresse.getPostleitzahl()));
            txtHausnummer.setText(String.valueOf(s.adresse.getHausnummer()));
            txtOrt.setText(s.adresse.getOrt());
            //MatrikelNr
            lblMatrikelnummer.setText(String.valueOf(s.getMatrikelnummer()));
            //Password
            txtPasswort.setText(s.getPasswort());
            comboStudienfach.setPromptText(s.getStudienfach().getStringValue());


            btnPrivatChat.setVisible(false);
            btnPrivatChat.setDisable(true);

        }
        else if (currentLehrender != null) {
            // Adresse festsetzen
            txtStrasse.setText(s.getAdresse().getStrasse());
            txtPostleitzahl.setText(String.valueOf(s.adresse.getPostleitzahl()));
            txtHausnummer.setText(String.valueOf(s.adresse.getHausnummer()));
            txtOrt.setText(s.adresse.getOrt());

            txtStrasse.setDisable(true);
            txtPostleitzahl.setDisable(true);
            txtHausnummer.setDisable(true);
            txtOrt.setDisable(true);

            //MatrikelNr
            lblMatrikelnummer.setText(String.valueOf(s.getMatrikelnummer()));

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


            comboStudienfach.setDisable(true);

            btnPrivatChat.setVisible(true);
            btnPrivatChat.setDisable(false);

        }
        else {
            txtStrasse.setDisable(true);
            txtStrasse.setVisible(false);
            txtPostleitzahl.setDisable(true);
            txtPostleitzahl.setVisible(false);
            txtHausnummer.setDisable(true);
            txtHausnummer.setVisible(false);
            txtOrt.setDisable(true);
            txtOrt.setVisible(false);
            lblMatrikelnummer.setDisable(true);
            lblMatrikelnummer.setVisible(false);

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

            lblMatrikelnummer.setDisable(true);
            lblMatrikelnummer.setVisible(false);

            btnZuruecksetzen.setDisable(true);
            btnZuruecksetzen.setVisible(false);
            btnZuruecksetzen1.setDisable(true);
            btnZuruecksetzen1.setVisible(false);
            btnSpeichern.setDisable(true);
            btnSpeichern.setVisible(false);
            btnProfilbildAendern.setDisable(true);
            btnProfilbildAendern.setVisible(false);

            comboStudienfach.setDisable(true);

            btnPrivatChat.setVisible(true);
            btnPrivatChat.setDisable(false);

        }

        comboStudienfach.setItems(FXCollections.observableArrayList(Studienfach.values()));

        columnSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        columnTitel.setCellValueFactory(new PropertyValueFactory<>("titel"));
        columnVeranstaltungstyp.setCellValueFactory(new PropertyValueFactory<>("veranstaltungstyp"));
        //if (FXCollections.observableArrayList(s.getVeranstaltungList())!=null){
            //tblMeineVeranstaltungen.setItems(FXCollections.observableArrayList(s.getVeranstaltungList()));
        //}




        //NAME + EMAIL
        txtVorname.setText(s.vorname);
        txtNachname.setText(s.nachname);
        txtEmail.setText(s.email);

        //IMAGE
        //Image image = new Image(s.photoString);
        //imgProfilBild.setImage(image);

        //File file = new File(s.photoString);
        //Image imageView = new Image(file.toURI().toString());
        //byte[] data = Base64.decodeBase64(s.photoString);
        //s.photoString
        try {
            byte[] in = Base64.decodeBase64(s.photoString);
            File file = new File("Client/src/main/resources/Profilbild");
            FileUtils.writeByteArrayToFile(file, in);
            Image image = new Image(file.toURI().toString());
            imgProfilbild.setImage(image);
        }
        catch(Exception e){}

        Context.privaterChatEmpfaenger = s;

    }

    public void btnPrivChatPressed(ActionEvent event)
    {
        openModalForm("PrivatChat","Chat "  + Context.privaterChatEmpfaenger.getVorname() + "  " + Context.privaterChatEmpfaenger.getNachname());
    }
}
