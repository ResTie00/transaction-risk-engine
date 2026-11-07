package com.project.fintechprac.exceptions;

public class DuplicateAccountException extends RuntimeException {
    public DuplicateAccountException(String externalId) {
        super("Account with externalId " + externalId + " already exists.");
    }
}

