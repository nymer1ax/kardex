package co.com.java.jpa.inventory;

import co.com.java.model.inventory.Inventory;
import co.com.java.model.product.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;
import java.util.Optional;

public interface InventoryEntityDataRepository extends CrudRepository<InventoryEntityData, Integer>, QueryByExampleExecutor<InventoryEntityData> {

    Optional<InventoryEntityData> findByProduct(Product product);
    List<InventoryEntityData> findAll();

}
