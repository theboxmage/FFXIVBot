package com.boxfort.demo.Abstracts;

import lombok.Getter;
import org.javacord.api.event.Event;
import org.javacord.api.event.message.MessageCreateEvent;
import sun.plugin2.message.Message;

public class CustomEvent {

    @Getter
    private final Event event;
    public CustomEvent(Event event)
    {
        this.event = event;
    }

    public String[] getArgs(){
       if(event instanceof MessageCreateEvent){
           MessageCreateEvent messageCreateEvent = (MessageCreateEvent)event;
           return messageCreateEvent.getMessageContent().substring(1).split(" ");
       }
       return new String[0];
    }
}
