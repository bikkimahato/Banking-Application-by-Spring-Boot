package com.bikki.bankingapplication.service.impl;

import com.bikki.bankingapplication.domain.entity.Card;
import com.bikki.bankingapplication.domain.entity.Role;
import com.bikki.bankingapplication.domain.entity.Transaction;
import com.bikki.bankingapplication.domain.entity.User;
import com.bikki.bankingapplication.forms.UserCreateForm;
import com.bikki.bankingapplication.repository.UserRepository;
import com.bikki.bankingapplication.service.CardService;
import com.bikki.bankingapplication.service.RoleService;
import com.bikki.bankingapplication.service.TransactionService;
import com.bikki.bankingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.bikki.bankingapplication.constants.GeneralConstants.ROLE_USER;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private CardService cardService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<Card> findUserCardsById(long id) {
        return cardService.findCardsByUserId(id);
    }

    @Override
    public List<Transaction> findTransactionsByCardId(long id) {
        return transactionService.findTransactionsByCardId(id);
    }

    public User registerUser(UserCreateForm userCreateForm) {
        User user = new User();

        user.setUsername(userCreateForm.getUsername());
        user.setEmail(userCreateForm.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));

        Set<Role> roles = generateRolesSet();
        user.setRoles(roles);
        user.setCards(new ArrayList<>());

        return userRepository.save(user);
    }

    private Set<Role> generateRolesSet() {
        Role role = roleService.findByRoleName(ROLE_USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        return roles;
    }

    public boolean hasValidPassword(User user, String pwd) {
        return passwordEncoder.matches(pwd, user.getPassword());
    }
}
