package com.main.bank.account.persistance;

import com.main.bank.account.model.Account;
import com.main.bank.common.exception.UserException;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

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
