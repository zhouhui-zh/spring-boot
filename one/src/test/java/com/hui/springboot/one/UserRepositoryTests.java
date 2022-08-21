package com.hui.springboot.one;

import com.hui.springboot.one.dao.UserRepository;
import com.hui.springboot.one.vo.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)

//较新版的Spring Boot取消了@SpringApplicationConfiguration这个注解，用@SpringBootTest就可以了
//@SpringApplicationConfiguration
@SpringBootTest
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
       /* Date date = new Date();
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
        String formattedDate = dateFormatter.format(date);
        System.out.println(formattedDate);
        userRepository.save(new User("aa1",  "aa@126.com", "aa", "aa123456",formattedDate,17));
        userRepository.save(new User("bb2",  "bb@126.com", "bb","bb123456",formattedDate,18));
        userRepository.save(new User("cc3",  "cc@126.com", "cc", "cc123456",formattedDate,21));

        Assert.assertEquals(9, userRepository.findAll().size());*/
        Assert.assertEquals("bb", userRepository.findByUsernameOrEmail("bb", "bb@126.com").getNickName());
        userRepository.delete(userRepository.findByUsername("aa1"));
    }

    @Test
    public void deleteById() {
        userRepository.deleteById(10L);
    }

}
