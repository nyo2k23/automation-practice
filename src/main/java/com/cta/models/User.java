package com.cta.models;

public record User(
        String gender,
        String fName,
        String lName,
        String email,
        String password,
        int dateOfBirth,
        String monthOfBirth,
        int yearOfBirth
) {
}


