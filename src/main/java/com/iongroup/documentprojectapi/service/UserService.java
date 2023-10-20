package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.UserRepository;
import com.iongroup.documentprojectapi.util.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Entity.USER)
                );
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User oldUser, User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        BeanUtils.copyProperties(newUser, oldUser, "id");
        return userRepository.save(oldUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
