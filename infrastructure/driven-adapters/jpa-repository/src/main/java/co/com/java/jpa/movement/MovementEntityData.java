package co.com.java.jpa.movement;

import co.com.java.jpa.product.ProductEntityData;
import co.com.java.model.movementtype.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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
    private BigDecimal subtotal;


}
