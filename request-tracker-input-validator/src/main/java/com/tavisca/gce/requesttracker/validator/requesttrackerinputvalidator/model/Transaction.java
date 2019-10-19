package com.tavisca.gce.requesttracker.validator.requesttrackerinputvalidator.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private int id;
    private boolean valid;
    private Instant timeStamp;
    private String requestFrom;
    private String requestTo;
    private String payload;

    public Transaction() {
    }

    public Transaction(int id, boolean valid, Instant timeStamp, String requestFrom, String requestTo, String payload) {
        this.id = id;
        this.valid = valid;
        this.timeStamp = timeStamp;
        this.requestFrom = requestFrom;
        this.requestTo = requestTo;
        this.payload = payload;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getRequestFrom() {
        return requestFrom;
    }

    public void setRequestFrom(String requestFrom) {
        this.requestFrom = requestFrom;
    }

    public String getRequestTo() {
        return requestTo;
    }

    public void setRequestTo(String requestTo) {
        this.requestTo = requestTo;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}
