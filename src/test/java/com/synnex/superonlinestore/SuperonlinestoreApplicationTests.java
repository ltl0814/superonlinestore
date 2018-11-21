package com.synnex.superonlinestore;

import com.synnex.superonlinestore.dao.entity.User;
import com.synnex.superonlinestore.dao.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperonlinestoreApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {
        System.out.println(userRepository.updateUserByloginid("5712","Dustin","1","dustin@synnex.com"));
    }

}
