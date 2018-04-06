package ru.bellintegrator.practice.users.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserView {

    public Long id;
    public Long officeId;
    public String firstName;
    public String lastName;
    public String middleName;
    public String position;
    public String docCode;
    public String citizenshipCode;
    public String phone;
    public String docName;
    public String docNumber;
    public Date docDate;
    public String citizenshipName;

    public UserView() {
    }

    public Long getId() {
        return id;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPosition() {
        return position;
    }

    public String getDocCode() {
        return docCode;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getDocName() {
        return docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }


    @Override
    public String toString() {
        return "UserView{" +
                "id=" + id +
                ", officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", docCode='" + docCode + '\'' +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                ", phone='" + phone + '\'' +
                ", docName='" + docName + '\'' +
                ", docNumber='" + docNumber + '\'' +
                ", docDate=" + docDate +
                ", citizenshipName='" + citizenshipName + '\'' +
                '}';
    }
}
