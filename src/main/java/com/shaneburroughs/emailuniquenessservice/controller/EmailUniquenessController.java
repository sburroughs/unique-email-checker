package com.shaneburroughs.emailuniquenessservice.controller;


import com.shaneburroughs.emailuniquenessservice.service.EmailUniquenessService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmailUniquenessController {

    private EmailUniquenessService service;

    public EmailUniquenessController(EmailUniquenessService service){
        this.service = service;
    }

    @PostMapping("/count-unique-emails")
    public long countUniqueEmails(@RequestBody List<String> emails){
        return service.countUnique(emails);
    }

}
