package com.company;

import java.util.UUID;

public class Transaction {
    private UUID identifier;
    private User recipient;
    private User sender;
    public enum TransferCategory{
        DEBITS,
        CREDITS,
    }
    private TransferCategory transferCategory;
    private Integer transferAmount;

    public Transaction(User recipient, User sender, TransferCategory transferCategory, Integer transferAmount) {
        this.recipient = recipient;
        this.sender = sender;
        this.transferAmount = transferAmount;
        this.transferCategory = transferCategory;
        identifier = UUID.randomUUID();
        addTransaction();
    }
    private void addTransaction() {
        if (getTransferAmount() > 0) {
            if (transferCategory == TransferCategory.DEBITS) {
                if (getSender().getBalance() >= getTransferAmount()) {
                    getSender().setBalance(getSender().getBalance() - getTransferAmount());
                    getRecipient().setBalance(getRecipient().getBalance() + getTransferAmount());
                }
            }
            if (transferCategory == TransferCategory.CREDITS) {
                if (getRecipient().getBalance() >= getTransferAmount()) {
                    getRecipient().setBalance(getRecipient().getBalance() - getTransferAmount());
                    getSender().setBalance(getSender().getBalance() + getTransferAmount());
                }
            }
        }
    }
    public User getRecipient() {
        return recipient;
    }
    public User getSender() {
        return sender;
    }
    public Integer getTransferAmount() {
        return transferAmount;
    }

    public UUID getIdentifier() {
        return identifier;
    }

}

