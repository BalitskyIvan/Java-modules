package com.company;

import java.util.UUID;

public class Program {

    public static void main(String[] args) {
        UsersArrayList list = new UsersArrayList();

        for (Integer i = 0; i < 50; i++) {
            User user1 = new User("Ivan Ivanov", 10000);
            list.addUser(user1);
        }
        for (Integer i = list.retrieveTheNumberOfUsers(); i < 100; i++) {
            User user1 = new User("Maria DB", 10000);
            list.addUser(user1);
        }
        try {
            System.out.println(list.retrieveUserByIndex(0).getIdentifier());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println(list.retrieveUserById(91).getName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        System.out.println(list.retrieveTheNumberOfUsers());
    }
}
