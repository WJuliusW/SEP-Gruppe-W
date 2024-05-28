package de.unidue.SEP.eteach.client.Endpoints;

import de.unidue.SEP.eteach.client.Entities.ChatNachricht;
import retrofit2.Call;
import retrofit2.http.*;

public interface ChatRaumEndpoint {

    @GET("/eteach/chatRoom/{veranstaltungId}")
    Call<ChatNachricht[]> getChatByVeranstaltung(@Header("Authorization") String auth, @Path("veranstaltungId") Integer veranstaltungId);

    @POST("/eteach/chatRoom/{veranstaltungId}/{nutzerId}/newMessage")
    Call<ChatNachricht> createChatNachricht(@Header("Authorization") String auth, @Path("veranstaltungId") Integer veranstaltungId, @Path("nutzerId") Integer nutzerId, @Body ChatNachricht chatNachricht);

    @DELETE("/eteach/chatRoom/{veranstaltungId}/clear")
    Call<ChatNachricht[]> clearChatByVeranstaltung(@Header("Authorization") String auth, @Path("veranstaltungId") Integer veranstaltungId);

    @DELETE("/eteach/chatRoom/{id}")
    Call<ChatNachricht> deleteChatNachricht(@Header("Authorization") String auth, @Path("id") Integer id);
}