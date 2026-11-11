package com.project.fintechprac.strategies;


import com.project.fintechprac.models.FiredRule;
import com.project.fintechprac.models.Rule;
import com.project.fintechprac.models.RuleType;
import com.project.fintechprac.models.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AmountThresholdRuleTest {
    private final AmountThresholdRule evaluator = new AmountThresholdRule();

    private final Rule rule = Rule.builder()
            .name("High amount (USD)")
            .type(RuleType.AMOUNT_THRESHOLD)
            .params(Map.of("threshold", 10000, "currency", "USD"))
            .weight(50)
            .enabled(true)
            .build();

    @Test
    void firesWhenAmountExceedsThreshold(){
        Transaction tx = Transaction.builder()
                .amount(new BigDecimal("12000"))
                .currency("USD")
                .build();
        Optional<FiredRule> result = evaluator.evaluate(tx, rule);
        assertThat(result).isPresent();
        assertThat(result.get().weight()).isEqualTo(50);
        assertThat(result.get().message()).contains("12000");
    }

    @Test
    void doesNotFireWhenAmountEqualsThreshold(){
        Transaction tx = Transaction.builder()
                .amount(new BigDecimal(10000))
                .currency("USD")
                .build();
        Optional<FiredRule> result = evaluator.evaluate(tx, rule);
        assertThat(result).isEmpty();
    }

    @Test
    void doesNotFireWhenAmountBelowThreshold(){
        Transaction tx = Transaction.builder()
                .amount(new BigDecimal(9000))
                .currency("USD")
                .build();
        Optional<FiredRule> result = evaluator.evaluate(tx, rule);
        assertThat(result).isEmpty();
    }

    @Test
    void doesNotFireWhenCurrencyDiffers(){
        Transaction tx = Transaction.builder()
                .amount(new BigDecimal(12000))
                .currency("KGS")
                .build();
        Optional<FiredRule> result = evaluator.evaluate(tx, rule);
        assertThat(result).isEmpty();
    }

    @Test
    void doesNotFireWhenThresholdMissing(){
        Rule ruleWithoutThreshold = Rule.builder()
                .name("Broken Rule")
                .type(RuleType.AMOUNT_THRESHOLD)
                .params(Map.of("currency", "USD"))
                .weight(50)
                .enabled(true)
                .build();

        Transaction tx = Transaction.builder()
                .amount(new BigDecimal(12000))
                .currency("USD")
                .build();
        assertThat(evaluator.evaluate(tx, ruleWithoutThreshold)).isEmpty();
    }

    @Test
    void doesNotFireWhenCurrencyMissing(){
        Rule ruleWithoutCurrency = Rule.builder()
                .name("Without curr")
                .type(RuleType.AMOUNT_THRESHOLD)
                .params(Map.of("threshold","10000" ))
                .weight(50)
                .enabled(true)
                .build();

        Transaction tx = Transaction.builder()
                .amount(new BigDecimal(12000))
                .currency("USD")
                .build();

        assertThat(evaluator.evaluate(tx, ruleWithoutCurrency)).isEmpty();
    }


    @Test
    void doesNotFireWhenAmountEqualsThresholdWithScale(){
        Transaction tx = Transaction.builder()
                .amount(new BigDecimal(10000.0000))
                .currency("USD")
                .build();
        Optional<FiredRule> result = evaluator.evaluate(tx, rule);
        assertThat(result).isEmpty();
    }

    @Test
    void doesFireWhenAmountSlightlyGreaterThanThresholdWithScale(){
        Transaction tx = Transaction.builder()
                .amount(new BigDecimal(10000.0001))
                .currency("USD")
                .build();
        Optional<FiredRule> result = evaluator.evaluate(tx, rule);
        assertThat(result).isPresent();
        }


}
