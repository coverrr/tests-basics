package de.nils.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class User
{

    @Id
    @GeneratedValue
    private long id;

    private String login;

    private String password;

    private String firstname;

    private String lastname;

    private LocalDate birthday;

    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
    }

    public User()
    {
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getLastname()
    {
        return lastname;
    }

    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setBirthday(LocalDate birthday)
    {
        this.birthday = birthday;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
