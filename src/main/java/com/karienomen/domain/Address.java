package com.karienomen.domain;

import javax.persistence.*;

/**
 * Created by andreb on 08.02.17.
 */
@Entity
public class Address {
    @Id @GeneratedValue
    private long AddressId;
    private String country;
    private String city;
    private String addressLine;
    @OneToOne
    private User owner;

    public long getAddressId() {
        return AddressId;
    }

    public void setAddressId(long addressId) {
        AddressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
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

        Address address = (Address) o;

        if (country != null ? !country.equals(address.country) : address.country != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (addressLine != null ? !addressLine.equals(address.addressLine) : address.addressLine != null) return false;
        return owner != null ? owner.equals(address.owner) : address.owner == null;

    }

    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (addressLine != null ? addressLine.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "AddressId=" + AddressId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", owner=" + owner.getName() +
                '}';
    }
}
