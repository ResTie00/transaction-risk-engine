package com.project.fintechprac.models;


import lombok.Builder;

@Builder
public record FiredRule(
        String ruleName,
        int weight,
        String message
) {
}
