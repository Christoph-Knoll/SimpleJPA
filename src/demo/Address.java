package demo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Address implements Serializable
{
    //region Fields
    @EmbeddedId
    private AddressId id;
    private String city;
    private String country;
    private String street;
    // @Column annotation needed to tell the ORM in which column the field belongs.
    @Column(name = "street_no")
    private int streetNumber;
    //endregion

    //region Constructors
    public Address(Person associatedPerson, String city, String country, String street, int streetNumber) {
        this.id = new AddressId(associatedPerson);
        this.city = city;
        this.country = country;
        this.street = street;
        this.streetNumber = streetNumber;
    }

    public Address() {
        id = new AddressId();
    }
    //endregion

    //region Props
    public void setAssociatedPerson(Person p){
        id.setPerson(p);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }
    //endregion
}

@Embeddable
class AddressId implements Serializable{
    //region Fields
    // @Column annotation needed to tell the ORM in which column the field belongs.
    @GeneratedValue
    @Column(name = "address_no")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "ssn")
    private Person person;
    //endregion

    //region Constructors
    public AddressId(Person person) {
        this.person = person;
    }

    public AddressId() {
    }
    //endregion

    //region Props
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
    //endregion
}