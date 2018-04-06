package ru.bellintegrator.practice.offices.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;

/**
 * Created by Alena on 28.03.2018.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OfficeFilterView {

    public Long id;
    public Long orgId;
    public String name;
    public String phone;
    public Boolean isActive;

    public OfficeFilterView() {
    }

    public OfficeFilterView(Long id, Long orgId,String name,Boolean isActive) {
        this.orgId = orgId;
        this.name = name;
        this.id = id;
        this.isActive = isActive;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "OfficeFilterView{" +
                "orgId=" + orgId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
