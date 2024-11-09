package ru.netology.diploma_cloudservice.services.auth;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.netology.diploma_cloudservice.dto.request.LoginRequest;
import ru.netology.diploma_cloudservice.dto.response.GetToken;
import ru.netology.diploma_cloudservice.entity.User;
import ru.netology.diploma_cloudservice.jwt.JWTHelper;
import ru.netology.diploma_cloudservice.repository.LoginRepository;
import ru.netology.diploma_cloudservice.repository.UserRepository;
import ru.netology.diploma_cloudservice.services.file.UserService;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {
    private UserRepository userRepository;
    private AuthenticationManager authManager;
    private JWTHelper jwtHelper;
    private UserService userService;
    private LoginRepository loginRepository;

    public GetToken login(LoginRequest loginAuth) {
        final String userName = loginAuth.getLogin();
        final String password = loginAuth.getPassword();

        authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

        final UserDetails userDetails = userService.loadUserByUsername(userName);
        final String token = jwtHelper.generateToken(userDetails);

        userRepository.saveTokenAndUser(token, userName);
        log.info("User {} authenticate. JWT: {}", userName, token);
        return new GetToken(token);
    }


    public void logout(String token) {
        final String userToken = token.substring(7);
        final String userName = userRepository.getUsernameByToken(userToken);
        log.info("User {} logout, JWT is disabled", userName);
        userRepository.removeTokenAndUsername(token);
    }

    public User getUserFromToken(String token) {
        if (token.startsWith("Bearer ")) {
            String authTokenBearer = token.split(" ")[1];
            String userName = userRepository.getUsernameByToken(authTokenBearer);
            return loginRepository.findByUsername(userName);
        }
        return null;
    }
}
