package co.com.java.api.request;

import co.com.java.model.product.Product;
import lombok.*;
import org.wildfly.common.annotation.NotNull;

import java.time.LocalDate;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateSaleRequest {
    @NotNull
    private LocalDate date;
    @NotNull
    private List<Integer> products;
    @NotNull
    private List<Integer> quantities;
}
