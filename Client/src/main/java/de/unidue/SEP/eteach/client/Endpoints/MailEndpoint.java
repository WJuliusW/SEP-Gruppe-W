package de.unidue.SEP.eteach.client.Endpoints;


import de.unidue.SEP.eteach.client.Entities.Ereignis;
import de.unidue.SEP.eteach.client.Entities.Mail;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface MailEndpoint {

    @POST("/eteach/mail/send")
    Call<Mail> send(@Header("Authorization") String auth, @Body Mail mail);

    @POST("/eteach/mail/sendReminder")
    Call<Mail> sendReminder(@Header("Authorization") String auth, @Body Ereignis ereignis);
}