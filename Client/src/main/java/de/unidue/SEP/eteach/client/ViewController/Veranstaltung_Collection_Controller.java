package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Context;
import de.unidue.SEP.eteach.client.Controller.*;
import de.unidue.SEP.eteach.client.Entities.*;
import de.unidue.SEP.eteach.client.MainApp;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class Veranstaltung_Collection_Controller extends Veranstaltung_Controller implements Initializable {
    File selectedFile;
    //<editor-fold desc="Declaration JavaFX Controls">
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

    @FXML
    private Label lblTitel;

    @FXML
    private Label lblSemester;

    @FXML
    private Label lblVeranstaltungstyp;

    @FXML
    private Label lblBeschreibung;

    @FXML
    private TableView<Ereignis> tblTermine;

    @FXML
    private TableColumn<Ereignis, String> columnTerminTitel;

    @FXML
    private TableColumn<Ereignis, String> dateStartDatum;

    @FXML
    private TableColumn<Ereignis, String> dateEndDatum;

    @FXML
    private Button btnAddTeilnehmer;

    @FXML
    private Button btnDeleteTeilnehmer;

    @FXML
    public TableView<Nutzer> tblTeilnehmer;

    @FXML
    private TableColumn<Nutzer, String> columnVorname;

    @FXML
    private TableColumn<Nutzer, String> columnNachname;

    @FXML
    private TableColumn<Nutzer, String> columnTyp;

    @FXML
    public TableView<Material> tblMaterial;

    @FXML
    private TableColumn<Material, String> columnBezeichnung;

    @FXML
    private Button btnExportCsv;

    @FXML
    private TableColumn<Material, String> columnAnmerkungen;

    @FXML
    public TableView<Quiz> tblQuiz;

    @FXML
    public TableColumn<Quiz,String> tbcQuiz;

    private String[] csvInhalt;
    private Veranstaltung veranstaltung;
    // beim jar datei erstellen ob hierdurch probleme auftreten.
    private File csvTemplate = new File("Client/src/main/resources/Csv/Csv_Template.csv");


    @FXML
    public Button btnQuizNeu;
    @FXML
    public Button btnXml;

    @FXML
    void handlebtnAenderungenSpeichernButtonAction(ActionEvent event)
    {

    }
    //</editor-fold>

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        Context.currentVeranstaltung = null;

        Nutzer_Controller nutzerController = new Nutzer_Controller();

        FilteredList<Veranstaltung> Kurslist =new FilteredList<> (nutzerController.meineVeranstaltungen(currentNutzer));
        Kurslist.setPredicate(t -> t.getTyp() != Veranstaltungstyp.PROJEKTGRUPPE);
        comboVeranstaltungen.setItems(Kurslist);//currentNutzer.getVeranstaltungslist

        // PREP Tables
        tblMaterial.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // MATERIAL
        columnBezeichnung.setCellValueFactory(new PropertyValueFactory<>("bezeichnung"));
        columnAnmerkungen.setCellValueFactory(new PropertyValueFactory<>("anmerkungen"));

        // TEILNEHMER
        columnVorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        columnNachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        columnTyp.setCellValueFactory(new PropertyValueFactory<>("nutzer_typ"));


        // TERMINE
        columnTerminTitel.setCellValueFactory(new PropertyValueFactory<>("titel"));
        dateStartDatum.setCellValueFactory(new PropertyValueFactory<>("startDatum"));
        dateEndDatum.setCellValueFactory(new PropertyValueFactory<>("startZeit"));

        //Quiz
        tbcQuiz.setCellValueFactory((new PropertyValueFactory<>("name")));


        if(currentStudent != null)
        {
            btnAddTeilnehmer.setDisable(true);
            btnAddTeilnehmer.setVisible(false);
            btnDeleteTeilnehmer.setDisable(true);
            btnDeleteTeilnehmer.setVisible(false);

            btnAddMaterial.setDisable(true);
            btnAddMaterial.setVisible(false);
            btnEditMaterial.setDisable(true);
            btnEditMaterial.setVisible(false);
            btnDeleteMaterial.setDisable(true);
            btnDeleteMaterial.setVisible(false);

            btnAddLehrveranstaltung.setDisable(true);
            btnAddLehrveranstaltung.setVisible(false);
            btnEditVeranstaltung.setDisable(true);
            btnEditVeranstaltung.setVisible(false);
            btnDeleteLehrveranstaltung.setDisable(true);
            btnDeleteLehrveranstaltung.setVisible(false);

            btnImportCSV.setDisable(true);
            btnImportCSV.setVisible(false);
            btnExportCsv.setDisable(true);
            btnExportCsv.setVisible(false);



            }
        btnQuizNeu.setDisable(true);
        btnQuizNeu.setVisible(false);
        btnXml.setDisable(true);
        btnXml.setVisible(false);
    }

    @FXML
    public void handleDownloadMaterialButtonAction(ActionEvent event){
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
    void handleExportCSVButtonAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();

        //Filter für CSV dateien
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV datei (*.csv)", "*.csv" );
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialFileName(csvTemplate.getName());

        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            writeTextToFile(FileUtils.readFileToByteArray(csvTemplate), file.getPath());
            showInfoWindows("Datei erfolgreich gespeichert", "Die Datei " + file.getName() + " wurde am Ort " + file.getPath() + " abgelegt");
        }
    }

    @FXML
    void handleComboBoxVeranstaltungChange(ActionEvent event) {
        // Set and load Veranstaltung
        setCurrentVeranstaltung(comboVeranstaltungen.getValue());
        lblTitel.setText(getCurrentVeranstaltung().getTitel());
        lblBeschreibung.setText(getCurrentVeranstaltung().getBeschreibung());
        lblSemester.setText(getCurrentVeranstaltung().getSemester().getStringValue());
        lblVeranstaltungstyp.setText(getCurrentVeranstaltung().getVeranstaltungstyp().getStringValue());
        lblOrganisator.setText(getCurrentVeranstaltung().getOrganisator().toString());

        // Load related Data
        tblMaterial.setItems(FXCollections.observableArrayList(getCurrentVeranstaltung().getMaterialList()));
        tblTeilnehmer.setItems(FXCollections.observableArrayList(getCurrentVeranstaltung().getVeranstaltungsteilnehmerList()));
//        tblTermine.setItems(FXCollections.observableArrayList(getCurrentVeranstaltung().getEreignisList()));


        //QUiz
        if(currentLehrender != null)
        {
            btnQuizNeu.setVisible(true);
            btnXml.setVisible(true);
            btnQuizNeu.setDisable(false);
            btnXml.setDisable(false);
        }


        Quiz_Controller quizC = new de.unidue.SEP.eteach.client.Controller.Quiz_Controller();
        FilteredList<Quiz> quizList =new FilteredList<> (quizC.getQuizList());
        quizList.setPredicate(t -> t.passendeVeranstaltung.getId() == Context.currentVeranstaltung.getId());
        tblQuiz.setItems(quizList);




        columnVorname.setCellFactory((column) -> {
             TableCell<Nutzer,String> tbc= new TableCell<Nutzer,String>() {
                 @Override
                 public void updateItem(String item, boolean empty)
                 {
                     super.updateItem(item, empty);

                    setText(item);

                     if (item != null && item.equals(currentNutzer.getVorname()) )
                     {

                        this.setStyle("-fx-font-size: 16;  -fx-background-color: orange");
                     }

                 }
             };
             return tbc;
         });
        columnNachname.setCellFactory((column) -> {
            TableCell<Nutzer,String> tbc= new TableCell<Nutzer,String>() {
                @Override
                public void updateItem(String item, boolean empty)
                {
                    super.updateItem(item, empty);

                    setText(item);

                    if (item != null && item.equals(currentNutzer.getNachname()) )
                    {

                        this.setStyle("-fx-font-size: 16;  -fx-background-color: orange");
                    }

                }
            };
            return tbc;
        });

        Ereignis_Controller eC = new Ereignis_Controller();
        tblTermine.setItems(eC.getVeranstaltungKalender(Context.currentVeranstaltung));

    }

    @FXML
    void handleImportCSVButtonAction(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);
        int counter = 0;

        if(checkCsv(selectedFile.getName())){
            veranstaltung=new Veranstaltung();
            Veranstaltung persistentVeranstaltung;
            BufferedReader reader = null;
            String line = "";
            try{
                reader = new BufferedReader(new FileReader(selectedFile.getAbsolutePath()));
                line = reader.readLine();

                while((line = reader.readLine()) != null){
                    csvInhalt = line.split(";");

                    for(String index : csvInhalt){

                        if (sommerSemesterFormat(csvInhalt[2].toLowerCase())){
                            String jahr[] = csvInhalt[2].split("\\s");
                            if (jahr[1].contains("21")){
                                veranstaltung.setSemester(Semester.SOSE21);
                            }
                            else if(jahr[1].contains("22")){
                                veranstaltung.setSemester(Semester.SOSE22);
                            }
                            else if(jahr[1].contains("23")){
                                veranstaltung.setSemester(Semester.SOSE23);
                            }
                            else if(jahr[1].contains("24")){
                                veranstaltung.setSemester(Semester.SOSE24);
                            }
                        }
                        else if(winterSemesterFormat(csvInhalt[2].toLowerCase())){
                            String jahr[] = csvInhalt[2].split("\\s");
                            if (jahr[1].contains("21") && jahr[1].contains("22")){
                                veranstaltung.setSemester(Semester.WISE2122);
                            }
                            else if (jahr[1].contains("22") && jahr[1].contains("23")){
                                veranstaltung.setSemester(Semester.WISE2223);
                            }
                            else if (jahr[1].contains("23") && jahr[1].contains("24")){
                                veranstaltung.setSemester(Semester.WISE2324);
                            }
                            else if (jahr[1].contains("24") && jahr[1].contains("25")){
                                veranstaltung.setSemester(Semester.WISE2425);
                            }
                        }

//                         veranstaltung=new Veranstaltung();
                        // Veranstaltung persistentVeranstaltung;
                        veranstaltung.setTitel(csvInhalt[0]);
                        veranstaltung.setBeschreibung("");
                        if (csvInhalt[1].equalsIgnoreCase(Veranstaltungstyp.SEMINAR.toString())){
                            veranstaltung.setVeranstaltungstyp(Veranstaltungstyp.SEMINAR);
                        }
                        else if(csvInhalt[1].equalsIgnoreCase(Veranstaltungstyp.VORLESUNG.toString())){
                            veranstaltung.setVeranstaltungstyp(Veranstaltungstyp.VORLESUNG);
                        }
                    }

                    FilteredList<Veranstaltung> list = new FilteredList<> (getVeranstaltungList());
                    list.setPredicate(t -> t.getTitel().equalsIgnoreCase(csvInhalt[0]));
                    if (list.isEmpty()) {
                        Nutzer_Controller nutzerController = new Nutzer_Controller();
                        persistentVeranstaltung = saveVeranstaltung(veranstaltung);
                        veranstaltungsorganisatorFestlegen(persistentVeranstaltung.getId(), getCurrentLehrender().getId());
                        nutzerController.nutzerInVeranstaltungEinschreiben(getCurrentLehrender().getId(), persistentVeranstaltung.getId());
                        counter++;
                    }
                    else{
                        showInfoWindows("Veranstaltung existiert bereits", "Es existiert bereits eine veranstaltung mit dem Namen " +csvInhalt[0]);
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                try {
                    reader.close();
                    showInfoWindows("Csv Upload erfolgreich", "Es wurden erfolgreich: " + counter + " Veranstaltungen angelegt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        else {
            showInfoWindows("Falsches Dateiformat", "Es werden nur Csv Dateien angenommen");
        }
    }

    @FXML
    void handleRefreshAllButtonAction(ActionEvent event)
    {

        tblMaterial.setItems(FXCollections.observableArrayList(getCurrentVeranstaltung().getMaterialList()));
        tblTeilnehmer.setItems(FXCollections.observableArrayList(getCurrentVeranstaltung().getVeranstaltungsteilnehmerList()));
        Quiz_Controller quizC = new de.unidue.SEP.eteach.client.Controller.Quiz_Controller();
        FilteredList<Quiz> quizList =new FilteredList<> (quizC.getQuizList());
        quizList.setPredicate(t -> t.passendeVeranstaltung.getId() == Context.currentVeranstaltung.getId());
        tblQuiz.setItems(quizList);
    }

    @FXML
    void handleAddLehrveranstaltungButtonAction(ActionEvent event) throws IOException {
        Veranstaltung veranstaltung = new Veranstaltung(getCurrentNutzer());
        setCurrentVeranstaltung(veranstaltung);
        openModalForm("veranstaltung_single", "Neue Veranstaltung anlegen...");
    }

//      Code konnte nicht getestet werden da immer ein Error auftritt sobald die veranstaltungs collection geöffnet werden sollte.
//      Error: javafx.fxml.LoadException: Error resolving onAction='#deleteVeranstaltungButtonAction', either the event handler is not in the Namespace or there is an error in the script.
//      Damit Veranstaltung collection aufgerufen werden kann bei Button 3 in Scenebuilder das on Action event gelöscht (handleDeleteVeranstaltungButtonAction)

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
    void handleAddMaterialButtonAction(ActionEvent event) throws IOException {
        if (getCurrentVeranstaltung() != null) {
            setCurrentMode("New");
            openModalForm("material_single", "Neues Material anlegen...");
        } else {
            showInfoWindows("Fehler", "Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
        }
    }

    @FXML
    void handleAddTeilnehmerButtonAction(ActionEvent event) {
        if (getCurrentVeranstaltung() != null) {
            openModalForm("nutzer_list", "Auswahl Studenten");
        } else {
            showInfoWindows("Fehler", "Bitte erst eine Veranstaltung wählen. Dann erneut versuchen.");
        }
    }

    @FXML
    void handleAddTerminButtonAction(ActionEvent event)
    {
        if (getCurrentVeranstaltung() != null)
        {
            Context.ereignisZeigen = null;
            openModalForm("ereignis_single", "Neuen Termin anlegen...");
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
    void handleDeleteTeilnehmerButtonAction(ActionEvent event) throws IOException {
        Nutzer nutzer=tblTeilnehmer.getSelectionModel().getSelectedItem();
        getNutzerEndpoint().nutzerAusVeranstaltungEntfernen(MainApp.getAuthHeader(),nutzer.getId(),getCurrentVeranstaltung().getId()).execute();
    }

    @FXML
    void handleDeleteTerminButtonAction(ActionEvent event) {
        getCurrentVeranstaltung().getEreignisList().remove(tblTermine.getSelectionModel().getSelectedItem());
        tblTermine.setItems(FXCollections.observableArrayList(getCurrentVeranstaltung().getEreignisList()));
    }

    @FXML
    void handleEditMaterialButtonAction(ActionEvent event) {
        setCurrentMaterial(tblMaterial.getSelectionModel().getSelectedItem());
        setCurrentMode("Edit");
        openModalForm("material_single", "Material bearbeiten...");
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
    void handleEditTeilnehmerButtonAction(ActionEvent event) {

    }

    @FXML
    void handleEditTerminButtonAction(ActionEvent event) {

    }

    public void tblTeilnehmerClicked(MouseEvent event) throws IOException {
        Student_Controller student_controller = new Student_Controller();
        Lehrender_Controller lehrender_controller = new Lehrender_Controller();
        Nutzer nutzer = tblTeilnehmer.getSelectionModel().getSelectedItem();

        if(nutzer.getNutzer_typ().equals("Student"))
        {
            selectedStudent = student_controller.getStudentById(nutzer.getId());
            openModalForm("student_single_view", "Profil von :" + nutzer.getVorname() + " " + nutzer.getNachname());
            selectedStudent = null;
        }
        else
        {
            selectedLehrender = lehrender_controller.getLehrenderById(nutzer.getId());
            openModalForm("lehrender_single", "Profil von :" + nutzer.getVorname()+ " " + nutzer.getNachname());
            selectedLehrender = null;
        }
        tblTeilnehmer.getSelectionModel().clearSelection();
    }

    public void update()
    {
        tblMaterial.refresh();
        tblTeilnehmer.refresh();
    }

    public void tblQuizClicked(MouseEvent event)
    {
        currentQuiz= tblQuiz.getSelectionModel().getSelectedItem();
        openModalForm("Quiz", "Quiz: " + currentQuiz.name);
        tblQuiz.getSelectionModel().clearSelection();
    }

    public void btnQuizPressed(ActionEvent event)
    {
        currentQuiz = null;
        openModalForm("Quiz", "Quiz: ");
    }

    public void xmlUpload(ActionEvent event) throws IOException, JAXBException {
        selectedFile = null;
        FileChooser chooser = new FileChooser();
        selectedFile = chooser.showOpenDialog(null);
        if (checkXml(selectedFile.getName()))
        {

            String xml = Files.readString(Path.of(selectedFile.toString()));
            StringReader reader = new StringReader(xml);

            JAXBContext jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {Quiz.class}, null);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            Quiz_Controller qC = new Quiz_Controller();

            qC.saveQuizwithVeranstaltung((Quiz) unmarshaller.unmarshal(reader),Context.currentVeranstaltung);

        }
        else
        {
            showInfoWindows("Falsches Format", "Es werden nur XML dateien akzeptiert");
        }
        selectedFile = null;
    }

    public void tblTermineClicked(MouseEvent event)
    {
        if(tblTermine.getSelectionModel().getSelectedItem() != null)
        {
            Context.ereignisZeigen = tblTermine.getSelectionModel().getSelectedItem();
            openModalForm("ereignis_single", Context.ereignisZeigen.getTitel());
        }
    }
}