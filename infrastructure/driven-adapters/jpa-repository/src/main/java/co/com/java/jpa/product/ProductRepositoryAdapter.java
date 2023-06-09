package co.com.java.jpa.product;

import co.com.java.jpa.helper.AdapterOperations;
import co.com.java.model.product.Product;
import co.com.java.model.product.gateways.ProductRepository;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryAdapter extends AdapterOperations<Product, ProductEntityData, Integer, ProductEntityDataRepository> implements ProductRepository {
    public ProductRepositoryAdapter(ProductEntityDataRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class/* change for domain model */));
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll()
                .stream()
                .map(o -> toEntity(o)).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getById(Integer id) {
        Optional<ProductEntityData> o = repository.findById(id);
        if (o.isPresent()) {
            return Optional.of(toEntity(o.get()));
        }
        return Optional.empty();
    }

    @Override
    public void saveProduct(Product product) {
        ProductEntityData o = toData(product);
        repository.save(o);
    }
}
