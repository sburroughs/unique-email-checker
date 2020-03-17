package com.shaneburroughs.emailuniquenessservice.service;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class EmailUniquenessService {

    private static final String EMAIL_REGEX = "^(.+)@(.+)$";

    public long countUnique(List<String> emailAddresses){
        Assert.notNull(emailAddresses, "emailAddresses can not be null");
        return emailAddresses.stream()
                .filter(this::filterInvalidEmails)
                .map(this::filterCharacterPeriod)
                .map(this::filterCharactersAfterPlus)
                .distinct()
                .count();
    }

    private boolean filterInvalidEmails(String s) {
        return s != null && s.matches(EMAIL_REGEX);
    }

    private String filterCharacterPeriod(String s) {
        return s.replaceAll("\\.","");
    }

    private String filterCharactersAfterPlus(String s) {
        if(s.contains("+")){
            int lastEmailCharacterindex = s.indexOf("+");
            int atSympbolIndex = s.lastIndexOf("@");
            return s.substring(0, lastEmailCharacterindex) + s.substring(atSympbolIndex);
        }
        return s;
    }
}
