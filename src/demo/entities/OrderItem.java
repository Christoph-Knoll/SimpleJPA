package demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    private OrderItemId id;

    private Integer amount;

    public OrderItem(Order order, Product product, Integer amount){
        id = new OrderItemId(order, product);
        this.amount = amount;
    }
    public OrderItem(){
        id = new OrderItemId();
    }


    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

@Embeddable
class OrderItemId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    //region Constructors
    public OrderItemId(Order order, Product product) {
        this.order = order;
        this.product = product;
    }

    public OrderItemId() {
    }
    //endregion

    //region Props

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    //endregion
}
