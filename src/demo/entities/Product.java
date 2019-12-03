package demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    //region Fields
    public String description;
    public Double price;
    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue
    private int id;
    @OneToMany(mappedBy = "id.product", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<OrderItem>();
    //endregion

    //region Constructors
    public Product(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public Product() {

    }
    //endregion

    //region Props
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    //endregion
}
