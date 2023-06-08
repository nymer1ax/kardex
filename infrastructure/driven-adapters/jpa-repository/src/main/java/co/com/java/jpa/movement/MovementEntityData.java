package co.com.java.jpa.movement;

import co.com.java.jpa.product.ProductEntityData;
import co.com.java.model.movementtype.MovementType;
import jakarta.persistence.*;

@Entity
public class MovementEntityData {
    @Id
    private Integer id;

    @ManyToOne
    private ProductEntityData product;

    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private MovementType type;

}
