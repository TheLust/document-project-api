package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.exception.NotFoundException;
import com.iongroup.documentprojectapi.repository.UserRepository;
import com.iongroup.documentprojectapi.util.Field;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> NotFoundException.of(Field.USER)
                );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(User oldUser, User newUser) {
        BeanUtils.copyProperties(newUser, oldUser, "id");
        return userRepository.save(oldUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
