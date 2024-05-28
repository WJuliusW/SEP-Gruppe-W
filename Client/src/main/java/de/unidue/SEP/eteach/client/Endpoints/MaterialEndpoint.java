package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Material;
import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import retrofit2.Call;
import retrofit2.http.*;
public interface MaterialEndpoint {
    @GET("/eteach/material/{id}")
    Call<Material> getMaterialById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/material/all")
    Call<Material[]> getAllMaterial(@Header("Authorization") String auth);

    @POST("/eteach/material/new")
    Call<Material> createMaterial(@Header("Authorization") String auth, @Body Material material);

    @PUT("/eteach/material/{materialId}/veranstaltung/{veranstaltungId}")
    Call<Veranstaltung> veranstaltungsmaterialHinzufuegen(
            @Header("Authorization") String auth,
            @Path("materialId") Integer materialId,
            @Path("veranstaltungId") Integer veranstaltungId);

    @PUT("/eteach/material/edit/{id}")
    Call<Material> editMaterial(
            @Header("Authorization") String auth,
            @Body Material material,
            @Path("id") Integer id);

    @DELETE("/eteach/material/{id}")
    Call<Material> deleteMaterial(
            @Header("Authorization") String auth,
            @Path("id") Integer id);

    @DELETE("/eteach/material/{materialId}/veranstaltung/{veranstaltungId}")
    Call<Veranstaltung> veranstaltungsmaterialEntfernen(
            @Header("Authorization") String auth,
            @Path("materialId") Integer materialId,
            @Path("veranstaltungId") Integer veranstaltungId);
}
