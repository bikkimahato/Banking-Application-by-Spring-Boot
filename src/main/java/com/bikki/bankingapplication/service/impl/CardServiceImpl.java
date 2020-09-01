package com.bikki.bankingapplication.service.impl;

import com.bikki.bankingapplication.service.CardService;
import com.bikki.bankingapplication.domain.entity.Card;
import com.bikki.bankingapplication.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> findCardsByUserId(long id) {
        return cardRepository.findCardsByUserId(id);
    }

    @Override
    public Card findCardById(long id) {
        return cardRepository.findCardById(id);
    }


}
