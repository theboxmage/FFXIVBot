package com.boxfort.demo.Commands;

import com.boxfort.demo.Abstracts.AbstractCommand;
import com.boxfort.demo.Abstracts.CustomEvent;
import com.boxfort.demo.Services.FIVAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FreeCompanyCommand extends AbstractCommand {

    FIVAPIService fivapiService;

    @Autowired
    public FreeCompanyCommand() {
        super("FC");
    }

    @Override
    public void execute(CustomEvent event) {

    }
}
