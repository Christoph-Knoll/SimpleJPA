package demo.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name="\"order\"")
@Entity
public class Order {
    @Id
    @Column(name = "order_id")
    @GeneratedValue
    private Short id;


    @ManyToOne()
    @JoinColumns({
            @JoinColumn(name = "id.ssn"),
            @JoinColumn(name = "id.address_id")
    })
    private Address address;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.PERSIST)
    private List<OrderItem> items = new ArrayList<OrderItem>();

    private LocalDate orderDate;
    private Integer orderState;

    public Order(Address address, LocalDate orderDate, Integer orderState) {
        this.address = address;
        this.orderDate = orderDate;
        this.orderState = orderState;
    }

    public Order() {
    }

    public void addProduct(Product product, Integer amount) {
        items.add(new OrderItem(this, product, amount));
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
