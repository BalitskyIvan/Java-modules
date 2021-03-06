package com.company;
import java.util.UUID;

public interface TransactionsList {
    public void addTransaction(Transaction transaction);
    public void removeTransactionByID(UUID id) throws TransactionNotFoundException;
    public Transaction[] toArray();
}
