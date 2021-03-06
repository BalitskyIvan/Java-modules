package com.company;

public class Program {

    public static void main(String[] args) {
        User user1 = new User("Ivan Ivanov", 1000);
        User user2 = new User("Karl Krylov", 30000);
        System.out.println(user1.getName() + " User1ID: " + user1.getIdentifier());
        System.out.println(user2.getName() + " User2ID: " + user2.getIdentifier());
        System.out.println("balance 1: :" + user1.getBalance());
        System.out.println("balance 2: :" + user2.getBalance());

        Transaction trans1 = new Transaction(user2, user1, Transaction.TransferCategory.CREDITS,2000);
        System.out.println("Trans1ID: " + trans1.getIdentifier());
        System.out.println("balance 1: :" + user1.getBalance());
        System.out.println("balance 2: :" + user2.getBalance());

        Transaction trans2 = new Transaction(user2, user1, Transaction.TransferCategory.DEBITS,20000);
        System.out.println("Trans2ID: " + trans2.getIdentifier());
        System.out.println("balance 1: :" + user1.getBalance());
        System.out.println("balance 2: :" + user2.getBalance());

        Transaction trans3 = new Transaction(user2, user1, Transaction.TransferCategory.DEBITS,3000);
        System.out.println("Trans3ID: " + trans3.getIdentifier());
        System.out.println("balance 1: :" + user1.getBalance());
        System.out.println("balance 2: :" + user2.getBalance());
    }
}
