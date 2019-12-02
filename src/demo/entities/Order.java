package demo.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Integer id;


    @ManyToOne()
    @JoinColumns({
            @JoinColumn(name = "ssn"),
            @JoinColumn(name = "address_no")
    })
    private Address address;

    @OneToMany(mappedBy = "id.order")
    private List<OrderItem> items = new ArrayList<OrderItem>();

    private Date orderDate;
    private Short orderState;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Short getOrderState() {
        return orderState;
    }

    public void setOrderState(Short orderState) {
        this.orderState = orderState;
    }
}
