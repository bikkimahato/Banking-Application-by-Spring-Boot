package com.bikki.bankingapplication.service;

import com.bikki.bankingapplication.domain.entity.Card;

import java.util.List;

public interface CardService {

    List<Card> findCardsByUserId(long id);

    Card findCardById(long id);
}
