package de.nils.service;

import de.nils.model.User;
import de.nils.repository.UserRepository;
import de.nils.utils.Utils;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService
{
    private final UserRepository userRepository;

    private final RegistrationMailService mailService;

    public UserRegistrationService(UserRepository userRepository, RegistrationMailService mailService)
    {
        this.userRepository = userRepository;
        this.mailService = mailService;
    }

    public void register(String login, String password)
    {
        if(userRepository.findByLogin(login).isPresent())
        {
            throw new RuntimeException("Login bereits bekannt");
        }

        User registeredUser = userRepository.save(new User(login, Utils.buildSha1Sum(password)));

        mailService.sendConfirmationMail(registeredUser);
    }
}
