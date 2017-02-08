package com.karienomen.dao;

import com.karienomen.domain.PhoneNumber;
import com.karienomen.domain.User;

import java.util.List;

/**
 * Created by andreb on 08.02.17.
 */
public interface UserDao {

    User getById(long id);
    void create(User user);
    void update(User user);
    void delete(User user);

    User getByName(String name);

    boolean exists(String name);
    List<User> getAll();

    void deleteAll();

    List<PhoneNumber> getPhoneNumberByUserId(long userId);

    List<User> searchInEachField(String searchTerm);
}
