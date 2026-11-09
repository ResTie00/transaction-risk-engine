package com.project.fintechprac.strategies;

import com.project.fintechprac.models.FiredRule;
import com.project.fintechprac.models.Rule;
import com.project.fintechprac.models.RuleType;
import com.project.fintechprac.models.Transaction;

import java.util.Optional;

public interface RuleEvaluator {
    RuleType getType();

    Optional<FiredRule> evaluate(Transaction transaction, Rule rule);
}
