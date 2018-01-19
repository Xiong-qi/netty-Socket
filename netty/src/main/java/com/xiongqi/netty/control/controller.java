package com.xiongqi.netty.control;

import com.xiongqi.netty.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller {

    @RequestMapping("/now")
    public User now() {
        User user = new User();
        user.setId(1);
        user.setAge(23);
        user.setUsername("阿杜哈");

        return user;
    }
}
