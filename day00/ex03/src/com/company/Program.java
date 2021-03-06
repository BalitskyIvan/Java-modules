package com.company;
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str;
        long scoreLine = 0;
        long mn = 1;
        for (int i = 1; i <= 18; i++) {
            str = scan.next();
            if (str.equals("42"))
                break;
            if (str.equals("Week") && (scan.nextInt()) == i) {
                    scoreLine += mn * parseInt(scan);
                    mn *= 10;
            }
            else {
                printError();
            }
        }
        printResult(scoreLine);
        scan.close();
    }
    private static void printError() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
    private static int parseInt(Scanner scan) {
        int score = 9;
        int score_tmp = 0;
        for (int j = 0; j < 5; j++) {
            score_tmp = scan.nextInt();
            if (score_tmp < 1 || score_tmp > 9) {
                printError();
            }
            if (score_tmp < score)
                score = score_tmp;
        }
        return score;
    }
    private static void printResult(long scoreLine) {
        long count = 0;
        for (int i = 1; scoreLine > 0; i++) {
            System.out.print("Week ");
            System.out.print(i);
            System.out.print(" ");
            count = scoreLine % 10;
            scoreLine /= 10;
            while (count-- > 0)
                System.out.print("=");
            System.out.println(">");
        }
    }
}
