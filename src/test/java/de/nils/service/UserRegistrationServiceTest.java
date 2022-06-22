package de.nils.service;

import de.nils.model.User;
import de.nils.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRegistrationServiceTest
{
    @InjectMocks
    UserRegistrationService userRegistrationService;

    @Mock
    UserRepository userRepository;

    @Mock
    RegistrationMailService mailService;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @BeforeEach
    void setup()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void wennRegistriere_dannLegeAccountInDBan()
    {
        // given -> when
        userRegistrationService.register("hans", "1234");

        // then
        verify(userRepository).save(any(User.class));
    }

    @Test
    void loginNameExistiert_wennRegistriere_dannException()
    {
        // given
        when(userRepository.findByLogin(anyString())).thenReturn(Optional.of(new User()));

        // when -> then
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> userRegistrationService.register("nils", "1234"))
                .withMessage("Login bereits bekannt");
    }

    @Test
    void wennRegistriere_dannPasswordIstGehashed_v1()
    {
        // given -> when
        userRegistrationService.register("hans", "1234");

        // then
        verify(userRepository).save(userCaptor.capture());
        assertThat(userCaptor.getValue()).extracting(User::getPassword).isNotEqualTo("1234");
    }

    @Test
    void wennRegistriere_dannPasswordIstGehashed_v2()
    {
        // given -> when
        userRegistrationService.register("hans", "1234");

        // then
        verify(userRepository).save(argThat(u -> !u.getPassword().equals("1234")));
    }

    @Test
    void wennRegistierungErfolgreich_dannSendeMail()
    {
        // given
        when(userRepository.save(any())).thenAnswer(i -> i.getArgument(0, User.class));

        // when
        userRegistrationService.register("hans", "1234");

        verify(mailService).sendConfirmationMail(any(User.class));
    }

}