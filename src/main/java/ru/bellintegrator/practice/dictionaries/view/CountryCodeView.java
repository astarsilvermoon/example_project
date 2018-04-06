package ru.bellintegrator.practice.dictionaries.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CountryCodeView {

    public Long id;
    public String name;
    public String code;

    public CountryCodeView(){

    }


    public CountryCodeView(String name, String code){
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CountryCodeView{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
