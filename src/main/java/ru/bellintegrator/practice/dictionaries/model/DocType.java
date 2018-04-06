package ru.bellintegrator.practice.dictionaries.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alena on 06.03.2018.
 */

@Entity
@Table(name="doc_type")
public class DocType implements Serializable {

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

   public DocType(){

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
