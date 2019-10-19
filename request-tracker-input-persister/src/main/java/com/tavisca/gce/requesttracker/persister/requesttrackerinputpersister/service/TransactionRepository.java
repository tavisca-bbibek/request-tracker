package com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.service;


import com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

}
