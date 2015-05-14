package pl.allegro.promo.geecon2015.domain.transaction;

import java.math.BigDecimal;

public class MissingUserTransactions implements UserTransactions {

    @Override
    public BigDecimal total() {
        return null;
    }
}
