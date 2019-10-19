package com.tavisca.gce.requesttracker.logger.requesttrackerexceptionlogger.service;

import com.tavisca.gce.requesttracker.logger.requesttrackerexceptionlogger.model.Log;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Integer> {
}
