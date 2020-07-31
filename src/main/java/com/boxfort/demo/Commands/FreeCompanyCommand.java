package com.boxfort.demo.Commands;

import com.boxfort.demo.Abstracts.AbstractCommand;
import com.boxfort.demo.Abstracts.CustomEvent;
import com.boxfort.demo.Services.FIVAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FreeCompanyCommand extends AbstractCommand {

    FIVAPIService fivapiService;
    RestTemplate restTemplate;

    @Autowired
    public FreeCompanyCommand(RestTemplate restTemplate) {
        super("FC");
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(CustomEvent event) {

    }
}
