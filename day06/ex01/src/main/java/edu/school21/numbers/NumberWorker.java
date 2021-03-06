package edu.school21.numbers;

public class NumberWorker {
    public static class IllegalNumberException extends Exception {
        IllegalNumberException(String message) {
            super(message);
        }
    }
    public boolean isPrime(int number) throws IllegalNumberException {
        if (number <= 1) {
            throw new IllegalNumberException("Illegal argument");
        }
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
    public int digitsSum(int number) {
        if (number == 0) {
            return 0;
        }
        return number % 10 + digitsSum(number / 10);
    }
}
