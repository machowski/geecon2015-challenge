package pl.allegro.promo.geecon2015.domain.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DefaultUserTransactions implements UserTransactions {

    private final List<UserTransaction> transactions = new ArrayList<>();

    public DefaultUserTransactions(@JsonProperty("transactions") List<UserTransaction> transactions) {
        this.transactions.addAll(transactions);
    }


    public List<UserTransaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    @Override
    public BigDecimal total() {
        return transactions.stream()
            .map(UserTransaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
