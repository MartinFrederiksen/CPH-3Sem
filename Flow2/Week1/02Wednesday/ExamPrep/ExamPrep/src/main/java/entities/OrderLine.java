package entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Joe
 */
@Entity
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int Quantity;

    @ManyToOne
    private OrderEnt orderEnt;
    
    @ManyToOne(cascade = CascadeType.PERSIST)
    private ItemType itemType;
    
    
    public OrderLine() {
    }

    public OrderLine(int Quantity) {
        this.Quantity = Quantity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public OrderEnt getOrderEnt() {
        return orderEnt;
    }

    public void setOrderEnt(OrderEnt orderEnt) {
        this.orderEnt = orderEnt;
    }

    @Override
    public String toString() {
        return "OrderLine{" + "id=" + id + ", Quantity=" + Quantity + ", orderEnt=" + orderEnt + ", itemType=" + itemType + '}';
    }
    
}
