package com.main.bank.transaction.service.helper;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

public interface ValidatorService {
    void validateAmount(BigDecimal transferAmount) throws UserException;
}
