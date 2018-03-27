package ru.bellintegrator.practice.offices.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {

    public Long id;
    public String name;
    public String phone;
    public Long orgId;
    public Boolean isActive;
    public String address;


    public OfficeView() {
    }

    public OfficeView(Long orgId, String name, String phone, Boolean isActive){
        this.orgId = orgId;
        this.name = name;
        this.phone = phone;
        this.isActive = isActive;
    }

    public OfficeView(Long id){
        this.id = id; //? можно ли так делать?
    }

    public OfficeView(Long id, String name, String address, String phone, Boolean isActive){
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isActive = isActive;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Boolean isActive() {
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

    public Boolean getActive() {
        return isActive;
    }

    @Override
    public String toString() {
        return "OfficeView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", orgId=" + orgId +
                ", isActive=" + isActive +
                ", address='" + address + '\'' +
                '}';
    }
}
