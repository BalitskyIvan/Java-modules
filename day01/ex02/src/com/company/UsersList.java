package com.company;

public interface UsersList {
    public void    addUser(User user);
    User    retrieveUserById(Integer id) throws UserNotFoundException;
    User    retrieveUserByIndex(Integer index) throws UserNotFoundException;
    Integer retrieveTheNumberOfUsers();
}
