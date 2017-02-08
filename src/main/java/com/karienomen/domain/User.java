package com.karienomen.domain;

import org.hibernate.annotations.*;

import javax.persistence.*;
import org.hibernate.annotations.CascadeType;
import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andreb on 08.02.17.
 */
@Entity
public class User {

    @Id @GeneratedValue
    private long userId;
    private String name;
    @OneToOne(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private Address address;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    @Cascade(CascadeType.ALL)
    private Set<PhoneNumber> phones = new HashSet<PhoneNumber>();

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<PhoneNumber> getPhones() {
        return phones;
    }

    public void setPhones(Set<PhoneNumber> phones) {
        this.phones = phones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return name != null ? name.equals(user.name) : user.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + userId +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }
}
