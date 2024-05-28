package de.unidue.SEP.eteach.client.ViewController;

import de.unidue.SEP.eteach.client.Controller.Student_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Student_List_Large_Controller extends Student_Controller implements Initializable {

    @FXML
    private Label lblHeadline;

    @FXML
    private VBox paneStudents;

    @FXML
    private Button btnAbbruch;

    @FXML
    private Button btnSpeichern;

    @FXML
    void handleAbbruchButtonAction(ActionEvent event) {

    }

    @FXML
    void handleSpeichernButtonAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Load components
        try {
            changePane(paneStudents, "student_list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePane(VBox pane, String fxml) throws IOException {
        URL fxmlLocation = getClass().getClassLoader().getResource(fxml + ".fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        try {
            pane.getChildren().add(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


