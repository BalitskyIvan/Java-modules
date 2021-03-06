package com.company;

public class UsersArrayList implements UsersList {
    private Integer size;
    private Integer capacity;
    private User[] usersArray;

    public UsersArrayList() {
        this.size = 0;
        this.capacity = 10;
        this.usersArray = new User[capacity];
    }

    public void addUser(User user) {
        if (size.equals(capacity)) {
            capacity *= 2;
            User[] tmpArray = new User[capacity];
            for (Integer i = 0; i < size; i++) {
                tmpArray[i] = usersArray[i];
            }
            usersArray = tmpArray;
        }
        usersArray[size] = user;
        size++;
    }
    @Override
    public User retrieveUserById(Integer id) throws UserNotFoundException {
        for (Integer i = 0; i < size; i++) {
            if (usersArray[i].getIdentifier().equals(id))
                return usersArray[i];
        }
        throw new UserNotFoundException("UserNotFound");
    }

    @Override
    public User retrieveUserByIndex(Integer index) throws UserNotFoundException {
        if (index < 0 || index > size) {
            throw new UserNotFoundException("UserNotFound");
        }
        return usersArray[index];
    }

    @Override
    public Integer retrieveTheNumberOfUsers() {
        return size;
    }
}
