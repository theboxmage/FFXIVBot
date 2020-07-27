package com.boxfort.demo.Abstracts;

import com.boxfort.demo.Interfaces.DiscordCommand;
import lombok.Data;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Data
public class AbstractCommand implements DiscordCommand {

    private final String alias;

    public AbstractCommand(String alias)
    {
        this.alias = alias.toLowerCase();
    }

    public void execute(CustomEvent event)
    {
        throw new NotImplementedException();
    }
}