package com.boxfort.demo.Interfaces;

import com.boxfort.demo.Abstracts.CustomEvent;

public interface DiscordCommand {
    void execute(CustomEvent event);

    String getAlias();
}
