package com.karienomen.service;

import com.karienomen.dao.UserDao;
import com.karienomen.domain.PhoneNumber;
import com.karienomen.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by andreb on 08.02.17.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserDao userDao;

    @Override
    public User getById(long id) {
        return userDao.getById(id);
    }

    @Override
    public User getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public void create(User user) {
        userDao.create(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public boolean exists(String name) {
        return userDao.exists(name);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public List<PhoneNumber> getPhoneNumberByUserId(long userId) {
        return userDao.getPhoneNumberByUserId(userId);
    }

    @Override
    public List<User> search(String searchTerm) {
        return userDao.searchInEachField(searchTerm);
    }
}
