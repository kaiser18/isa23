package com.ftn.isa23.general.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Country {
    String name;

    public Country() {}

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
