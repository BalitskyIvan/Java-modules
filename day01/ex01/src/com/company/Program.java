package com.company;

public class Program {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            User user = new User("Ivan", 100);
            System.out.println("user" + i + ": " + user.getName() +  " " + user.getIdentifier());
        }
    }
}
