package com.bikki.bankingapplication.service;

import com.bikki.bankingapplication.domain.entity.Card;
import com.bikki.bankingapplication.domain.entity.Transaction;
import com.bikki.bankingapplication.domain.entity.User;
import com.bikki.bankingapplication.forms.UserCreateForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsername(String username);

    Optional<User> findUserByEmail(String email);

    List<Card> findUserCardsById(long id);

    List<Transaction> findTransactionsByCardId(long id);

    User registerUser(UserCreateForm userCreateForm);

    boolean hasValidPassword(User user, String pwd);
}
