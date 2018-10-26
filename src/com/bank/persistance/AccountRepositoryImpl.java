package com.bank.persistance;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.bank.exception.UserException;
import com.bank.model.Account;

public class AccountRepositoryImpl implements AccountRepository {

    private final Map<Long, Account> accountMap;

    public AccountRepositoryImpl() {
        this.accountMap = new ConcurrentHashMap<>();
    }

    @Override
    public Account find(long accountNumber) throws UserException {
        return Optional.ofNullable(accountMap.get(accountNumber))
                .orElseThrow(() -> new UserException("Invalid Account number : " + accountNumber));
    }

    @Override
    public void save(Account account) {
        accountMap.put(account.getAccountNumber(), account);
    }

    @Override
    public void delete(long accountNumber) throws UserException {
        Optional.ofNullable(accountMap.remove(accountNumber))
                .orElseThrow(() -> new UserException("Invalid Account number : " + accountNumber));
    }

}
