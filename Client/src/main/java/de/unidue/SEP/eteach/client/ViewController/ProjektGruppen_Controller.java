package de.unidue.SEP.eteach.client.ViewController;


import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.ChatRaum_Controller;
import de.unidue.SEP.eteach.client.Controller.Nutzer_Controller;
import de.unidue.SEP.eteach.client.Controller.ToDo_Controller;
import de.unidue.SEP.eteach.client.Controller.Veranstaltung_Controller;
import de.unidue.SEP.eteach.client.Entities.*;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class ProjektGruppen_Controller extends Veranstaltung_Controller implements Initializable
{
    @FXML
    private Button btnAddMaterial;
    @FXML
    private Button btnDownloadMaterial;
    @FXML
    private Button btnImportCSV;

    @FXML
    private Button btnDeleteMaterial;

    @FXML
    private Button btnEditMaterial;

    @FXML
    private Label lblHeadline;

    @FXML
    private Label lblOrganisator;

    @FXML
    private Button btnAddLehrveranstaltung;

    @FXML
    private Button btnDeleteLehrveranstaltung;

    @FXML
    private Button btnRefreshAllTableData;

    @FXML
    public ComboBox<Veranstaltung> comboVeranstaltungen;

    @FXML
    private Button btnEditVeranstaltung;

    @FXML
    private GridPane gridUebersicht;

    //    @FXML
//    private VBox paneVeranstaltungsteilnehmer;
//
//    @FXML
//    private VBox paneVeranstaltungTermine;
//
//    @FXML
//    private VBox paneMaterialList;

    @FXML
    private Label lblTitel;

    @FXML
    private Label lblSemester;

    @FXML
    private Label lblVeranstaltungstyp;

    @FXML
    private Label lblBeschreibung;

    @FXML
    private TableView<ToDo> tblToDo;

    @FXML
    private TableColumn<ToDo, String> tbcToDoTitel;

    @FXML
    private TableColumn<ToDo, String> tbcNutzer;

    @FXML
    private Button btnAddTeilnehmer;

    @FXML
    private Button btnDeleteTeilnehmer;

    @FXML
    private Button btnEditTeilnehmer;


    @FXML
    public TableView<Material> tblMaterial;

    @FXML
    private TableColumn<Material, String> columnBezeichnung;


    @FXML
    private TableColumn<Material, String> columnAnmerkungen;

    @FXML
    public TextField txfMessage;

    @FXML
    public TableView<ChatNachricht> chat;

    @FXML
    public TableColumn<ChatNachricht, Date> tbcChatDate;
    @FXML
    public TableColumn<ChatNachricht, String> tbcChatNutzer;
    @FXML
    public TableColumn<ChatNachricht, String> tbcNachricht;



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        if(currentLehrender == null)
        {
            btnAddTeilnehmer.setVisible(false);
            btnAddTeilnehmer.setDisable(true);
        }
        Context.currentVeranstaltung = null;
        Nutzer_Controller nutzerController = new Nutzer_Controller();
        FilteredList<Veranstaltung> Kurslist =new FilteredList<> (nutzerController.meineVeranstaltungen(currentNutzer));
        Kurslist.setPredicate(t -> t.getTyp() == Veranstaltungstyp.PROJEKTGRUPPE);
        comboVeranstaltungen.setItems(Kurslist);//currentNutzer.getVeranstaltungslist

        // PREP Tables
        tblMaterial.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // MATERIAL
        columnBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        columnAnmerkungen.setCellValueFactory(new PropertyValueFactory<>("anmerkungen"));

        tbcToDoTitel.setCellValueFactory(new PropertyValueFactory<>("aufgabe"));
        tbcNutzer.setCellValueFactory(new PropertyValueFactory<>("aufgabensteller"));


        tbcChatDate.setCellValueFactory(new PropertyValueFactory<>("zeitstempel"));
        tbcChatNutzer.setCellValueFactory(new PropertyValueFactory<>("absender"));
        tbcNachricht.setCellValueFactory(new PropertyValueFactory<>("inhalt"));

    }


    @FXML
    void handleComboBoxVeranstaltungChange(ActionEvent event)
    {
        if(chatController != null)
        {
            chatController.unwatchChatByVeranstaltung();
        }
        ChatRaum_Controller controller = new ChatRaum_Controller();

        setCurrentVeranstaltung(comboVeranstaltungen.getValue());
        //lblTitel.setText(getCurrentVeranstaltung().getTitel());
        //lblBeschreibung.setText(getCurrentVeranstaltung().getBeschreibung());
        //lblSemester.setText(getCurrentVeranstaltung().getSemester().getStringValue());
        //lblVeranstaltungstyp.setText(getCurrentVeranstaltung().getVeranstaltungstyp().getStringValue());

        // Load related Data
        tblMaterial.setItems(FXCollections.observableArrayList(getCurrentVeranstaltung().getMaterialList()));


        ToDo_Controller c = new ToDo_Controller();
        FilteredList<ToDo> list = new FilteredList<ToDo> (c.getToDoList());
        list.setPredicate(t -> t.veranstaltung.getId() == Context.currentVeranstaltung.getId());
        tblToDo.setItems(list);


        chatController = controller;
        //ChatRaum
        Context.chat = this.chat;
        List<ChatNachricht> test = chatController.watchChatByVeranstaltung(Context.currentVeranstaltung);
        chat.setItems(FXCollections.observableList(test));

    }



    @FXML
    void handleAddLehrveranstaltungButtonAction(ActionEvent event)
    {
        Veranstaltung veranstaltung = new Veranstaltung(getCurrentNutzer());
        setCurrentVeranstaltung(veranstaltung);
        openModalForm("veranstaltung_single", "Neue Veranstaltung anlegen...");
    }

    @FXML
    void handleDeleteVeranstaltungButtonAction(ActionEvent event) throws IOException {
        if (getCurrentVeranstaltung() != null) {
            deleteVeranstaltung(getCurrentVeranstaltung());
            showInfoWindows("Erfolgreich", "Die Veranstaltung " + getCurrentVeranstaltung().getTitel() + " wurde entfernt");
            setCurrentVeranstaltung(null);
        } else {
            showInfoWindows("Fehler", "Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
        }
    }

    @FXML
    void handleEditVeranstaltungButtonAction(ActionEvent event) {
        if (getCurrentVeranstaltung() != null) {
            setCurrentMode("Edit");
            openModalForm("veranstaltung_single", "Veranstaltung bearbeiten...");
        } else {
            showInfoWindows("Fehler", "Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
        }
    }



    @FXML
    void handleAddMaterialButtonAction(ActionEvent event)
    {
        if (getCurrentVeranstaltung() != null) {
            setCurrentMode("New");
            openModalForm("material_single", "Neues Material anlegen...");
        } else {
            showInfoWindows("Fehler", "Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
        }
    }

    @FXML
    void handleDeleteMaterialButtonAction(ActionEvent event) {
        List<Material> selectedMaterialList = tblMaterial.getSelectionModel().getSelectedItems();
        getCurrentVeranstaltung().getMaterialList().removeAll(selectedMaterialList);
        tblMaterial.setItems(FXCollections.observableArrayList(getCurrentVeranstaltung().getMaterialList()));
    }

    @FXML
    void handleDownloadMaterialButtonAction(ActionEvent event) {
        Material material = tblMaterial.getSelectionModel().getSelectedItem();
        if (material != null) {
            FileChooser fileChooser = new FileChooser();

            // Filter für PDF dateien
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF datei (*.pdf)", "*.pdf");
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialFileName(material.getBezeichnung());

            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                writeTextToFile(Base64.getDecoder().decode(material.getMaterialString()), file.getPath());
                showInfoWindows("Datei erfolgreich gespeichert", "Die Datei " + file.getName() + " wurde am Ort " + file.getPath() + " abgelegt");
            }
        }
        else{
            showInfoWindows("Keine Datei ausgewaehlt", "Bitte eine Datei auswaehlen");
        }
    }

    @FXML
    void handleEditMaterialButtonAction(ActionEvent event) {
        setCurrentMaterial(tblMaterial.getSelectionModel().getSelectedItem());
        setCurrentMode("Edit");
        openModalForm("material_single", "Material bearbeiten...");
    }

    @FXML
    void handleAddTeilnehmerButtonAction(ActionEvent event)
    {
        if(getCurrentLehrender() != null) {
            if (getCurrentVeranstaltung() != null) {
                openModalForm("nutzer_list", "Auswahl Studenten");
            } else {
                showInfoWindows("Fehler", "Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
            }
        }

    }

    @FXML
    void handleDeleteTeilnehmerButtonAction(ActionEvent event) {

    }

    @FXML
    void handleEditTeilnehmerButtonAction(ActionEvent event) {

    }

    @FXML
    void handleAddTerminButtonAction(ActionEvent event)
    {
        Context.editToDo = null;
        if (getCurrentVeranstaltung() != null) {
            openModalForm("todo_new", "Neuen Termin anlegen...");
        } else {
            showInfoWindows("Fehler", "Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
        }

    }

    @FXML
    void handleDeleteTerminButtonAction(ActionEvent event) throws IOException
    {
        ToDo_Controller tC = new ToDo_Controller();
        ToDo t = tblToDo.getSelectionModel().getSelectedItem();
        tC.deleteToDo(t);

    }
    @FXML
    void handleEditTerminButtonAction(ActionEvent event)
    {
        Context.editToDo = tblToDo.getSelectionModel().getSelectedItem();
        if (getCurrentVeranstaltung() != null) {
            openModalForm("todo_new", "Neuen Termin anlegen...");
        } else {
            showInfoWindows("Fehler", "Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
        }
    }


    @FXML
    void handleRefreshAllButtonAction(ActionEvent event) {

    }

    public void btnSendPressed(ActionEvent event) throws IOException {
        ChatRaum_Controller chatC = new ChatRaum_Controller();
        ChatNachricht msg = new ChatNachricht();
        msg.setInhalt(txfMessage.getText());
        msg.setAbsender(currentNutzer);
        System.out.println(currentNutzer.getId());
        Date date = new Date();
        System.out.println(date);
        System.out.println(chatC.sendMessage(msg,Context.currentVeranstaltung,currentNutzer).getAbsender().getId());
        txfMessage.clear();
    }




    /*

    public synchronized void addToChat(Message msg) {
        Task<HBox> othersMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                Image image = new Image(getClass().getClassLoader().getResource("images/" + msg.getPicture().toLowerCase() + ".png").toString());
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);
                BubbledLabel bl6 = new BubbledLabel();
#
                bl6.setText(msg.getName() + ": " + msg.getMsg());

                bl6.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
                HBox x = new HBox();

      LINKS RECHTS ORIENTIERUNG

                bl6.setBubbleSpec(BubbleSpec.FACE_LEFT_CENTER);
                x.getChildren().addAll(profileImage, bl6);
                #
                return x;
            }
        };

        othersMessages.setOnSucceeded(event -> {
            chatPane.getItems().add(othersMessages.getValue());
        });

        Task<HBox> yourMessages = new Task<HBox>() {
            @Override
            public HBox call() throws Exception {
                Image image = userImageView.getImage();
                ImageView profileImage = new ImageView(image);
                profileImage.setFitHeight(32);
                profileImage.setFitWidth(32);
#
                BubbledLabel bl6 = new BubbledLabel();
#
                bl6.setText(msg.getMsg());

                bl6.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN,
                        null, null)));
                HBox x = new HBox();
#
  LINKS REchts ORIENTIERUNG

                x.setMaxWidth(chatPane.getWidth() - 20);
                x.setAlignment(Pos.TOP_RIGHT);
                bl6.setBubbleSpec(BubbleSpec.FACE_RIGHT_CENTER);
                x.getChildren().addAll(bl6, profileImage);

                return x;
            }
        };
        yourMessages.setOnSucceeded(event -> chatPane.getItems().add(yourMessages.getValue()));


????##
        if (msg.getName().equals(usernameLabel.getText())) {
            Thread t2 = new Thread(yourMessages);
            t2.setDaemon(true);
            t2.start();
        } else {
            Thread t = new Thread(othersMessages);
            t.setDaemon(true);
            t.start();
        }
    }

 */
}
