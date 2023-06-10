package co.com.java.model.movements;
import co.com.java.model.movementtype.MovementType;
import co.com.java.model.product.Product;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Movement {
   private Integer id;
   private Product product;
   private Integer quantity;
   private MovementType type;
   private BigDecimal subtotal;
}
