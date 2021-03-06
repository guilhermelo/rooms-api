package melo.guilherme.rooms.api.config.security;

import melo.guilherme.rooms.api.user.User;
import melo.guilherme.rooms.api.user.UserRepository;
import melo.guilherme.rooms.api.util.crypt.CryptUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService service;

    @Mock
    private UserRepository repository;

    @Test
    public void shouldReturnUserByUsername() {
        final String username = "guilhermelo";

        User user = new User.builder()
                .id(UUID.randomUUID())
                .email("guilher@empresa.com.br")
                .name("Guilherme Melo")
                .password(CryptUtil.encryptPassword("senhateste"))
                .build();

        when(repository.findByUsername(username)).thenReturn(Optional.of(user));

        User recoveredUser = (User) service.loadUserByUsername(username);

        assertNotNull(recoveredUser.getName());
    }


    @Test(expected = UsernameNotFoundException.class)
    public void shouldThrowExceptionWhenUserDoesntExist() {
        final String username = "usuario_um";

        when(repository.findByUsername(username)).thenReturn(Optional.empty());
        User recoveredUser = (User) service.loadUserByUsername(username);
        assertNotNull(recoveredUser.getName());
    }

}
