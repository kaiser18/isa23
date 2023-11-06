package com.ftn.isa23.general.domain;

import javax.persistence.Embeddable;

@Embeddable
public class City {
    String name;

    public City() {}

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
