package ru.bellintegrator.practice.accounts.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account implements Serializable{

    @Id
    @GeneratedValue
    @Column(name= "Id")
    private Long id;

    @Version
    private Integer version = 0;

    @Basic(optional = false)
    @Column
    @Email
    private String login;

    @Basic(optional = false)
    @Column
    private String password;

    @Column(name = "activation_code")
    private String activationCode;

    @Column(name = "is_activated")
    private  Boolean isActivated;

    @Column
    private String name;

    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
