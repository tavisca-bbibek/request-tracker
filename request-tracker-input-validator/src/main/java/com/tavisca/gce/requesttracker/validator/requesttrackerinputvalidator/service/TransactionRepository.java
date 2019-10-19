package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.service;

import com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
