package com.boxfort.demo.Commands;

import com.boxfort.demo.Abstracts.AbstractCommand;
import com.boxfort.demo.Abstracts.CustomEvent;
import lombok.Getter;
import lombok.Setter;
import org.javacord.api.entity.channel.ServerVoiceChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class FiredrillCommand extends AbstractCommand {


    @Autowired
    public FiredrillCommand()
    {
        super("ChineseFireDrill");
    }


    @Override
    public void execute(CustomEvent event) {
        MessageCreateEvent messageCreateEvent = (MessageCreateEvent)event.getEvent();
        String messageArgs[] = messageCreateEvent.getMessageContent().split(" ");
        Long userID = messageCreateEvent.getMessageAuthor().getId();

        if(messageArgs.length > 1 && messageArgs[1].contains("@")) {
            userID = Long.parseLong(messageArgs[1].substring(3, messageArgs[1].length()-1));
        }
        Long finalUserID = userID;
        messageCreateEvent.getServer().ifPresent(server -> {
            List<ServerVoiceChannel> voiceChannels = server.getVoiceChannels();
            ServerVoiceChannel mainVoiceChannel = null;
            for (ServerVoiceChannel voiceChannel : voiceChannels) {
                if(voiceChannel.isConnected(finalUserID))
                {
                    mainVoiceChannel = voiceChannel;
                    break;
                }
            }
            if(mainVoiceChannel != null)
            {
                Set<UserStatus> userStatuses = new HashSet<UserStatus>();

                ServerVoiceChannel finalMainVoiceChannel = mainVoiceChannel;

                mainVoiceChannel.getConnectedUsers().forEach(user -> {
                    userStatuses.add(new UserStatus(user, finalMainVoiceChannel));
                });
                for(int i = 0; i < 3; i++) {
                    userStatuses.forEach(userStatus -> {
                        server.moveUser(userStatus.getUser(), getNewRandomVoiceChannel(voiceChannels, userStatus));
                    });
                }
            }
        });
        messageCreateEvent.deleteMessage();
    }

    private ServerVoiceChannel getNewRandomVoiceChannel(List<ServerVoiceChannel> voiceChannels, UserStatus userStatus) {
        Random random = new Random();
        ServerVoiceChannel serverVoiceChannel = null;
        if(voiceChannels.size() == 1) return voiceChannels.get(0);
        do{
            int index = random.nextInt(voiceChannels.size());
            serverVoiceChannel = voiceChannels.get(index);
        } while(serverVoiceChannel != null && serverVoiceChannel.getId() == userStatus.getChannel().getId());
        userStatus.setChannel(serverVoiceChannel);
        return serverVoiceChannel;
    }

    private class UserStatus {
        @Getter
        @Setter
        private ServerVoiceChannel channel;
        @Getter
        private final User user;
        public UserStatus(User user, ServerVoiceChannel channel)
        {
            this.channel = channel;
            this.user = user;
        }
    }
}
