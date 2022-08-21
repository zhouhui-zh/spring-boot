package com.hui.springboot.one;

import com.hui.springboot.one.dao.UsersDao;
import com.hui.springboot.one.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersDaoTest {
    @Autowired
    private UsersDao usersDao;

    @Test
    public void test() {
        System.out.println("getOne");
        long id = 14;
        UserEntity userEntity = usersDao.getOne(id);
        System.out.println(userEntity);
        userEntity.setNickName("lalal");
        System.out.println("update");
        usersDao.update(userEntity);

        userEntity = usersDao.getOne(id);
        System.out.println(userEntity);

        System.out.println("delete");
        usersDao.delete(id);
        usersDao.insert(userEntity);


        System.out.println("getAll");
        List list = usersDao.getAll();
        for (Object o : list) {

            System.out.printf("");
        }
    }
}
