package de.unidue.SEP.eteach.client.Entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatRaum {

    private int id;
    private List<ChatNachricht> chatNachrichten;

    public ChatRaum() {
    }
}
