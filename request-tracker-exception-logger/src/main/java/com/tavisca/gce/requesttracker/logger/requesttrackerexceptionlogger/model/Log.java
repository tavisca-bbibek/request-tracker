package com.tavisca.gce.requesttracker.logger.requesttrackerexceptionlogger.model;

import javax.persistence.*;

@Entity
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String serviceName;
    private String exception;

    public Log() {
    }

    public Log(String serviceName, String exception) {
        this.serviceName = serviceName;
        this.exception = exception;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
