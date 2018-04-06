package ru.bellintegrator.practice.offices.model;

import ru.bellintegrator.practice.organizations.model.Organization;
import ru.bellintegrator.practice.users.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Alena on 06.03.2018.
 */
@Entity
@Table(name = "office")
public class Office implements Serializable {

    @Id
    @GeneratedValue
    @Column(name= "id")
    private Long id;

    @Column
    @Basic(optional = false)
    private String name;

    @Column
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column
    @Basic(optional = false)
    private String address;

    @Version
    private Integer version =0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="org_id")
    private Organization organizationId;

    @OneToMany(mappedBy = "office", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<User> users;

    public Office(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Organization getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Organization organizationId) {
        this.organizationId = organizationId;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
