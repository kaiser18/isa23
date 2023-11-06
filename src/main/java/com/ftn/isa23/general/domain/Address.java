package com.ftn.isa23.general.domain;

import org.hibernate.annotations.Proxy;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Proxy(lazy = false)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String number;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "city")),
    })
    private City city;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "name", column = @Column(name = "country")),
    })
    private Country country;

    public Address() {}

    public Address(Long id, String street, String number, City city, Country country) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.city = city;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
