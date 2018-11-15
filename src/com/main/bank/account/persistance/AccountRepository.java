package com.main.bank.account.persistance;

import com.main.bank.account.model.Account;
import com.main.bank.common.exception.UserException;

public interface AccountRepository {

    Account find(long accountNumber) throws UserException;

    void save(Account account);

    void delete(long accountNumber) throws UserException;

}
