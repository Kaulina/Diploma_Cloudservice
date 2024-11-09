package ru.netology.diploma_cloudservice.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserRepositoryTest {

    private UserRepository userRepository;
    private final Map<String, String> testTokenMapUsers = new ConcurrentHashMap<>();

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        userRepository.saveTokenAndUser("token1", "username1");
        testTokenMapUsers.clear();
        testTokenMapUsers.put("token1", "username1");
    }

    @org.testng.annotations.Test
    void tokenAndUsername() {
        String before = userRepository.getUsernameByToken("token1");
        assertNotNull(before);
        userRepository.saveTokenAndUser("token2", "username2");
        String after = userRepository.getUsernameByToken("token2");
        assertEquals("username2", after);
    }

    @Test
    void removeToken() {
        String before = userRepository.getUsernameByToken("token1");
        assertNotNull(before);
        userRepository.removeTokenAndUsername("token1");
        String after = userRepository.getUsernameByToken("token1");
        assertNull(after);
    }

    @Test
    void usernameGetTokenTest() {
        assertEquals(testTokenMapUsers.get("token1"), userRepository.getUsernameByToken("token1"));
    }
}