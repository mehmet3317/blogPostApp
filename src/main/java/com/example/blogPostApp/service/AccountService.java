package com.example.blogPostApp.service;

import com.example.blogPostApp.model.Account;
import com.example.blogPostApp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

    @Service
    public class AccountService {
        @Autowired
        private AccountRepository accountRepository;

        public Account save(Account account){
            return accountRepository.save(account);
        }

        public Optional<Account> findByEmail(String email){
            return accountRepository.findOneByEmail(email);
        }
    }

