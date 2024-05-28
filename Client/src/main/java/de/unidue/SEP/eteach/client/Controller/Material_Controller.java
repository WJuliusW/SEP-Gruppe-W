package de.unidue.SEP.eteach.client.Controller;

import de.unidue.SEP.eteach.client.Endpoints.MaterialEndpoint;
import de.unidue.SEP.eteach.client.Entities.Material;
import de.unidue.SEP.eteach.client.MainApp;
import de.unidue.SEP.eteach.client.ViewController.ETeach_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import retrofit2.Response;

import java.io.IOException;

public class Material_Controller extends ETeach_Controller {
    private Response<Material[]> response;
    private Response<Material> responseSingle;

    public ObservableList<Material> getMaterialList() {
        Material[] materials;
        MaterialEndpoint materialEndpoint = getMaterialEndpoint();
        try {
            response = materialEndpoint.getAllMaterial(MainApp.getAuthHeader()).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        materials = response.body();
        ObservableList<Material> materialsList = FXCollections.observableArrayList(materials);
        return materialsList;
    }

    public Material saveMaterial(Material material) throws IOException {
        responseSingle = getMaterialEndpoint().createMaterial(MainApp.getAuthHeader(), material).execute();
        return responseSingle.body();
    }

    public Material editMaterial(Material material) throws IOException {
        return getMaterialEndpoint().editMaterial(MainApp.getAuthHeader(), material, material.getId()).execute().body();
    }


    public void deleteMaterial(Material material) throws IOException {
        responseSingle = getMaterialEndpoint().deleteMaterial(MainApp.getAuthHeader(), material.getId()).execute();
    }

}

