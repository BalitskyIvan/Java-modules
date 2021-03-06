package com.company;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        if (!(scan.hasNextInt())) {
            printError();
        }
        int number = scan.nextInt();
        if (number <= 0 || number == 1) {
            printError();
        } else {
            checkPrime(number);
        }
        scan.close();
    }
    private static void checkPrime(int number) {
        int iterator = 1;
        boolean answer = true;
        for (int del = 2; del * del <= number; del++, iterator++){
            if (number % del == 0) {
                answer = false;
                break;
            }
        }
        System.out.println(answer + " " + iterator);
    }
    private static void printError() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
}
