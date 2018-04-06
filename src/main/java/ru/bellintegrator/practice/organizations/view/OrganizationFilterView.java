package ru.bellintegrator.practice.organizations.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;

/**
 * Created by Alena on 28.03.2018.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class OrganizationFilterView {

    public Long id;
    public String name;
    public String inn;
    public Boolean isActive;

    public OrganizationFilterView() {
    }

    public OrganizationFilterView(String name, Boolean isActive, String inn) {
        this.name = name;
        this.isActive = isActive;
        this.inn = inn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    @Override
    public String toString() {
        return "OrganizationFilterView{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", inn='" + inn + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
