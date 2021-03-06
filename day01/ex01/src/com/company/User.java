package com.company;

public class User {
    private Integer identifier;
    private String name;
    private Integer balance;

    public User(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
        this.identifier = UserIdsGenerator.getInstance().generateId();
    }

    public Integer getBalance() {
        return balance;
    }
    public String getName() {
        return name;
    }
    public Integer getIdentifier() {
        return identifier;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
