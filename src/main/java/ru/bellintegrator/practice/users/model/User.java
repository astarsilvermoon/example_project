package ru.bellintegrator.practice.users.model;

import ru.bellintegrator.practice.offices.model.Office;
import ru.bellintegrator.practice.userdocs.model.UserDoc;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Alena on 06.03.2018.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name= "Id")
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

    @Column(name = "is_identified")
    private Boolean isIdentified;

    @Version
    private Integer version;

    @Basic(optional = false)
    @Column
    private String login;

    @Basic(optional = false)
    @Column
    private char[] password;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="office_id")
    private Office office;

    @OneToMany(mappedBy="user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserDoc> userDocs;

    public User(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Office getOfficeId() {
        return office;
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

    public List<UserDoc> getUserDocs() {
        return userDocs;
    }

    public void setUserDocs(List<UserDoc> userDocs) {
        this.userDocs = userDocs;
    }

    public void addUserDoc(UserDoc doc) {
        getUserDocs().add(doc);
        doc.setUser(this);
    }
    public void removeUserDoc(UserDoc doc) {
        getUserDocs().remove(doc);
        doc.setUser(null);
    }

}
