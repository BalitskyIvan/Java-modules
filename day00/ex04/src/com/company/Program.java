package com.company;
import java.util.Scanner;
import java.lang.String;

public class Program {

    public static void main(String[] args) {
        char[] myArray = new char[999];
        int[] countSymbols = new int[999];
        Scanner scan = new Scanner(System.in);
        String buff = scan.nextLine();
        int sizeArray = 0;
        char[] arraySymbols = buff.toCharArray();
        for (int i = 0; i < buff.length(); i++) {
            if (sizeArray == 0) {
                myArray[i] = arraySymbols[i];
                countSymbols[i]++;
                sizeArray++;
            } else {
                sizeArray = insertSymbols(myArray, countSymbols, arraySymbols[i], sizeArray);
            }
        }
        quickSortArrays(myArray, countSymbols, 0, sizeArray - 1);
        findSame(myArray, countSymbols, sizeArray - 1);
        printHistogram(myArray, countSymbols, sizeArray);
        scan.close();
    }
    private static int insertSymbols(char[] myArray, int[] index, char s, int pos) {
        for (int i = 0; i < pos; i++) {
            if (myArray[i] == s) {
                index[i]++;
                return pos;
            }
        }
        myArray[pos] = s;
        index[pos] = 1;
        return pos + 1;
    }
    private static void quickSortArrays(char[] myArray, int[] countSymbols, int begin, int end) {
        if (end <= begin) {
            return;
        }
        int position = partition(myArray, countSymbols, begin, end);
        quickSortArrays(myArray, countSymbols, begin, position - 1);
        quickSortArrays(myArray, countSymbols, position + 1, end);
    }
    private static int partition(char[] myArray, int[] countSymbols, int begin, int end) {
        int pivot = end;
        int counter = begin;
        for (int i = begin; i < end; i++) {
            if (countSymbols[i] > countSymbols[pivot]) {
                int tmp = countSymbols[counter];
                countSymbols[counter] = countSymbols[i];
                countSymbols[i] = tmp;
                char temp = myArray[counter];
                myArray[counter] = myArray[i];
                myArray[i] = temp;
                counter++;
            }
        }
        int tmp = countSymbols[pivot];
        countSymbols[pivot] = countSymbols[counter];
        countSymbols[counter] = tmp;
        char temp = myArray[pivot];
        myArray[pivot] = myArray[counter];
        myArray[counter] = temp;
        return counter;
    }
    private static void findSame(char[] myArray, int[] countSymbols, int size) {
        for (int i = 0; i < size; i++) {
            if (countSymbols[i] == countSymbols[i + 1]) {
                if (myArray[i] > myArray[i + 1]) {
                    char tmp = myArray[i];
                    myArray[i] = myArray[i + 1];
                    myArray[i + 1] = tmp;
                    findSame(myArray, countSymbols, size);
                }
            }
        }
    }
    private static void printHistogram(char[] myArray, int[] countSymbols, int sizeArray) {
        if (sizeArray > 10) {
            sizeArray = 10;
        }
        int maxI = sizeArray * 4 + 1;
        int maxJ = 13;
        char[][] histogram = new char[maxJ][maxI];
        for (int j = 0; j < maxJ; j++) {
            for (int i = 0; i < maxI; i++)
                histogram[j][i] = ' ';
        }
        int[] hashCount = new int[sizeArray];
        for (int  i = 0; i < sizeArray; i++) {
            hashCount[i] = (countSymbols[i] * 10) / countSymbols[0];
        }
        for (int i = 2, j = 0; i < maxI && j < sizeArray; i += 3, j++) {
            histogram[maxJ - 1][i] = myArray[j];
        }
        int inPlace = 2;
        int temp;
        for (int i = 0; i < sizeArray; ++i) {
            for (int j = 11; j > 11 - hashCount[i]; --j) {
                histogram[j][inPlace] = '#';
            }
            temp = inPlace;
            while (countSymbols[i] > 0) {
                histogram[11 - hashCount[i]][temp] = (char)((countSymbols[i] % 10) + 48);
                countSymbols[i] /= 10;
                temp -= 1;
            }
            inPlace += 3;
        }
        if (sizeArray > 0) {
            for (int j = 1; j < maxJ; j++) {
                for (int i = 0; i < maxI; i++)
                    System.out.print(histogram[j][i]);
                System.out.println();
            }
        }
    }
}
