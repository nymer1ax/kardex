package co.com.java.model.sale;
import co.com.java.model.product.Product;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Sale {
   private Integer id;
   private LocalDate date;
   private List<Product> products;
   private List<Integer> quantities;
}
