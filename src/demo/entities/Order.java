package demo.entities;

import org.ietf.jgss.GSSName;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// It is necessary to set the table-name to "order" because order itself is a command in posgresql
@Table(name = "\"order\"")
@Entity
public class Order {
    //region Fields
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue
    private int id;

    @ManyToOne()
    @JoinColumn(name = "ssn", insertable = false, updatable = false)
    private Person person;

    @ManyToOne()
    @JoinColumns({
            @JoinColumn(name = "address_no"),
            @JoinColumn(name = "ssn")
    })
    private Address address;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL)
    private List<OrderItem> items = new ArrayList<OrderItem>();

    private LocalDate orderDate;
    private Integer orderState;
    //endregion

    //region Constructors
    public Order(Address address, LocalDate orderDate, Integer orderState) {
        this.address = address;
        this.person = address.getId().getPerson();
        this.orderDate = orderDate;
        this.orderState = orderState;
    }

    public Order() {
    }
    //endregion

    //region Methods
    public void addProduct(Product product, Integer amount) {
        items.add(new OrderItem(this, product, amount));
    }

    public void addOrderItem(OrderItem item) {
        items.add(item);
    }
    //endregion

    //region Props
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    //endregion

}
