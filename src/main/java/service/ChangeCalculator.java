package service;

import dto.Coin;


public class ChangeCalculator {

    public static int[] CalculateChange(int funds) {
        int[] coinChangeList = new int[Coin.values().length];
        for (Coin c : Coin.values()) {
            int numberOfCoin = funds / c.getValue();
            coinChangeList[c.ordinal()] = numberOfCoin;
            funds -= (numberOfCoin * c.getValue());
        }
        return coinChangeList;
    }
}
