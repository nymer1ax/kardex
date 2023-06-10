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
                .map(o -> Product
                        .builder()
                        .id(o.getId())
                        .description(o.getDescription())
                        .price(o.getPrice())
                        .build()).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getById(Integer id) {
        Optional<ProductEntityData> o = repository.findById(id);
        if (o.isPresent()) {
            return Optional.of(Product
                    .builder()
                    .id(o.get().getId())
                    .description(o.get().getDescription())
                    .price(o.get().getPrice())
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntityData o = ProductEntityData
                .builder()
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName())
                .build();
        repository.save(o);
        return product;
    }

    @Override
    public Optional<Product> findByProductId(Integer id) {
        Optional<ProductEntityData> product = repository.findById(id);
        if(product.isPresent()){
            return Optional.of(Product.builder()
                    .name(product.get().getName())
                    .description(product.get().getDescription())
                    .price(product.get().getPrice())
                    .id(product.get().getId())
                    .build());
        }
        return Optional.empty();

    }

    @Override
    public List<Product> findAllById(List<Integer> products) {
        return repository.findAllByIdIn(products).stream().map(o -> Product.builder()
                .name(o.getName())
                .description(o.getDescription())
                .price(o.getPrice())
                .id(o.getId()).build()).collect(Collectors.toList());
    }
}
