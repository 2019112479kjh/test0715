package com.example.test0715.day0716;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<User> getAllUsers() {
        return null;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return null;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return null;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User updateUser) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
    }
}
