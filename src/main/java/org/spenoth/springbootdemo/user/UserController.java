package org.spenoth.springbootdemo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String hello() {
        return "Hello Dear User";
    }

    @GetMapping(path = "getusers")
    public List<User> getUsers() {
        List<User> toRet = userService.getUsers();
        if (null == toRet) {
            return Collections.emptyList();
        } else return toRet;
    }

    @PostMapping("register")
    public User registerUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping(path="{uid}")
    public User getUser(@PathVariable Long uid) {
       return userService.getUser(uid).get();
    }

    @DeleteMapping(path="{uid}")
    public void deleteUser(@PathVariable Long uid) {
        userService.deleteUser(uid);
    }

    @PutMapping(path="update")
    public void updateUser(@RequestBody User user) {
        userService.updateUser(user);
    }

}
