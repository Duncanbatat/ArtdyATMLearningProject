package com.artdy;

import java.util.*;

import com.artdy.exceptions.NotEnoughMoneyException;

public class CurrencyManipulator {
    private String currencyCode;
    private Map<Integer, Integer> denominations;

    public CurrencyManipulator(String currencyCode) {
        this.currencyCode = currencyCode;
        denominations = new HashMap<>();
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void addAmount(int denomination, int count) {
        if (denominations.containsKey(denomination)) {
            denominations.put(denomination, denominations.get(denomination) + count);
        } else {
            denominations.put(denomination, count);
        }
    }

    public int getTotalAmount() {
        int totalAmount = 0;
        for (Map.Entry<Integer, Integer> entry : denominations.entrySet()) {
            totalAmount += entry.getKey() * entry.getValue();
        }
        return totalAmount;
    }

    public boolean hasMoney() {
        return !denominations.isEmpty();
    }

    public boolean isAmountAvailable(int expectedAmount) {
        return  getTotalAmount() >= expectedAmount;
    }

    public Map<Integer, Integer> withdrawAmount(int expectedAmount) throws NotEnoughMoneyException {
        Map<Integer, Integer> map = new HashMap<>();

        while (expectedAmount > 0) {
            int nextBill = getNextBill(expectedAmount);
            if (map.containsKey(nextBill)) {
                map.put(nextBill, map.get(nextBill) + 1);
            } else {
                map.put(nextBill, 1);
            }
            expectedAmount -= nextBill;
        }
        return map;
    }

    private int getNextBill(int expectedAmount) throws NotEnoughMoneyException {
        int maxSuitableDenomination = getMaxSuitableDenominationInStock(expectedAmount);
        return extractOneBill(maxSuitableDenomination);
    }

    private int getMaxSuitableDenominationInStock(int expectedAmount) throws NotEnoughMoneyException {
        List<Integer> list = new ArrayList<>(denominations.keySet());
        Collections.sort(list, Collections.reverseOrder());
        for (Integer integer : list) {
            if (integer <= expectedAmount) {
                return integer;
            }
        }
        throw new NotEnoughMoneyException();
    }

    private int extractOneBill(int denomination) {
        if(denominations.get(denomination) == 1) {
            denominations.remove(denomination);
        } else {
            denominations.put(denomination, denominations.get(denomination) - 1);
        }
        return denomination;
    }
}
