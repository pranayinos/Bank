package com.bank.persistance;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.bank.exception.UserException;
import com.bank.model.TransactionDetail;

public class TransactionRepositoryImpl implements TransactionRepository {

    Map<Long, LinkedList<TransactionDetail>> accountTransactionMap = new ConcurrentHashMap<>();

    @Override
    public List<TransactionDetail> findLast10Transaction(long accountNumber) throws UserException {
        LinkedList<TransactionDetail> transactionDetails = Optional.ofNullable(accountTransactionMap.get(accountNumber))
                .orElseThrow(() -> new UserException("Invalid Account number : " + accountNumber));
        Iterator<TransactionDetail> descendingTransactionIterator = transactionDetails.descendingIterator();
        return getFirst10Transactions(descendingTransactionIterator);
    }

    @Override
    public void save(Long accountNumber, TransactionDetail transactionDetail) {
        accountTransactionMap.compute(accountNumber,
            (aLong, transactionDetails) -> Objects.isNull(transactionDetails) ? new LinkedList<>()
                    : appendToListAndReturnList(transactionDetails, transactionDetail));
    }

    private LinkedList<TransactionDetail> appendToListAndReturnList(
            LinkedList<TransactionDetail> existingTransactionDetails,
            TransactionDetail newTransactionDetail) {
        existingTransactionDetails.add(newTransactionDetail);
        return existingTransactionDetails;
    }

    private List<TransactionDetail> getFirst10Transactions(Iterator<TransactionDetail> descendingTransactionIterator) {
        List<TransactionDetail> listOfLastTenTransaction = new ArrayList<>(10);
        int counter = 0;
        while (descendingTransactionIterator.hasNext() && counter < 10) {
            listOfLastTenTransaction.add(new TransactionDetail(descendingTransactionIterator.next()));
            counter++;
        }
        return listOfLastTenTransaction;
    }
}
