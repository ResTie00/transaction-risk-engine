package com.project.fintechprac.strategies;


import com.project.fintechprac.models.FiredRule;
import com.project.fintechprac.models.Rule;
import com.project.fintechprac.models.RuleType;
import com.project.fintechprac.models.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Component
@Slf4j
public class AmountThresholdRule implements RuleEvaluator {
    private static final String PARAM_THRESHOLD = "threshold";
    private static final String PARAM_CURRENCY = "currency";

    @Override
    public RuleType getType() {
        return RuleType.AMOUNT_THRESHOLD;
    }

    @Override
    public Optional<FiredRule> evaluate(Transaction transaction, Rule rule) {
        Map<String, Object> params = rule.getParams();
        Object currency = params.get(PARAM_CURRENCY);
        BigDecimal threshold = extractBigDecimal(params.get(PARAM_THRESHOLD));

        if(currency == null || threshold == null) {
            log.warn("Rule '{}' is misconfigured: threshold and currency are required", rule.getName());
            return Optional.empty();
        }
        if (!currency.toString().equalsIgnoreCase(transaction.getCurrency())) {
            log.warn("Currencies must be same");
            return Optional.empty();
        }
        if (transaction.getAmount().compareTo(threshold) > 0) {
            String reason = "Amount %s %s exceeds threshold %s".formatted(
                    transaction.getAmount().toPlainString(),
                    transaction.getCurrency(),
                    threshold.toPlainString());
            return Optional.of(new FiredRule(rule.getName(), rule.getWeight(), reason));

        }
        return Optional.empty();

    }

    private BigDecimal extractBigDecimal(Object value) {
        if (value == null) return null;

        try {
            return new BigDecimal(value.toString().trim());
        }catch (NumberFormatException e){
            return null;
        }
    }
}
