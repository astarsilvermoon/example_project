package ru.bellintegrator.practice.users.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.Valid;

/**
 * Created by Alena on 28.03.2018.
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UserFilterView {

    public Long id;
    public Long officeId;
    public String firstName;
    public String lastName;
    public String middleName;
    public String position;
    public String docCode;
    public String citizenshipCode;

    public UserFilterView() {
    }

    public UserFilterView( Long officeId,  String firstName,  String lastName,
                           String middleName, String position, String docCode, String citizenshipCode) {
        this.officeId = officeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.position = position;
        this.docCode = docCode;
        this.citizenshipCode = citizenshipCode;
    }

    public Long getOfficeId() {
        return officeId;
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

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    @Override
    public String toString() {
        return "UserFilterView{" +
                "officeId=" + officeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", docCode='" + docCode + '\'' +
                ", citizenshipCode='" + citizenshipCode + '\'' +
                '}';
    }
}
