package com.karienomen;

import com.karienomen.domain.Address;
import com.karienomen.domain.PhoneNumber;
import com.karienomen.domain.User;
import com.karienomen.service.UserService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.karienomen.UserBuilder.buildUser;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by andreb on 08.02.17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistTest {

    @Autowired
    UserService userService;

    @Before
    public void deleteData(){
        userService.deleteAll();
    }

    @Test
    public void persistTest() {

        User user = buildUser();

        userService.create(user);
        List<User> resultList = userService.getAll();
        System.out.println(resultList);
        assertThat(resultList, hasSize(1));
        assertThat(resultList, hasItem(user));
    }

    @Test
    public void deleteTest() {

        List<User> resultList = userService.getAll();
        System.out.println(resultList);
        assertThat(resultList, hasSize(0));
    }

    @Test
    public void getPhonesTest() {

        User user = buildUser();

        userService.create(user);
        long userId = userService.getByName(user.getName()).getUserId();
        List<PhoneNumber> phones = userService.getPhoneNumberByUserId(userId);
        assertThat(phones, hasSize(1));

        user.getPhones().add(new PhoneNumber("093", "9021638", user));
        userService.update(user);
        phones = userService.getPhoneNumberByUserId(userId);
        assertThat(phones, hasSize(2));
    }

    @Test
    public void addPhonesTest() {

        User user = buildUser();
        userService.create(user);
        long userId = userService.getByName(user.getName()).getUserId();
        user.getPhones().add(new PhoneNumber("093", "9021638", user));
        userService.update(user);
        assertThat(userService.getAll(), hasSize(1));
        List<PhoneNumber> phones = userService.getPhoneNumberByUserId(userId);
        assertThat(phones, hasSize(2));
    }

    @Test
    public void searchWithOneUserTest() {

        User user = buildUser();
        userService.create(user);
        List<User> resultList = userService.search("ndr");
        assertThat(resultList, hasSize(1));
        resultList = userService.search("066");
        assertThat(resultList, hasSize(1));
    }

    @Test
    public void searchWithUsersTest() {

        User user1 = buildUser();
        User user2 = buildUser();
        user2.setName("Ivanov");
        userService.create(user1);
        userService.create(user2);

        List<User> resultList = userService.search("ndr");
        assertThat(resultList, hasSize(1));

        resultList = userService.search("066");
        assertThat(resultList, hasSize(2));

        resultList = userService.search("no_match");
        assertThat(resultList, empty());

        resultList = userService.search("6");
        assertThat(resultList, hasSize(2));

        resultList = userService.search("");
        assertThat(resultList, hasSize(2));
    }
}
