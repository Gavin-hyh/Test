package com.hyh.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ShowDatabases {

    @RequestMapping
    public String dbNamelist(){


        return null;
    }
}
