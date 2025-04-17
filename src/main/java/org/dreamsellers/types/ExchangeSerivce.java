package org.dreamsellers.types;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class ExchangeSerivce {
    private final Map<String, BigDecimal> CACHE = new HashMap<>();
    private final RemoteApiClienct apicliant;

    public ExchangeSerivce(RemoteApiClient a) {
        this.apiclient = a;
    }

    public List<ExchangeRate> getAllRates() {
        todo
    }

    public ExchangeRate getRate(String currencyCode) {
        todo
    }

    public ExchangeRate getExchangeAmount(String currencyCode, BigDecimal amount) {

    }

    public void refreshCache() {
        todo
    }
}
