package com.karienomen;

import com.karienomen.domain.Address;
import com.karienomen.domain.PhoneNumber;
import com.karienomen.domain.User;

/**
 * Created by andreb on 08.02.17.
 */
public class UserBuilder {

    public static User buildUser(){
        User user = new User();
        user.setName("Andrey Bobrov");
        Address address = new Address();
        address.setCity("Kiev");
        address.setCountry("Ukraine");
        address.setAddressLine("Kopernika 16B, 89");
        address.setOwner(user);
        user.setAddress(address);
        PhoneNumber phone = new PhoneNumber();
        phone.setCode("066");
        phone.setNumber("2046725");
        phone.setOwner(user);
        user.getPhones().add(phone);

        return user;
    }
}
