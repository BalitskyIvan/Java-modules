package edu.school21.numbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {

    private final NumberWorker numberWorker;

    public NumberWorkerTest() {
        numberWorker = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 7, 113})
    void  isPrimeForPrimes(int numbers) throws NumberWorker.IllegalNumberException {
        Assertions.assertTrue(numberWorker.isPrime(numbers));
    }
    @ParameterizedTest
    @ValueSource(ints = {4, 8, 134})
    void isPrimeForNotPrimes(int numbers) throws NumberWorker.IllegalNumberException {
        Assertions.assertFalse(numberWorker.isPrime(numbers));
    }

    @ParameterizedTest
    @ValueSource(ints = {-3, 0, -100})
    void isPrimeForIncorrectNumbers(int numbers) {
        Assertions.assertThrows(NumberWorker.IllegalNumberException.class, () -> numberWorker.isPrime(numbers));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    void isDigitSum(int number, int result) {
        Assertions.assertEquals(numberWorker.digitsSum(number), result);
    }
}
