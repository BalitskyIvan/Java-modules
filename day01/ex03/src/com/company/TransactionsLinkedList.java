package com.company;
import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {

    private Node begin;
    private Node end;
    private Integer size;

    public TransactionsLinkedList() {
        this.size = 0;
    }

    private class Node {

        private Transaction transaction;
        private Node next;
        private Node prev;

        public Node(Node prev, Transaction transaction, Node next) {
            this.prev = prev;
            this.transaction = transaction;
            this.next = next;
        }
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (begin == null) {
            begin = new Node(null, transaction, null);
            end = new Node (begin, null, null);
            begin.next = end;
        }
        else {
            end.transaction = transaction;
            Node newEnd = new Node(end, null, null);
            end.next = newEnd;
            end = newEnd;
        }
        size++;
    }

    @Override
    public void removeTransactionByID(UUID id) throws TransactionNotFoundException {
        if (begin == null) {
            throw new TransactionNotFoundException("TransactionNotFound");
        }
        Node prev = null;
        Node current = begin;
        while (current.transaction != null) {
            if (current.transaction.getIdentifier().equals(id)) {
                if (prev != null) {
                    prev.next = current.next;
                    current.next.prev = prev;
                    size--;
                }
                else {
                    if (current.next != end) {
                        begin = current.next;
                        size--;
                    }
                    else {
                        begin = null;
                        end = null;
                        size = 0;
                    }
                }
                return;
            }
            prev = current;
            current = current.next;
        }
        throw new TransactionNotFoundException("TransactionNotFound");
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactionsArray = new Transaction[size];
        Node current = begin;
        for (int i = 0; i < size && current != null; i++) {
            transactionsArray[i] = current.transaction;
            current = current.next;
        }

        return transactionsArray;
    }

    public Integer getSize() {
        return size;
    }
}
