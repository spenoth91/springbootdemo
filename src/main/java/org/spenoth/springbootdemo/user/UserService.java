package org.spenoth.springbootdemo.user;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User addUser(final User user) {
        System.out.println(user.toString());
        Optional<User> u = userRepository.findByEmail(user.getEmail());
        if (u.isPresent()) {
            throw new IllegalArgumentException("Email allready taken");
        }
        User toRet = userRepository.save(user);
        return toRet;
    }

    public void deleteUser(Long uid) {
        boolean exists = userRepository.existsById(uid);
        if (!exists) {
            throw new IllegalArgumentException(String.format("User with id %s does not exit.", uid));
        }
        userRepository.deleteById(uid);
    }

    @Transactional
    public void updateUser(final User user) {

        validateUserAttrs(user);

        Optional<User> pUser = userRepository.findById(user.getId());
        if (pUser.isEmpty()) {
            throw new IllegalArgumentException(String.format("User with id %s does not exist.", user.getId()));
        }

        if (pUser.get().equals(user)) {
            // do something nice like no changes detected or something
            return;
        }

        pUser.get().setName(user.getName());
        pUser.get().setDob(user.getDob());
        pUser.get().setEmail(user.getEmail());

    }

    private static void validateUserAttrs(User user) {
        List<String> errors = new ArrayList<>();

        if (user.getId() == null) {
            errors.add("Missing id");
        }

        if (StringUtils.isEmpty(user.getName())) {
            errors.add("Missing name");
        }

        if (StringUtils.isEmpty(user.getEmail())) {
            errors.add("Missing email");
        }

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join("; ", errors));
        }
    }
}
