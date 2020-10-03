package com.kastle.sharewise.service;

import com.kastle.sharewise.exception.NotFoundException;
import com.kastle.sharewise.model.Transaction;
import com.kastle.sharewise.repository.TeamRepository;
import com.kastle.sharewise.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * A Restful Controller servlet providing Restful access to <code>Transaction</code> data.
 * @see Transaction
 */
@RestController
public class TransactionService {
    /**
     * The Team Repository
     * @see TeamRepository
     */
    @Autowired
    private TransactionRepository tranRepo;

    /**
     * HTTP Get request to return the list of all <code>Transaction</code> records
     * @return the list of all <code>Transaction</code> records
     */
    @GetMapping(value = RestUriConstants.GET_ALL_TRANSACTION)
    public Iterable<Transaction> getAllTransactions() {
        return tranRepo.findAll();
    }

    /**
     * HTTP Get Request to get a single <code>Transaction</code> record.
     * Has <code>TransactionRepository</code> throw <code>NotFoundException</code> if record is not found.
     * @param id The id of the <code>Transaction</code> to return sent as a URL path variable
     * @return The <code>Transaction</code> record requested by it's id
     */
    @GetMapping(value = RestUriConstants.GET_TRANSACTION)
    public Transaction getTransaction(@PathVariable("id") Transaction.TransactionKey id) {
        return tranRepo.findById(id).orElseThrow(()->new NotFoundException());
    }

    /**
     * HTTP PUT Request to update an existing <code>Transaction</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param tran The new values of the record with the exact <code>id</code> of the record to replace.
     * @return The updated record if found, else has <code>TransactionRepository</code>
     * throw <code>NotFoundException</code>
     */
    @PutMapping(
            value = RestUriConstants.PUT_TRANSACTION,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    public Transaction updateTransaction(@RequestBody Transaction tran) {
        tranRepo.findById(tran.getId()).orElseThrow(()->new NotFoundException());
        tranRepo.save(tran);
        return tran;
    }

    /**
     * HTTP POST request to add a new <code>Transaction</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param tran The new <code>Transaction</code> record data to add.
     * @return The created <code>Transaction</code> record
     */
    @PostMapping(
            value = RestUriConstants.POST_TRANSACTION,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    //TODO better exception
    public Transaction addTransaction(@RequestBody Transaction tran) throws Exception {
        if (tranRepo.findById(tran.getId()).isPresent())
            throw new Exception(); //TODO Why this?
        tranRepo.save(tran);
        return tran;
    }

    /**
     * HTTP DELETE Request to delete a <code>Transaction</code> record.
     * Requires the request to include <code>application/json</code> in header.
     * @param tran The <code>Transaction</code> to delete.
     * @return The data of the deleted <code>Transaction</code> unless it was not found in which case
     * it has <code>TransactionRepository</code> throw a <code>NotFoundException</code>
     */
    @DeleteMapping(
            value = RestUriConstants.DELETE_TRANSACTION,
            consumes = RestUriConstants.ACCEPTED_CONSUMER,
            produces = RestUriConstants.ACCEPTED_PRODUCER
    )
    public Transaction deleteTransaction(@RequestBody Transaction tran) {
        tranRepo.findById(tran.getId()).orElseThrow(() -> new NotFoundException());
        tranRepo.deleteById(tran.getId());
        return tran;
    }
}
