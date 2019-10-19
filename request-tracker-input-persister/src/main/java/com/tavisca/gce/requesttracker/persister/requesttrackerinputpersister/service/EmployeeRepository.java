package com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.service;

import com.tavisca.gce.requesttracker.persister.requesttrackerinputpersister.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

}
