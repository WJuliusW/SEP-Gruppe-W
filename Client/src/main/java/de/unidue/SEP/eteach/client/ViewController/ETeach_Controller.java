package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.Ereignis_Controller;
import de.unidue.SEP.eteach.client.Endpoints.NutzerEndpoint;
import de.unidue.SEP.eteach.client.Entities.Ereignis;
import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.Remindertyp;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import retrofit2.Response;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ETeach_Controller extends Context implements Initializable {
    Response<Nutzer> responseNutzer;

    private static Veranstaltung currentVeranstaltung;
    private NutzerEndpoint nutzerEndpoint;


    //<editor-fold desc="Declaration FXML Control Elements">
    @FXML
    public BorderPane borderpaneMainPane;


    @FXML
    private GridPane gridNavBar;

    @FXML
    private ToolBar toolbarMenuLeft;

    @FXML
    private Label lblCurrentUser;

    @FXML
    private Button btnEinstellungen;

    @FXML
    private Button btnProfil;

    @FXML
    private ToolBar toolbarMenuRight;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnVeranstaltungen;

    @FXML
    private Button btnKalender;

    @FXML
    private Button btnChat;

    @FXML
    private Button btnContacts;
    //</editor-fold>

    //<editor-fold desc="ActionEvents Menu Buttons">

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //REMINDER POPUP ANZEIGEN
        Ereignis_Controller eC = new Ereignis_Controller();

        List<Ereignis> eList = eC.getReminder(Context.getCurrentNutzer());
        for(int i = 0;i< eList.size(); i++)
        {
            if(eList.get(i).remindertyp.equals(Remindertyp.POPUP) || eList.get(i).remindertyp.equals(Remindertyp.BEIDES))
            {
                String a = "";
                if(eList.get(i).getVeranstaltung() != null)
                {
                    a = eList.get(i).getNameVorlesung();
                }
                else
                {
                    a =  "Persoenlicher Termin von ihnen";
                }

                showInfoWindows(eList.get(i).getTitel(),"Das Event " + eList.get(i).getTitel() + " von " + a + " beginnt in " +  eList.get(i).getErinnerungsIntervall() + " Stunden");
            }
        }

        eC.watchReminderByNutzer(getCurrentNutzer());

        if (getCurrentNutzer() != null) {
            lblCurrentUser.setText(getCurrentNutzer().toString());


        }

    }

    @FXML
    void handleChatButtonAction(ActionEvent event)
    {
        borderChangePane("Freundschaftsanfragen(21).fxml");
        deleteRightPane();
    }

    @FXML
    void handleHomeButtonAction(ActionEvent event) throws IOException {
        if (getCurrentStudent() != null) {
            borderChangePane("student_single_view.fxml");
            borderChangeRightPane("Freundschaftsanfragen(21).fxml");
        } else if (getCurrentLehrender() != null) {
            borderChangePane("lehrender_single.fxml");
            borderChangeRightPane("Freundschaftsanfragen(21).fxml");
        }
    }

    @FXML
    void handleKalenderButtonAction(ActionEvent event) {

        borderChangePane("Kalender.fxml");
        deleteRightPane();
    }

    @FXML
    void handleContactsButtonAction(ActionEvent event) throws IOException
    {
        borderChangePane("projektgruppen.fxml");
        deleteRightPane();
    }

    @FXML
    void handleProfilButtonAction(ActionEvent event) throws IOException {
        if (getCurrentStudent() != null) {
            borderChangePane("student_single_view.fxml");
            borderChangeRightPane("Freundschaftsanfragen(21).fxml");
        } else {
            borderChangePane("lehrender_single.fxml");
            borderChangeRightPane("Freundschaftsanfragen(21).fxml");
        }
    }

    @FXML
    void handleSettingsButtonAction(ActionEvent event) {
    }

    @FXML
    public void alleLehrveranstaltungPressed(ActionEvent actionEvent) {

        borderChangePane("veranstaltung_list.fxml");
        deleteRightPane();
    }

    @FXML
    void handleVeranstaltungenButtonAction(ActionEvent event) throws IOException {
        borderChangePane("veranstaltung_collection.fxml");
        deleteRightPane();
    }
    //</editor-fold>

    //<editor-fold desc="Util Methods">

    public void changePane(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(ui));

        } catch (IOException e) {
            e.printStackTrace();
        }
        MainApp.mainPane.setCenter(root);
    }

    public void borderChangePane(String ui)
    {
        if(Context.chatController != null)
        {
            chatController.unwatchChatByVeranstaltung();
        }
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(ui));

        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpaneMainPane.setCenter(root);
        MainApp.mainPane = borderpaneMainPane;
    }

    public void borderChangeRightPane(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(ui));

        } catch (IOException e) {
            e.printStackTrace();
        }
        borderpaneMainPane.setRight(root);
        MainApp.mainPane = borderpaneMainPane;
    }

    public void deleteRightPane()
    {
        borderpaneMainPane.setRight(null);
    }

    public void changeStage(String name) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(name));
            Scene neu = new Scene(root);
            MainApp.window.setScene(neu);
            MainApp.window.setMaximized(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openModalForm(String formName, String titel) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource(formName + ".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root));
        stage.setTitle(titel);
        stage.initModality(Modality.WINDOW_MODAL);
        //stage.initOwner(getMainStage());
        stage.show();
    }

    public void showInfoWindows(String titel, String nachricht) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titel);
        alert.setHeaderText(null);
        alert.setContentText(nachricht);
        alert.showAndWait();
    }

    public void refreshMain() throws IOException {
        changePane("veranstaltung_collection.fxml");
    }
    //</editor-fold>


    public static boolean isMatrikelnummer(String text) {
        return text.matches("^[0-9]{7}");
    }

    public boolean legitStudentMailadresse(String text) {
        return text.matches("[a-zA-Z\\d\\-_.]+@stud-uni.de");
    }

    public boolean legitLehrenderMailadresse(String text) {
        return text.matches("[a-zA-Z\\d\\-_.]+@doz-uni.de");
    }

    public boolean nurBuchstaben(String toCheck) {
        return toCheck.matches("[a-zA-ZäÄöÖüÜß]+([\\-|\\s]*[a-zA-ZäÄöÖüÜß]+)*");
    }

    public boolean nurZahlen(String toCheck) {
        return toCheck.matches("[\\d]+");
    }

    public boolean legitMailadresse(String text) {
        return text.matches("[a-zA-Z\\d\\-_.]+[@][a-zA-Z\\-]+[.][a-zA-Z]+");
    }


    public void LogoutPressed(ActionEvent event)
    {
        changeStage("LOGINPage.fxml");
        setCurrentLehrender(null);
        setCurrentStudent(null);
        setCurrentNutzer(null);
        setCurrentVeranstaltung(null);
    }
}
