package com.company;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    int value;
	    int counter = 0;
	    while ((value = scan.nextInt()) != 42) {
	    	if (value == 0 || value == 1) {
	    		System.out.println("IllegalNumber");
	    		System.exit(-1);
			}
	    	if (checkPrimeSum(value)) {
				counter++;
			}
        }
	    System.out.println("Count of coffee-request – " + counter);
	    scan.close();
    }
    private static boolean checkPrimeSum(int value) {
        int sum = calculatingSum(value);
        if (checkPrime(sum)) {
			return true;
		}
        return false;
    }
	private static int calculatingSum(int number) {
		if (number == 0) {
			return 0;
		}
        return number % 10 + calculatingSum(number / 10);
	}
	private static boolean checkPrime(int number) {
		boolean answer = true;
		for (int del = 2; del * del <= number; del++){
			if (number % del == 0) {
				answer = false;
				break;
			}
		}
		return answer;
	}
}
