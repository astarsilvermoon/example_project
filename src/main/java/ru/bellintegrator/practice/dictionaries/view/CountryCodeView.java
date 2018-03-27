package ru.bellintegrator.practice.dictionaries.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryCodeView {

    public Long id;
    public String name;
    public String code;

    //ToDo как возвращать список значений во вью?

    public CountryCodeView() {

    }


    public CountryCodeView(String name, String code) {
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

    //ToDo /*Проверить правильность сгенерированного toString*/
    @Override
    public String toString() {
        return "CountryCodeView{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
