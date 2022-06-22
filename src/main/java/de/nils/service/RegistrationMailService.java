package de.nils.service;

import de.nils.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistrationMailService
{
    private static final Logger log = LoggerFactory.getLogger( RegistrationMailService.class );
    
    public void sendConfirmationMail(User user)
    {
        log.info("Sending confirmation mail for ...");
        log.info("Ok now what ... ?");
    }
}
