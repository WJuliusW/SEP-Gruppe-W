package de.unidue.SEP.eteach.client.Endpoints;

import de.unidue.SEP.eteach.client.Entities.Veranstaltung;
import retrofit2.Call;
import retrofit2.http.*;

public interface VeranstaltungEndpoint {
    @GET("/eteach/veranstaltung/{id}")
    Call<Veranstaltung> getVeranstaltungById(@Header("Authorization") String auth, @Path("id") Integer id);

    @GET("/eteach/veranstaltung/pertitle/{title}")
    Call<Veranstaltung> getVeranstaltungByTitle(@Header("Authorization") String auth, @Path("title") String title);

    @GET("/eteach/veranstaltung/perteilnehmer/{nutzerId}")
    Call <Veranstaltung[]> getVeranstaltungenByTeilnehmer(@Header("Authorization") String auth, @Path("nutzerId") Integer nutzerId);

    @GET("/eteach/veranstaltung/all")
    Call<Veranstaltung[]> getAllVeranstaltung(@Header("Authorization") String auth);

    @POST("/eteach/veranstaltung/new")
    Call<Veranstaltung> createVeranstaltung(@Header("Authorization") String auth, @Body Veranstaltung veranstaltung);

    @PUT("/eteach/veranstaltung/{veranstaltungId}/organisator/{lehrenderId}")
    Call<Veranstaltung> veranstaltungsorganisatorFestlegen(
            @Header("Authorization") String auth,
            @Path("veranstaltungId") Integer veranstaltungId,
            @Path("lehrenderId") Integer lehrenderId);

    @PUT("/eteach/veranstaltung/{id}")
    Call<Veranstaltung> editVeranstaltung(@Header("Authorization") String auth, @Path("id") Integer id);


    @DELETE("/eteach/veranstaltung/{id}")
    Call<Veranstaltung> deleteVeranstaltung(@Header("Authorization") String auth, @Path("id") Integer id);
}
