package com.company;

public class Program {

    public static void main(String[] args) {
	int number = 479598;
	System.out.println(calculatingSum(number));
    }
    private static int calculatingSum(int number) {
        int sum = 0;
        sum += number / 100000;
        sum += (number % 100000) / 10000;
        sum += (number % 10000) / 1000;
        sum += (number % 1000) / 100;
        sum += (number % 100) / 10;
        sum += number % 10;
        return sum;
    }
}
