package co.com.java.jpa.product;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.model.product.Product;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryAdapter extends AdapterOperations<Product, ProductEntityData, Integer, ProductEntityDataRepository> {
    public ProductRepositoryAdapter(ProductEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class/* change for domain model */));
    }
}
