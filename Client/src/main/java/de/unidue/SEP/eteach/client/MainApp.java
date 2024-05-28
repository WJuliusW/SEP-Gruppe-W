package de.unidue.SEP.eteach.client;

import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;

public class MainApp extends Application {
    public static Stage stage;
    public static Scene scene;

    //<editor-fold desc="CONSTANTS">
    public static final String imgPath = "/Images/";
    public static final String imgPathGreen = "/Images/Green";
    public static final String imgPathGray = "/Images/Gray_24/";
    public static final String imgPathWhite = "/Images/white/";
    public static final String BASE_URL = "http://localhost:8080";
    public static final String username = "abc";
    public static final String password = "abcdfg";
    //</editor-fold>

    public static Stage window;
    public static BorderPane mainPane;//MAINPAIN

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LOGINPage.fxml"));
        scene = new Scene(root);
        stage.setMaximized(false);
        window = stage;
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(imgPath+"teacher_16px.png")));
        stage.setTitle("eTeach");
        ETeach_Controller eTeach_controller=new ETeach_Controller();
        eTeach_controller.setMainStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static String getAuthHeader() {
        byte[] authHeaderB64 = Base64.encodeBase64((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(authHeaderB64);
        return authHeader;
    }

    public static Scene getScene() {
        return scene;
    }


    public static String getBaseUrl() {
        return BASE_URL;
    }

}
