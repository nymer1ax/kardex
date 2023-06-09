package co.com.java.jpa.movement;

import co.com.java.jpa.product.ProductEntityData;
import co.com.java.model.movementtype.MovementType;
import jakarta.persistence.*;

@Entity
@Table(name = "Movement")
public class MovementEntityData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private ProductEntityData product;

    private Integer quantity;
    @Enumerated(EnumType.STRING)
    private MovementType type;

}
