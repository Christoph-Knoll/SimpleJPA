package demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue
    private Integer id;

    @OneToMany(mappedBy = "id.product", cascade = CascadeType.PERSIST)
    private List<OrderItem> items = new ArrayList<OrderItem>();

    public String description;
    public Double price;

    public Product(String description, Double price) {
        this.description = description;
        this.price = price;
    }

    public Product() {

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
}
