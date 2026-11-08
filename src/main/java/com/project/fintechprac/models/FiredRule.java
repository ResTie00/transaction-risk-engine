package com.project.fintechprac.models;

public record FiredRule(
        String ruleName,
        int weight,
        String message
) {
}
