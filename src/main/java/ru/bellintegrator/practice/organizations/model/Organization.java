package ru.bellintegrator.practice.organizations.model;

import ru.bellintegrator.practice.offices.model.Office;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Created by Alena on 06.03.2018.
 */

@Entity
@Table(name="organization")
public class Organization implements Serializable {

    @Id
    @GeneratedValue
    @Column(name= "Id")
    private Long id;

    @Column
    @Basic(optional = false)
    private String name;

    @Column(name = "full_name")
    @Basic(optional = false)
    private String fullName;

    @Column
    @Basic(optional = false)
    private String inn;

    @Column
    private String kpp;

    @Column
    @Basic(optional = false)
    private String address;

    @Column
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @Version
    private Integer version=0;

    @OneToMany(mappedBy = "organizationId", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices;

    public Organization(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }
}

