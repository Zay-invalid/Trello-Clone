package com.zay.trelloClone.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class MainController {
    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public Map<String,String> getInfo(){
        Map<String,String> info= new HashMap<>();
        info.put("name","Trello Rest API");
        info.put("description","A backend copy version of Trello app by Zayar Htet");
        info.put("version",appVersion);
        return info;
    }
}
