package com.bikki.bankingapplication.service.impl;

import com.bikki.bankingapplication.domain.entity.Transaction;
import com.bikki.bankingapplication.repository.TransactionRepository;
import com.bikki.bankingapplication.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> findTransactionsByCardId(long id) {
        return transactionRepository.findTransactionsByCardId(id);
    }
}
