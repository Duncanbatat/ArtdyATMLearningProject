package com.artdy;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CurrencyManipulatorFactory {
    private CurrencyManipulatorFactory() {
    }

    private static Map<String, CurrencyManipulator> map = new HashMap<>();

    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
        if (map.containsKey(currencyCode)) {
            return map.get(currencyCode);
        }
        map.put(currencyCode, new CurrencyManipulator(currencyCode));
        return map.get(currencyCode);
    }

    public static Collection<CurrencyManipulator> getAllCurrencyManipulators() {
        return map.values().stream().collect(Collectors.toList());
    }
}
