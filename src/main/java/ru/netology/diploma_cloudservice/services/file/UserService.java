package ru.netology.diploma_cloudservice.services.file;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.diploma_cloudservice.entity.User;
import ru.netology.diploma_cloudservice.exception.MissingValueException;
import ru.netology.diploma_cloudservice.repository.LoginRepository;

@Slf4j
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = loginRepository.findByUsername(userName);
        if (user == null) {
            log.error("Пользователя не существует с username {}", userName);
            throw new MissingValueException("Пользователя не существует с username " + userName);
        }
        return user;
    }
}