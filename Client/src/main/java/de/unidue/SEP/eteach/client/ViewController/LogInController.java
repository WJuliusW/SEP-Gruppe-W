package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Lehrender_Controller;
import de.unidue.SEP.eteach.client.Controller.Nutzer_Controller;
import de.unidue.SEP.eteach.client.Controller.Student_Controller;
import de.unidue.SEP.eteach.client.Entities.Lehrender;
import de.unidue.SEP.eteach.client.Entities.Mail;
import de.unidue.SEP.eteach.client.Entities.Nutzer;
import de.unidue.SEP.eteach.client.Entities.Student;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;


public class LogInController extends Nutzer_Controller {
    public TextField txfMailField;
    public PasswordField pwfPasswordField;
    public Button btnLoginButton;
    public Button btnRegisterButton;
    public Student_Controller student_controller = new Student_Controller();
    public Lehrender_Controller lehrender_controller = new Lehrender_Controller();
    private Integer nutzerId;
    @FXML
    private TextField txfCode;
    public static Integer twoFA;

    @Override
public void initialize(URL location, ResourceBundle resource)
{}

    public void loginButtonPressed() throws IOException {
        // 0. Nutzereingaben holen
        String email = txfMailField.getText();
        String password = pwfPasswordField.getText();
        Nutzer_Controller nutzer_controller = new Nutzer_Controller();

        // 1. Schritt: CurrentNutzer setzen ausgehend von Email oder Matrikelnummer
        if (legitMailadresse(email)) {
            Random random = new Random();
            twoFA = random.nextInt(999999);
            Nutzer nutzer = getNutzerEndpoint().getNutzerByEmail(MainApp.getAuthHeader(), email).execute().body();  //HIER DIE ABFRAGE FUER 2FA veraendern
            Mail mail = new Mail();
            mail.setVon("eteach@fmeyer-it.de");
            mail.setAn(nutzer.getEmail());
            mail.setBetreff("Zwei Faktor");
            mail.setNachricht(twoFA.toString());
            getMailEndpoint().send(MainApp.getAuthHeader(), mail).execute();

            setCurrentNutzer(nutzer);

            Lehrender lehrender = null;
            try
            {
               lehrender = getLehrenderEndpoint().getLehrenderByEmail(MainApp.getAuthHeader(), email).execute().body();
            }
            catch(Exception e){}

            if (lehrender != null)
            {
                Lehrender lehrender1 = getLehrenderEndpoint().getLehrenderByEmail(MainApp.getAuthHeader(), email).execute().body();
                setCurrentLehrender(lehrender1);
            }
            else if (getStudentEndpoint().getStudentByEmail(MainApp.getAuthHeader(), email).execute().body() != null)
            {
                Student student = getStudentEndpoint().getStudentByEmail(MainApp.getAuthHeader(), email).execute().body();
                setCurrentStudent(student);
            }
        } else if (isMatrikelnummer(txfMailField.getText())) {
            Integer matrikelnummer = Integer.parseInt(txfMailField.getText());
            Student student = getStudentEndpoint().getStudentByMatrikelnummer(MainApp.getAuthHeader(), matrikelnummer).execute().body();
            setCurrentStudent(student);
            setCurrentNutzer(getNutzerEndpoint().getNutzerById(MainApp.getAuthHeader(), student.getId()).execute().body());
        }

        // 2. Schritt: Abgleich eingegebenes Passwort mit Passwort des CurrentNutzer
        if (getCurrentNutzer() != null && getCurrentNutzer().getPasswort().equals(pwfPasswordField.getText()))
        {
            //  2FA
            this.changeStage("2-Faktor(28).fxml");
            System.out.println(twoFA);
            //this.changeStage("eTeachMain.fxml");
        }
        else
        {
            showInfoWindows("Login nicht Erfolgreich", "Mailadresse oder Passwort falsch");
        }
    }

    public void registerButtonPressed() {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Register_Student_New.fxml"));
            Scene neu = new Scene(root, 1800, 900);
            MainApp.window.setScene(neu);
            MainApp.window.setMaximized(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeStage(String name) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(name));
            Scene neu = new Scene(root, 1800, 900);
            MainApp.window.setScene(neu);
            MainApp.window.setMaximized(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnCommit(ActionEvent event)//2FA ABfrage
    {
        //Check den zweiten FAktor
        Integer check = Integer.parseInt(txfCode.getText().toString());
        if(twoFA.equals(check))
        {
            this.changeStage("eTeachMain.fxml");
        }
        else
        {
            this.showInfoWindows("Fehler!","Der eingegebene Code ist nicht korrekt");
        }



        //Login
    }

    public void btnAbbrechenPressed(ActionEvent event)
    {
        changeStage("LOGINPage.fxml");
    }


}
