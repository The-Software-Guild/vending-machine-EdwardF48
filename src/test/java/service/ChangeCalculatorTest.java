package service;

import dto.Coin;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ChangeCalculatorTest {


    @Test
    void test0(){
        int[] change = ChangeCalculator.CalculateChange(0);
        int[] actualAnswer = new int[Coin.values().length];
        assertTrue(Arrays.equals(change, actualAnswer));
    }

    @Test
    void test100(){
        int[] change = ChangeCalculator.CalculateChange(100);
        int[] actualAnswer = {0,1,0,0,0,0,0,0};
        assertTrue(Arrays.equals(change, actualAnswer));
    }

    @Test
    void test388(){
        int[] change = ChangeCalculator.CalculateChange(388);
        int[] actualAnswer = {1,1,1,1,1,1,1,1};
        assertTrue(Arrays.equals(change, actualAnswer));
    }

    @Test
    void test440(){
        int[] change = ChangeCalculator.CalculateChange(440);
        int[] actualAnswer = {2,0,0,2,0,0,0,0};
        assertTrue(Arrays.equals(change, actualAnswer));
    }



}