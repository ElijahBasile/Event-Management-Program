//Address.java 

import java.io.Serializable;

//controls address values for a particular event
public class Address implements Serializable {
    private String street;
    private String apartment;
    private String city;
    private String state;
    private String zipCode;
   
    Address() {
        this.setStreet(" ");
        this.setApartment(" ");
        this.setCity(" ");
        this.setState(" ");
        this.setZipCode(" ");
    }

    public void setStreet(String streetNumber) {
        this.street = streetNumber;
    }

    public void setApartment(String apartmentNumber) {
        this.apartment = apartmentNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
    public void setZipCode(String zip) {
        this.zipCode = zip;
    }

    public String getAddress() {
        return (this.street + ", " + this.apartment + ", " + this.city + ", " + this.state + ", " + this.zipCode);
    }
} //end Address
