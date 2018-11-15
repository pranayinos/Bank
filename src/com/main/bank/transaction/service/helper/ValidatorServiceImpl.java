package com.main.bank.transaction.service.helper;

import com.main.bank.common.exception.UserException;

import java.math.BigDecimal;

public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public void validateAmount(BigDecimal transferAmount) throws UserException {
        if (transferAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new UserException("Transfer amount cannot be null or negative : " + transferAmount);
        }
    }

}
