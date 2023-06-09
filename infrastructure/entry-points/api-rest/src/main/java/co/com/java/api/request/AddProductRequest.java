package co.com.java.api.request;

import co.com.java.model.product.Product;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductRequest {
    private Product product;
    private Integer quantity;
}
