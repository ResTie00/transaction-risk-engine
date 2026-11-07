package com.project.fintechprac.exceptions;

public class DuplicateTransactionException extends RuntimeException {
    public DuplicateTransactionException(String externalId)  {
        super("Transaction with external id: " + externalId + " already exists");
    }
}
