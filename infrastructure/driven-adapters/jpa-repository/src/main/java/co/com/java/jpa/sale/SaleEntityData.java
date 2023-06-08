package co.com.java.jpa.sale;

import co.com.java.jpa.product.ProductEntityData;
;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "Sale")
public class SaleEntityData {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    @OneToMany
    private List<ProductEntityData> products;
    @ElementCollection
    private List<Integer> quantities;


}
