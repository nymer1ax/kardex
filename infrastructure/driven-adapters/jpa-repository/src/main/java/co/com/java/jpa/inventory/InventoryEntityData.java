package co.com.java.jpa.inventory;

import co.com.java.jpa.product.ProductEntityData;
import co.com.java.model.product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "Inventory")
public class InventoryEntityData {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    @OneToOne
    private ProductEntityData product;
}
