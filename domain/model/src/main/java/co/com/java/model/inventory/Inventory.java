package co.com.java.model.inventory;
import co.com.java.model.product.Product;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
//import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Inventory {
    private Integer id;
    private Integer quantity;
    private Product product;
}
