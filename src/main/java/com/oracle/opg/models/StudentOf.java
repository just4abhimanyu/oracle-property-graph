package com.oracle.opg.models;

public class StudentOf {
    private String sId;
    private String sPersonId;
    private String sUnivId;
    private String subject;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getsPersonId() {
        return sPersonId;
    }

    public void setsPersonId(String sPersonId) {
        this.sPersonId = sPersonId;
    }

    public String getsUnivId() {
        return sUnivId;
    }

    public void setsUnivId(String sUnivId) {
        this.sUnivId = sUnivId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
