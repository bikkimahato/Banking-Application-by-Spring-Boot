package com.bikki.bankingapplication.service;

import com.bikki.bankingapplication.domain.entity.Transaction;

import java.util.List;


public interface TransactionService {

    List<Transaction> findTransactionsByCardId(long id);
}
