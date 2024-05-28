package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Nutzer;
import retrofit2.Call;
import retrofit2.http.*;

public interface NutzerEndpoint {
    @GET("/eteach/nutzer/{id}")
    Call<Nutzer> getNutzerById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/nutzer/authenticate/ep/{key}")
    Call<Nutzer> getNutzerByKeyEmailPass(@Header("Authorization") String auth, @Path("key") String key);

    @GET("/eteach/nutzer/authenticate/{email}")
    Call<Nutzer> getNutzerByEmail(@Header("Authorization") String auth, @Path("email") String email);

    @GET("/eteach/nutzer/all")
    Call<Nutzer[]> getAllNutzer(@Header("Authorization") String auth);

    @POST("/eteach/nutzer/new")
    Call<Nutzer> createNutzer(@Header("Authorization") String auth, @Body Nutzer nutzer);

    @PUT("/eteach/nutzer/{id}")
    Call<Nutzer> editNutzer(@Header("Authorization") String auth, @Path("id") Integer id);

    @PUT("/eteach/nutzer/{nutzerId}/veranstaltungen/{veranstaltungId}")
    Call<Nutzer> nutzerInVeranstaltungEinschreiben(
            @Header("Authorization") String auth,
            @Path("nutzerId") Integer nutzerId,
            @Path("veranstaltungId") Integer veranstaltungId);

    @DELETE("/eteach/nutzer/{id}")
    Call<Nutzer> deleteNutzer(@Header("Authorization") String auth, @Path("id") Integer id);

    @DELETE("/eteach/nutzer/{nutzerId}/veranstaltungen/{veranstaltungId}")
    Call<Nutzer> nutzerAusVeranstaltungEntfernen(
            @Header("Authorization") String auth,
            @Path("nutzerId") Integer nutzerId,
            @Path("veranstaltungId") Integer veranstaltungId);
}
