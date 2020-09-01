package com.bikki.bankingapplication.controller;

import com.bikki.bankingapplication.auth.CustomUserDetails;
import com.bikki.bankingapplication.domain.entity.Card;
import com.bikki.bankingapplication.domain.entity.Transaction;
import com.bikki.bankingapplication.forms.MakeTransactionForm;
import com.bikki.bankingapplication.repository.CardRepository;
import com.bikki.bankingapplication.repository.TransactionRepository;
import com.bikki.bankingapplication.service.CardService;
import com.bikki.bankingapplication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CardService cardService;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private MakeTransactionForm makeTransactionForm;

    @ModelAttribute("currentUserCards")
    public List<Card> getCurrentUserCards(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        return userService.findUserCardsById(customUserDetails.getId());
    }

    @ModelAttribute("addTransactionForm")
    public MakeTransactionForm getTransactionForm() {
        return makeTransactionForm;
    }

    @GetMapping("/transactions")
    public String getTransactions() {
        return "transactions";
    }


    @GetMapping("/transactions/card/{id}")
    public String getCardTransactions(@PathVariable("id") long cardId,
                                      Model model) {
        List<Transaction> cardTransactions = userService.findTransactionsByCardId(cardId);
        model.addAttribute("cardTransactions", cardTransactions);
        makeTransactionForm.setCardId(cardId);
        return "card-transactions";
    }

    @PostMapping("/add-transaction")
    public String processAddTransaction(@ModelAttribute("addTransactionForm") MakeTransactionForm makeTransactionForm,
                                        Model model) {
        long cardId = makeTransactionForm.getCardId();
        String iban = makeTransactionForm.getIban();
        double sum = makeTransactionForm.getSum();

        String purpose = makeTransactionForm.getPurpose();

        Card card = cardService.findCardById(cardId);

        if (card != null && card.getCardBalance() >= sum) {
            Transaction transaction = new Transaction(card, iban, sum, purpose);
            card.setCardBalance(card.getCardBalance() - sum);

            cardRepository.save(card);
            transactionRepository.save(transaction);

            model.addAttribute("successfulTransaction", "successfulTransaction");
        } else {
            model.addAttribute("insufficientFundsError", "insufficientFundsError");
        }

        return "transactions";
    }


}
