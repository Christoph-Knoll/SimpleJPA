package demo.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private Integer id;

    @OneToMany(mappedBy = "id.product")
    private List<OrderItem> items = new ArrayList<OrderItem>();

    public String description;
    public BigDecimal price;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
