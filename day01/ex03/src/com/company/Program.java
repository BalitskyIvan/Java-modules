package com.company;
import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        TransactionsLinkedList list = new TransactionsLinkedList();
        User user1 = new User("Ivan Ivanov", 10000);
        User user2 = new User("Karl Krylov", 30000);


        for (int i = 0; i < 10; i++) {
            add(list, user1, user2);
        }

        Transaction[] array = list.toArray();
        UUID[] ids = new UUID[list.getSize()];

        for (int i = 0; i < list.getSize(); i++) {
            ids[i] = array[i].getIdentifier();
            System.out.println(array[i].getIdentifier());
        }
        try {
            list.removeTransactionByID(ids[0]);
            list.removeTransactionByID(ids[5]);
            list.removeTransactionByID(ids[9]);
            list.removeTransactionByID(ids[0]);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        Transaction[] array1 = list.toArray();
        System.out.println();
        System.out.println("Remove:");
        System.out.printf("");
        for (int i = 0; i < list.getSize(); i++) {
            ids[i] = array1[i].getIdentifier();
            System.out.println(array1[i].getIdentifier());
        }
    }

    private static void add(TransactionsLinkedList list, User user1, User user2) {
        Transaction trans1 = new Transaction(user2, user1, Transaction.TransferCategory.CREDITS,2000);
        list.addTransaction(trans1);

    }
}
