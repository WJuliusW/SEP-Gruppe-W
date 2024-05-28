package de.unidue.SEP.eteach.client.Endpoints;

import de.unidue.SEP.eteach.client.Entities.Abgabe;
import de.unidue.SEP.eteach.client.Entities.PrivatNachricht;
import retrofit2.Call;
import retrofit2.http.*;

public interface PrivatNachrichtenEndpoint {

    @GET("/eteach/privatChat/nachrichten/{empfaengerId}")
    Call<PrivatNachricht[]> getPrivatNachrichtenByEmpfaenger(@Header("Authorization") String auth, @Path("veranstaltungId") Integer empfaengerId);

    @GET("/eteach/privatChat/freundschaftsanfragen/{empfaengerId}")
    Call<PrivatNachricht[]> getFreunschaftsanfragenByEmpfaenger(@Header("Authorization") String auth, @Path("veranstaltungId") Integer empfaengerId);

    @GET("/eteach/privatChat/nachrichten/{absenderId}/{empfaengerId}")
    Call<PrivatNachricht[]> getPrivatNachrichtenByAbsenderOrEmpfaenger(@Header("Authorization") String auth, @Path("absenderId") Integer absenderId, @Path("empfaengerId") Integer empfaengerId);

    @POST("/eteach/privatChat/nachrichten/{absenderId}/{empfaengerId}/new")
    Call<PrivatNachricht> createPrivatNachricht(@Header("Authorization") String auth, @Path("absenderId") Integer absenderId, @Path("empfaengerId") Integer empfaengerId, @Body PrivatNachricht privatNachricht);

    @POST("/eteach/privatChat/freundschaftsanfragen/{absenderId}/{empfaengerId}/new")
    Call<PrivatNachricht> createFreunschaftsanfrage(@Header("Authorization") String auth, @Path("absenderId") Integer absenderId, @Path("empfaengerId") Integer empfaengerId, @Body PrivatNachricht privatNachricht);

    @DELETE("/eteach/privatChat/{id}")
    Call<PrivatNachricht> deletePrivatNachricht(@Header("Authorization") String auth, @Path("id") Integer id);

    //JULIUS
    @GET("/eteach/privatChat/all")
    Call<PrivatNachricht[]> getAllPrivatNachricht(@Header("Authorization") String auth);
}