package co.com.java.jpa.inventory;

import co.com.java.jpa.product.ProductEntityData;
import co.com.java.model.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
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
