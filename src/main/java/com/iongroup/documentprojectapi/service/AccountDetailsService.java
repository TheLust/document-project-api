package com.iongroup.documentprojectapi.service;

import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.repository.UserRepository;
import com.iongroup.documentprojectapi.security.AccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final UserRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> account = accountRepository.findByUsername(username);
        if (account.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new AccountDetails(account.get());
    }
}
