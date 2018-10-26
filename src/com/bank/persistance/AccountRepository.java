package com.bank.persistance;

import com.bank.exception.UserException;
import com.bank.model.Account;

public interface AccountRepository {

    Account find(long accountNumber) throws UserException;

    void save(Account account);

    void delete(long accountNumber) throws UserException;

}
