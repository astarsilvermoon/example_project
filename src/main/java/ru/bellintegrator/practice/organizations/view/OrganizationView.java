package ru.bellintegrator.practice.organizations;

public class OrganizationView {

    public long id;
    public String name;
    public String inn;
    public boolean isActive;
    public String fullName;
    public String kpp;
    public String address;
    public String phone;

    public OrganizationView() {
    }

    public OrganizationView(String name, String inn, boolean isActive) {
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
    }

    public OrganizationView(long id) {
        this.id = id;
    }

    public OrganizationView(long id, String name, String inn, boolean isActive, String fullName, String kpp, String address, String phone) {
        this.id = id;
        this.name = name;
        this.inn = inn;
        this.isActive = isActive;
        this.fullName = fullName;
        this.kpp = kpp;
        this.address = address;
        this.phone = phone;
    }
}
