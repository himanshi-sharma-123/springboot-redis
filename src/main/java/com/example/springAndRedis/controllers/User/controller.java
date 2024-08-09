package com.example.springAndRedis.controllers.User;

import com.example.springAndRedis.dao.UserDao;
import com.example.springAndRedis.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class controller {

    @Autowired
    private UserDao userDao;

    // create user
    @PostMapping
    public User createUser(@RequestBody User user){
        user.setUserId(UUID.randomUUID().toString());
        return userDao.save(user);
    }

    // get single user

    @GetMapping("/{userId}")
    public User getUser(@PathVariable String userId){
      return userDao.get(userId);
    }

    //findall
    @GetMapping
    public List<User> getAll(){

        Map<Object, Object> all = userDao.findAll();
        Collection<Object> values = all.values();
        List<User> collect = values.stream().map(value -> (User) value).collect(Collectors.toList());
        return collect;
//        return userDao.findAll();
    }

    // Update user
    @PutMapping("/{userId}")
    public User updateUser(@PathVariable String userId, @RequestBody User user) {
        user.setUserId(userId);
        return userDao.save(user);
    }

    // delete user
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable String userId){
        userDao.delete(userId);
    }



}
