package co.com.java.model.product.gateways;

import co.com.java.model.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> getAll();
    Optional<Product> getById(Integer id);
    Product saveProduct(Product product);

    Optional<Product> findByProductId(Integer id);

    List<Product> findAllById(List<Integer> products);

    List<Product> findAllByName(String name);
}
