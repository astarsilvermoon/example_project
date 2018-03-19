package ru.bellintegrator.practice.offices.model;

import ru.bellintegrator.practice.organizations.model.Organization;

import javax.persistence.*;

/**
 * Created by Alena on 06.03.2018.
 */
@Entity
@Table(name = "office")
public class Office {

    @Id
    @GeneratedValue
    @Column(name= "Id")
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
    private Integer version;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="org_id")
    private Organization organizationId;

    public Office(){

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Organization getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Organization organizationId) {
        this.organizationId = organizationId;
    }
}
