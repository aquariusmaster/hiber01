package com.karienomen.service;

import com.karienomen.domain.PhoneNumber;
import com.karienomen.domain.User;

import java.util.List;

/**
 * Created by andreb on 08.02.17.
 */
public interface UserService {

    User getById(long id);

    User getByName(String name);

    void create(User user);
    void update(User user);
    void delete(User user);
    boolean exists(String name);
    List<User> getAll();

    void deleteAll();

    List<PhoneNumber> getPhoneNumberByUserId(long userId);

    List<User> search(String searchTerm);
}
