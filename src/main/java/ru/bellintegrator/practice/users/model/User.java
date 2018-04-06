package ru.bellintegrator.practice.users.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.userdocs.model.UserDoc;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Alena on 06.03.2018.
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Column(name= "id")
    private Long id;

    @Basic(optional = false)
    @Column(name="first_name" )
    private String firstName;

    @Basic(optional = false)
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private  String middleName;

    @Basic(optional = false)
    @Column
    private String position;

    @Column
    private String phone;

    @Version
    private Integer version =0;

    @Column(name = "is_identified")
    private Boolean isIdentified;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="office_id")
    private Office office;

    @OneToOne(mappedBy="user",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private UserDoc userDoc;

    public User(){

    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOfficeId(Office office) {
        this.office = office;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public UserDoc getUserDoc() {
        return userDoc;
    }

    public void setUserDoc(UserDoc userDoc) {
        this.userDoc = userDoc;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }


}
