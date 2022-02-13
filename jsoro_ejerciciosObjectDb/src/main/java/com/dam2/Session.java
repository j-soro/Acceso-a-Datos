package com.dam2;

import javax.persistence.*;

@Embeddable
public class Session {

    private int id;
    private String sessionName;
    private String sessionDate;

    public Session() {
    }

    public int getId() {
        return id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public String getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(String sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionName='" + sessionName + '\'' +
                ", sessionDate=" + sessionDate +
                '}';
    }
}
