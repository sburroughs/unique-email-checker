package com.shaneburroughs.emailuniquenessservice.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class EmailUniquenessServiceTest {

    private EmailUniquenessService service = new EmailUniquenessService();

    @Test
    public void countUniqueHappyPath(){
        List<String> emails = List.of("test.email@gmail.com", "test.email+spam@gmail.com", "testemail@gmail.com");
        assertThat(service.countUnique(emails), is(equalTo(1L)));
    }

    @Test
    public void countUniqueInvalidEmailMultiplePlusSymbol(){
        List<String> emails = List.of("testemail@gmail.com", "test.email+all+this+is+removed@gmail.com");
        assertThat(service.countUnique(emails), is(equalTo(1L)));
    }

    @Test
    public void countUniqueInvalidEmailMultiplePeriodSymbol(){
        List<String> emails = List.of("testemail@gmail.com", "t.e.s.t.e.m.a.i.l@gmail.com");
        assertThat(service.countUnique(emails), is(equalTo(1L)));
    }

    @Test
    public void countUniqueInvalidEmailMissingAtSymbol(){
        List<String> emails = List.of("test.emailatgmail.com");
        assertThat(service.countUnique(emails), is(equalTo(0L)));
    }

    @Test
    public void countUniqueInvalidEmailEmpty(){
        List<String> emails = List.of("");
        assertThat(service.countUnique(emails), is(equalTo(0L)));
    }

    @Test
    public void countUniqueInvalidEmailNull(){
        List<String> emails = Arrays.asList("test.email@gmail.com", null);
        assertThat(service.countUnique(emails), is(equalTo(1L)));
    }

    @Test
    public void countUniqueNullRequest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.countUnique(null));
    }
}
