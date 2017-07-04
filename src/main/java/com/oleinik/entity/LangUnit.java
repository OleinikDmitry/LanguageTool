package com.oleinik.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "lang_unit")
public class LangUnit {

    private Long id;
    private String versionRu;
    private String versionEn;
    private Set<User> users;


    public LangUnit() {
    }

    public LangUnit(String versionEn, String versionRu) {
        this.versionRu = versionRu;
        this.versionEn = versionEn;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "version_ru")
    public String getVersionRu() {
        return versionRu;
    }

    public void setVersionRu(String versionRu) {
        this.versionRu = versionRu;
    }

    @Column(name = "version_en")
    public String getVersionEn() {
        return versionEn;
    }

    public void setVersionEn(String versionEn) {
        this.versionEn = versionEn;
    }

    @ManyToMany(mappedBy = "langUnits")
    @JsonIgnore
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
