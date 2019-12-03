package demo.dto;

public class OrderSummary {
    //region Fields
    private int count;
    private double cost;
    private String city;
    private String country;
    private String street;
    //endregion

    //region Constructors
    public OrderSummary(int count, double cost, String city, String country, String street) {
        this.count = count;
        this.cost = cost;
        this.city = city;
        this.country = country;
        this.street = street;
    }

    public OrderSummary() {
    }
    //endregion

    //region Props
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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
    //endregion
}
