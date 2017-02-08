package com.karienomen.domain;

import javax.persistence.*;


/**
 * Created by andreb on 08.02.17.
 */
@Entity
public class PhoneNumber {

    @Id @GeneratedValue
    private long phoneId;
    private String code;
    private String number;
    @ManyToOne
    private User owner;

    public PhoneNumber(){}

    public PhoneNumber(String code, String number) {
        this.code = code;
        this.number = number;
    }
    public PhoneNumber(String code, String number, User user) {
        this.code = code;
        this.number = number;
        this.owner = user;
    }

    public long getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(long phoneId) {
        this.phoneId = phoneId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        return number != null ? number.equals(that.number) : that.number == null;

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneId=" + phoneId +
                ", code='" + code + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
