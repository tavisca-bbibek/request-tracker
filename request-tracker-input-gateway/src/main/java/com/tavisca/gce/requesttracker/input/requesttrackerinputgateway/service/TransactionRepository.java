package com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.service;

import com.tavisca.gce.requesttracker.input.requesttrackerinputgateway.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
