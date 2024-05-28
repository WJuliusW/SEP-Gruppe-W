package de.unidue.SEP.eteach.client;

import de.unidue.SEP.eteach.client.Controller.ChatRaum_Controller;
import de.unidue.SEP.eteach.client.Endpoints.*;
import de.unidue.SEP.eteach.client.Entities.*;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;

@Getter
@Setter
public class Context {
    public Stage mainStage;

    public static Adresse currentAdresse;

    public static Ereignis currentEreignis;
    public static Lehrender currentLehrender;
    public static Material currentMaterial;
    public static Nutzer currentNutzer;
    public static Semester currentSemester;
    public static Student currentStudent;
    public static Veranstaltung currentVeranstaltung;
    public static NutzerEndpoint nutzerEndpoint;
    public static String soSeRegex = "sose[\\s]*[\\d]{2}[\\/|\\-|\\_|\\s]?[\\d]{2}";
    public static String soSeRichtigesFormat = "Sommersemester ";
    public static String wiSeRegex = "wise[\\s]*[\\d]{2,4}[\\/|\\-|\\_|\\s]?[\\d]{2,4}";
    public static String wiSeRichtigesFormat = "Wintersemester ";

    public static String currentMode;

    //Zur Ansicht anderer Profile
    public static Nutzer selectedNutzer;
    public static Student selectedStudent;
    public static  Lehrender selectedLehrender;

    //Zyklus 2
    public static Quiz currentQuiz;
    public static ToDo editToDo;
    public static List<Abgabe> quizAntworten;

    public static TableView<ChatNachricht> chat;

    public static ChatRaum_Controller chatController;

    //Zyklus 3
    public static Ereignis ereignisZeigen;
    public static TableView<PrivatNachricht> privatChat;
    public static Nutzer privaterChatEmpfaenger;

    public Context() {
    }

    //<editor-fold desc="RETROFIT ENDPOINTS">
    public NutzerEndpoint getNutzerEndpoint() {
        return RetrofitClient.getClient().create(NutzerEndpoint.class);
    }

    public static LehrenderEndpoint getLehrenderEndpoint() {
        return RetrofitClient.getClient().create(LehrenderEndpoint.class);
    }

    public static MaterialEndpoint getMaterialEndpoint() {
        return RetrofitClient.getClient().create(MaterialEndpoint.class);
    }
    public static StudentEndpoint getStudentEndpoint() {
        return RetrofitClient.getClient().create(StudentEndpoint.class);
    }

    public static VeranstaltungEndpoint getVeranstaltungEndpoint() {
        return RetrofitClient.getClient().create(VeranstaltungEndpoint.class);
    }

    public static SemesterEndpoint getSemesterEndpoint() {
        return RetrofitClient.getClient().create(SemesterEndpoint.class);
    }

    public static MailEndpoint getMailEndpoint(){
        return RetrofitClient.getClient().create(MailEndpoint.class);
    }

    public static PrivatNachrichtenEndpoint getPrivatNachrichtenEndpoint(){

        return RetrofitClient.getClient().create(PrivatNachrichtenEndpoint.class);
    }

//</editor-fold> (1

    public Stage getMainStage() {
        return mainStage;
    }
    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public static Adresse getCurrentAdresse() {
        return currentAdresse;
    }

    public static void setCurrentAdresse(Adresse currentAdresse) {
        Context.currentAdresse = currentAdresse;
    }



    //<editor-fold desc="GETTER / SETTER CONTEXT OBJECTS">
    public static String getCurrentMode() {
        return currentMode;
    }

    public static void setCurrentMode(String currentMode) {
        Context.currentMode = currentMode;
    }

    public static Ereignis getCurrentEreignis() {
        return currentEreignis;
    }

    public static void setCurrentEreignis(Ereignis currentEreignis) {
        Context.currentEreignis = currentEreignis;
    }

    public static Lehrender getCurrentLehrender() {
        return currentLehrender;
    }

    public static void setCurrentLehrender(Lehrender currentLehrender) {
        Context.currentLehrender = currentLehrender;
    }

    public static Material getCurrentMaterial() {
        return currentMaterial;
    }

    public static void setCurrentMaterial(Material currentMaterial) {
        Context.currentMaterial = currentMaterial;
    }

    public static Nutzer getCurrentNutzer() {
        return currentNutzer;
    }

    public static void setCurrentNutzer(Nutzer currentNutzer) {
        Context.currentNutzer = currentNutzer;
    }


    public static Student getCurrentStudent() {
        return currentStudent;
    }

    public static void setCurrentStudent(Student currentStudent) {
        Context.currentStudent = currentStudent;
    }

    public static Veranstaltung getCurrentVeranstaltung() {
        return currentVeranstaltung;
    }

    public static void setCurrentVeranstaltung(Veranstaltung currentVeranstaltung) {
        Context.currentVeranstaltung = currentVeranstaltung;
    }

    //</editor-fold>

    //<editor-fold desc="Regex Helpers">
    public boolean checkCsv(String text) { return text.matches(".*.csv");}

    public boolean checkPdf(String text) { return text.matches(".*.pdf");}

    public boolean checkXml(String text) { return text.matches(".*.xml");}

    public boolean sommerSemesterFormat(String text){ return text.matches("sose[\\s]*[\\d]{2,4}");}

    public boolean winterSemesterFormat(String text){ return text.matches("wise[\\s]*[\\d]{2,4}[/|\\-|\\.|_|\\s]?[\\d]{2,4}");}

    public static String replaceSemesterFormat(String text, String regex, String neuesFormat){
        return text.replaceFirst(regex, neuesFormat);
    }

    public boolean nurBuchstaben(String toCheck) {
        return toCheck.matches("[a-zA-ZäÄöÖüÜß]+([\\-|\\s][a-zA-ZäÄöÖüÜß]+)*");
    }

    public boolean nurZahlen(String toCheck) {
        return toCheck.matches("[\\d]+");
    }

    public static boolean isMatrikelnummer(String text){
        return text.matches("^[0-9]{7}");
    }

    // muss in Gebrauchsanleitung berücksichtigt werden Login und Registrieren geht nur mit Uni Mailadresse
    // mit der Endung @stud-uni.de
    public boolean legitStudentMailadresse(String text) {
        return text.matches("[a-zA-Z\\d\\-_.]+@stud-uni.de");
    }

    // muss in Gebrauchsanleitung berücksichtigt werden Login und Registrieren geht nur mit Uni Mailadresse
    // mit der Endung @doz-uni.de
    public boolean legitLehrenderMailadresse(String text) {
        return text.matches("[a-zA-Z\\d\\-_.]+@doz-uni.de");
    }

    //</editor-fold>


    public void writeTextToFile(byte[] fileContent, String filePath){
        try {
            FileOutputStream stream = new FileOutputStream(filePath);
            stream.write(fileContent);
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
