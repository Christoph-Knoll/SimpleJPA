package demo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Person {
    //region Fields
    @Id
    private String SSN;
    private Date dateOfBirth;
    private String firstName;
    private String lastName;
    private boolean isAwesome;
    private Double awesomeness;
    private BigDecimal wealth;

    @OneToMany(mappedBy = "id.person", cascade = CascadeType.PERSIST)
    private List<Address> addresses = new ArrayList<>();
    //endregion

    //region Constructors
    public Person(String SSN, Date dateOfBirth, String firstName, String lastName, boolean isAwesome, Double awesomeness, BigDecimal wealth) {
        this.SSN = SSN;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAwesome = isAwesome;
        this.awesomeness = awesomeness;
        this.wealth = wealth;
    }

    public Person() {
    }
    //endregion

    //region Props
    public String getSSN() {
        return SSN;
    }

    public void setSSN(String sSN) {
        this.SSN = sSN;
    }

    public void setAwesomeness(Double awesomeness) {
        this.awesomeness = awesomeness;
    }

    public void addAddress(String city, String country, String street, int streetNumber) {
        addresses.add(new Address(this, city, country, street, streetNumber));
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isAwesome() {
        return isAwesome;
    }

    public void setAwesome(boolean awesome) {
        isAwesome = awesome;
    }

    public double getAwesomeness() {
        return awesomeness;
    }

    public void setAwesomeness(double awesomeness) {
        this.awesomeness = awesomeness;
    }

    public BigDecimal getWealth() {
        return wealth;
    }

    public void setWealth(BigDecimal wealth) {
        this.wealth = wealth;
    }
    //endregion
}
