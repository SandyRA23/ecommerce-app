package com.sra.warehouse.service.domain.valueobject;

import java.util.Objects;

public class Location {
    private final String address;
    private final String city;
    private final String country;

    public Location(String address, String city, String country) {
        this.address = address;
        this.city = city;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location that = (Location) o;
        return address.equals(that.address) && city.equals(that.city) && country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, country);
    }
}
