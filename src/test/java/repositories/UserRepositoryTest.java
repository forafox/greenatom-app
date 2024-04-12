package repositories;

import org.forafox.domain.Role;
import org.forafox.domain.User;
import org.forafox.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserRepositoryTest {
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        Set<Role> roles = new HashSet<>();
        roles.add(Role.USER);
        User user = new User(1L, "Andrey", "Andrey2004", "123456789", roles);
        User user2 = new User(2L, "Dima", "Dima2003", "12345", roles);

        when(userRepository.findByUsername("Andrey")).thenReturn(Optional.of(user));
        when(userRepository.findByUsername("Andrey2004")).thenReturn(Optional.of(user));
    }

    @Test
    public void UserRepository_testFindByUsername_returnExistingUser() {
        Optional<User> userOptional = userRepository.findByUsername("Andrey2004");
        assertThat(userOptional).isPresent();
        assertThat(userOptional.get().getPassword()).isEqualTo("123456789");
    }

    @Test
    public void UserRepository_testFindByUsername_returnNotExistingUser() {
        Optional<User> userOptional = userRepository.findByUsername("Andrey2004");
        assertThat(userOptional).isEmpty();
    }

}