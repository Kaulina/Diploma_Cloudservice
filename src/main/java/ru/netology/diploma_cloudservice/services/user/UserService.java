package ru.netology.diploma_cloudservice.services.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.diploma_cloudservice.entity.User;
import ru.netology.diploma_cloudservice.repository.UserRepository;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(username);
        if (user == null) {
            log.error("Пользователя не существует с username " + username);
            throw new UsernameNotFoundException("Пользователя не существует с username " + username);
        }

        return user;
    }
}