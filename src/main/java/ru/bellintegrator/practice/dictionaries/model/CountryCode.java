package ru.bellintegrator.practice.dictionaries.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alena on 06.03.2018.
 */
@Entity
@Table(name = "country_code")
public class CountryCode implements Serializable {

    @Id
    @GeneratedValue
    @Column(name= "Id")
    private Long id;

    @Column
    @Basic(optional = false)
    private String code;

    @Column
    @Basic(optional = false)
    private String name;

    @Version
    private Integer version =0;

    public CountryCode(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
